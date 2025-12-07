package fabrica;

import java.awt.Image;

public interface VehiculoFactory {
    Image[] getFrames();
    int getDelay();
    int getPaso();
    String getTipo();
}
