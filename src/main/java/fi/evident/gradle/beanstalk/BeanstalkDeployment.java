package fi.evident.gradle.beanstalk;

public class BeanstalkDeployment {

    private final String name;
    private String application;
    private String environment;
    private Object war;

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

    public Object getWar() {
        return war;
    }

    public void setWar(Object war) {
        this.war = war;
    }
}
