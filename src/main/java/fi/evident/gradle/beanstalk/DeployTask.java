package fi.evident.gradle.beanstalk;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeployTask extends DefaultTask {

    private BeanstalkPluginExtension beanstalk;
    private BeanstalkDeployment deployment;
    private Object war;

    @TaskAction
    protected void deploy() {
        String versionLabel = getProject().getVersion().toString();
        if (versionLabel.endsWith("-SNAPSHOT")) {
            versionLabel += new Date().getTime(); // Append time to get unique version label
        }

        AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(new EnvironmentVariableCredentialsProvider(), new SystemPropertiesCredentialsProvider(), new ProfileCredentialsProvider(beanstalk.getProfile()));

        BeanstalkDeployer deployer = new BeanstalkDeployer(beanstalk.getS3Endpoint(), beanstalk.getBeanstalkEndpoint(), credentialsProvider);

        File warFile = getProject().files(war).getSingleFile();
        deployer.deploy(warFile, deployment.getApplication(), deployment.getEnvironment(), versionLabel);
    }

    public void setBeanstalk(BeanstalkPluginExtension beanstalk) {
        this.beanstalk = beanstalk;
    }

    @InputFiles
    public Object getWar() {
        return war;
    }

    public void setWar(Object war) {
        this.war = war;
    }

    public void setDeployment(BeanstalkDeployment deployment) {
        this.deployment = deployment;
        setWar(deployment.getWar());
    }
}
