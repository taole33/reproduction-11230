import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class ReproTest {

    @Test
    void testImageResolution() {
        String imageName = "docker.io/redocly/cli:2.11.1"; 

        System.out.println("Attempting to start container: " + imageName);

        try (GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse(imageName))
                .withCommand("sleep", "100")) {
            
            container.start();
            
            System.out.println("Container started successfully!");
            System.out.println("Container ID: " + container.getContainerId());
        } catch (Exception e) {
            System.err.println("Failed to start container!");
            e.printStackTrace();
            throw e;
        }
    }
}