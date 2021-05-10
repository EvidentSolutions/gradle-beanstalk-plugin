package fi.evident.gradle.beanstalk;

import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeployTaskTest {
    @Test
    public void versionShouldIncludePrefix() {
        Project project = ProjectBuilder.builder().build();
        project.setVersion("version");
        Map<String, Object> params = new HashMap<>();
        params.put("type", TestDeployTask.class);
        TestDeployTask task = (TestDeployTask) project.task(params, "tasktest");
        BeanstalkDeployment deployment = new BeanstalkDeployment("tasktest");
        deployment.setVersionPrefix("test-");
        task.setDeployment(deployment);

        assertTrue(task.findVersionLabel().matches("test-version"));
    }

    public static class TestDeployTask extends DeployTask {
        private String findVersionLabel() {
            return getVersionLabel();
        }
    }
}
