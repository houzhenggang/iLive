package izuanqian;

import lombok.Getter;

import java.net.URI;
import java.nio.file.Path;

/**
 * @author sanlion do
 */
public class ImagePath {

    @Getter private Path localTargetDirectory;
    @Getter private URI show;

    public ImagePath(Path localTargetDirectory, URI show) {
        this.localTargetDirectory = localTargetDirectory;
        this.show = show;
    }
}
