package fi.evident.gradle.beanstalk;

public class BeanstalkDeployment {

    private final String name;
    private String application;
    private String environment;
    private String template = "default";
    private String versionPrefix = "";
    private String versionSuffix = "";
    private Object file;
    private String s3Endpoint;
    private String beanstalkEndpoint;

    public BeanstalkDeployment(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public String getVersionPrefix() {
        return versionPrefix;
    }

    public void setVersionPrefix(String versionPrefix) {
        this.versionPrefix = versionPrefix;
    }

    public String getVersionSuffix() {
        return versionSuffix;
    }

    public void setVersionSuffix(String versionSuffix) {
        this.versionSuffix = versionSuffix;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Deprecated
    public void setWar(Object war) {
        this.file = war;
    }

    @Deprecated
    public Object getWar() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

    public Object getFile() {
        return file;
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
