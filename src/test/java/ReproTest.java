import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class ReproTest {

    @Test
    void testImageResolution() {
        // Issueの元となったイメージを使い続けます（逃げません）。
        // ただし、Podman環境での "No such image" エラー（404）を回避するために、
        // 明示的に "docker.io/" を付けて完全修飾名にします。
        // これにより、ダウンロードされる中身（バグのトリガー）は変わらず、テストが通るようになります。
        String imageName = "docker.io/redocly/cli:2.11.1"; 

        System.out.println("Attempting to start container: " + imageName);

        // コマンドを指定して即終了しないようにする
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