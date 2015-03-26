package fi.evident.gradle.beanstalk

import com.amazonaws.auth.AWSCredentialsProviderChain
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider
import com.amazonaws.auth.SystemPropertiesCredentialsProvider
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class BeanstalkPlugin implements Plugin<Project> {

    void apply(Project project) {
        def deployments = project.container(BeanstalkDeployment)
        def beanstalk = project.extensions.create('beanstalk', BeanstalkPluginExtension)
        project.beanstalk.extensions.deployments = deployments

        project.tasks.addRule("Pattern: deploy<ID>") { String taskName ->
            if (taskName.startsWith('deploy')) {
                String suffix = (taskName - 'deploy')
                String env = "${suffix.charAt(0).toLowerCase()}${suffix.substring(1)}"

                def depl = deployments[env]
                project.task(taskName) { t ->
                    t.dependsOn depl.war
                    t << {
                        def label = new Date().format("yyyyMMdd'T'HHmmss");
                        def credentialsProvider =
                            new AWSCredentialsProviderChain(
                                new EnvironmentVariableCredentialsProvider(),
                                new SystemPropertiesCredentialsProvider(),
                                new ProfileCredentialsProvider(beanstalk.profile)
                            );

                        BeanstalkDeployer deployer = new BeanstalkDeployer(beanstalk.s3Endpoint, beanstalk.beanstalkEndpoint, credentialsProvider)
                        deployer.deploy(depl.war.outputs.files.singleFile, depl.application, depl.environment, label)
                    }
                }
            }
        }
    }
}

class BeanstalkPluginExtension {
    String profile
    String s3Endpoint
    String beanstalkEndpoint
}

class BeanstalkDeployment {

    final String name
    String application
    String environment
    Task war

    BeanstalkDeployment(String name) {
        this.name = name;
    }
}
