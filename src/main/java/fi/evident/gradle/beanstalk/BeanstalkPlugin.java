package fi.evident.gradle.beanstalk;

import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionAware;

public class BeanstalkPlugin implements Plugin<Project> {
    public void apply(Project project) {
        NamedDomainObjectContainer<BeanstalkDeployment> deployments = project.container(BeanstalkDeployment.class);
        BeanstalkPluginExtension beanstalk = project.getExtensions().create("beanstalk", BeanstalkPluginExtension.class);
        ((ExtensionAware) beanstalk).getExtensions().add("deployments", deployments);

        project.afterEvaluate(p -> {
            for (BeanstalkDeployment deployment : deployments) {
                String name = deployment.getName();
                String task = "deploy" + Character.toUpperCase(name.charAt(0)) + name.substring(1);

                p.getTasks().create(task, DeployTask.class, deployTask -> {
                    deployTask.setBeanstalk(beanstalk);
                    deployTask.setDeployment(deployment);
                });
            }
        });
    }
}