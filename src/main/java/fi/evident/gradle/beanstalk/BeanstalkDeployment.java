package fi.evident.gradle.beanstalk;

public class BeanstalkDeployment {

    private final String name;
    private String application;
    private String environment;
    private String template = "default";
    private Object war;
    private String account;
    private String arnRole;
    private String s3Endpoint;
    private String beanstalkEndpoint;
    
    public BeanstalkDeployment(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
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

    public Object getWar() {
        return war;
    }

    public void setWar(Object war) {
        this.war = war;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getArnRole() {
        return arnRole;
    }

    public void setArnRole(String arnRole) {
        this.arnRole = arnRole;
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
