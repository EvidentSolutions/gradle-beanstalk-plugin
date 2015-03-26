package fi.evident.gradle.beanstalk;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeployTask extends DefaultTask {

    private BeanstalkPluginExtension beanstalk;
    private BeanstalkDeployment deployment;
    private File war;

    @TaskAction
    protected void deploy() {
        String label = new SimpleDateFormat("yyyyMMdd'T'HHmmss").format(new Date());
        AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(new EnvironmentVariableCredentialsProvider(), new SystemPropertiesCredentialsProvider(), new ProfileCredentialsProvider(beanstalk.getProfile()));

        BeanstalkDeployer deployer = new BeanstalkDeployer(beanstalk.getS3Endpoint(), beanstalk.getBeanstalkEndpoint(), credentialsProvider);
        deployer.deploy(war, deployment.getApplication(), deployment.getEnvironment(), label);
    }

    public void setBeanstalk(BeanstalkPluginExtension beanstalk) {
        this.beanstalk = beanstalk;
    }

    public void setWar(File war) {
        this.war = war;
    }

    public void setDeployment(BeanstalkDeployment deployment) {
        this.deployment = deployment;
    }
}
