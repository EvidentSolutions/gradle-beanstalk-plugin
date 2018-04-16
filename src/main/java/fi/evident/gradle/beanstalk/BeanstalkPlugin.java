package fi.evident.gradle.beanstalk;

import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionAware;

public class BeanstalkPlugin implements Plugin<Project> {
    public void apply(final Project project) {
        NamedDomainObjectContainer<BeanstalkDeployment> deployments = project.container(BeanstalkDeployment.class);
        BeanstalkPluginExtension beanstalk = project.getExtensions().create("beanstalk", BeanstalkPluginExtension.class);

        ((ExtensionAware) beanstalk).getExtensions().add("deployments", deployments);

        project.getTasks().addRule(new DeployRule(deployments, project, beanstalk));
        
        project.afterEvaluate(p -> {
			deployments.forEach(deployment -> {
						String name = deployment.getName();
						String task = "deploy" + name.substring(0, 1).toUpperCase() + name.substring(1);

						project.getTasks().create(task, DeployTask.class, deployTask -> {
							deployTask.setBeanstalk(beanstalk);
							deployTask.setDeployment(deployment);
						});
					}
			);
		});
    }
}
