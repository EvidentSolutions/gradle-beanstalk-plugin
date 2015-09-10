package fi.evident.gradle.beanstalk;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.TaskAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeployTask extends DefaultTask {

    private BeanstalkPluginExtension beanstalk;
    private BeanstalkDeployment deployment;
    private Object war;

    private static final Logger log = LoggerFactory.getLogger(DeployTask.class);
    @TaskAction
    protected void deploy() {
        String versionLabel = getProject().getVersion().toString();
        if (versionLabel.endsWith("-SNAPSHOT")) {
            String timeLabel = new SimpleDateFormat("yyyyMMdd'.'HHmmss").format(new Date());
            versionLabel = versionLabel.replace("SNAPSHOT", timeLabel); // Append time to get unique version label
        }

        AWSCredentials awsCredentials;
        if (deployment.getAccount()==null || deployment.getAccount().isEmpty()) {
            AWSCredentialsProviderChain credentialsProviderChain = new AWSCredentialsProviderChain(new EnvironmentVariableCredentialsProvider(), new SystemPropertiesCredentialsProvider(), new ProfileCredentialsProvider(beanstalk.getProfile()));
            awsCredentials = credentialsProviderChain.getCredentials();
        }else{
            awsCredentials =  CredentialUtility.getAssumeRoleCredentials(deployment.getArnRole(), deployment.getAccount());
            log.info("Obtained credentials using arnRole {} for account {}", deployment.getArnRole() , deployment.getAccount());
            beanstalk.setBeanstalkEndpoint(deployment.getBeanstalkEndpoint());
            beanstalk.setS3Endpoint(deployment.getS3Endpoint());
        }
        BeanstalkDeployer deployer = new BeanstalkDeployer(beanstalk.getS3Endpoint(), beanstalk.getBeanstalkEndpoint(), awsCredentials);
        File warFile = getProject().files(war).getSingleFile();
        deployer.deploy(warFile, deployment.getApplication(), deployment.getEnvironment(), deployment.getTemplate(), versionLabel);
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
