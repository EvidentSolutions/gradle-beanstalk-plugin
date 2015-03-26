package fi.evident.gradle.beanstalk;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;
import org.gradle.api.Rule;

class DeployRule implements Rule {
    private final NamedDomainObjectContainer<BeanstalkDeployment> deployments;
    private final Project project;
    private final BeanstalkPluginExtension beanstalk;

    public DeployRule(NamedDomainObjectContainer<BeanstalkDeployment> deployments, Project project, BeanstalkPluginExtension beanstalk) {
        this.deployments = deployments;
        this.project = project;
        this.beanstalk = beanstalk;
    }

    @Override
    public String getDescription() {
        return "Pattern: deploy<ID>";
    }

    @Override
    public void apply(String s) {
        if (s.startsWith("deploy")) {
            String suffix = s.substring("deploy".length());
            final String env = Character.toLowerCase(suffix.charAt(0)) + suffix.substring(1);

            project.getTasks().create(s, DeployTask.class, new Action<DeployTask>() {
                @Override
                public void execute(DeployTask task) {
                    BeanstalkDeployment deployment = deployments.getAt(env);
                    task.setBeanstalk(beanstalk);
                    task.setDeployment(deployment);
                    task.setWar(deployment.getWar().getOutputs().getFiles().getSingleFile());
                    task.dependsOn(deployment.getWar());
                }
            });
        }
    }
}
