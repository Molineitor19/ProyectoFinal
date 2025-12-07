package fabrica;

import controlador.ImageFacade;
import java.awt.Image;

public class BicicletaFactory implements VehiculoFactory {
    private boolean esPro;
    public BicicletaFactory(boolean esPro) { this.esPro = esPro; }

    @Override
    public Image[] getFrames() {
        return ImageFacade.loadFrames("/Imagenes/imagenes/Ciclanormal", 4, ".png");
    }

    @Override public int getDelay() { return 25; }
    @Override public int getPaso() { return 5; }
    @Override public String getTipo() { return "bicicleta"; }
}
