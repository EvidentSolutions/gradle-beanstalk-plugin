package fi.evident.gradle.beanstalk;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;

public class CredentialUtility {
    private static final String DEFAULT = "default";

    public static StaticCredentialsProvider getAssumeRoleCredentials(String arnRole, String sessionName) {
        AWSCredentialsProviderChain awsCredentialsProvider = new AWSCredentialsProviderChain(new ClasspathPropertiesFileCredentialsProvider(), new EnvironmentVariableCredentialsProvider(), new SystemPropertiesCredentialsProvider(), new InstanceProfileCredentialsProvider(), new ProfileCredentialsProvider(DEFAULT));
        AWSCredentials defaultCredentials = awsCredentialsProvider.getCredentials();
        System.out.println(defaultCredentials.getAWSAccessKeyId());
        return CredentialUtility.getSessionCredentialsForRole(arnRole, sessionName, defaultCredentials);
    }

    public static StaticCredentialsProvider getSessionCredentialsForRole(String arnRole, String sessionName, AWSCredentials awsCredentials) {
        AWSSecurityTokenServiceClient stsClient = new AWSSecurityTokenServiceClient(awsCredentials);
        AssumeRoleRequest assumeRequest = new AssumeRoleRequest().withRoleArn(arnRole).withDurationSeconds(3600).withRoleSessionName(sessionName);
        AssumeRoleResult assumeResult = stsClient.assumeRole(assumeRequest);
        Credentials credentials = assumeResult.getCredentials();
        return new StaticCredentialsProvider(new BasicSessionCredentials(credentials.getAccessKeyId(), credentials.getSecretAccessKey(), credentials.getSessionToken()));
    }
}
