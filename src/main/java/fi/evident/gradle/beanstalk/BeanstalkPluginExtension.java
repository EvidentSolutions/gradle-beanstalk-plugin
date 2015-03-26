package fi.evident.gradle.beanstalk;

public class BeanstalkPluginExtension {

    private String profile;
    private String s3Endpoint;
    private String beanstalkEndpoint;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getS3Endpoint() {
        return s3Endpoint;
    }

    public void setS3Endpoint(String s3Endpoint) {
        this.s3Endpoint = s3Endpoint;
    }

    public String getBeanstalkEndpoint() {
        return beanstalkEndpoint;
    }

    public void setBeanstalkEndpoint(String beanstalkEndpoint) {
        this.beanstalkEndpoint = beanstalkEndpoint;
    }
}
