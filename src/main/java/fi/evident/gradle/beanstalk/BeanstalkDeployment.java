package fi.evident.gradle.beanstalk;

import org.gradle.api.Task;

public class BeanstalkDeployment {

    private final String name;
    private String application;
    private String environment;
    private Task war;

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

    public Task getWar() {
        return war;
    }

    public void setWar(Task war) {
        this.war = war;
    }
}
