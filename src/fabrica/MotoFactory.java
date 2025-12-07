package fabrica;

import controlador.ImageFacade;
import java.awt.Image;

public class MotoFactory implements VehiculoFactory {
    private boolean esPro;
    public MotoFactory(boolean esPro) { this.esPro = esPro; }

    @Override
    public Image[] getFrames() {
        return ImageFacade.loadFrames("/Imagenes/imagenes/motonormal", 4, ".png");
    }

    @Override public int getDelay() { return 15; }
    @Override public int getPaso() { return 7; }
    @Override public String getTipo() { return "moto"; }
}
