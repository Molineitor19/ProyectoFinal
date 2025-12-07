package fabrica;

import controlador.ImageFacade;
import java.awt.Image;

public class CarroFactory implements VehiculoFactory {
    private boolean esPro;
    public CarroFactory(boolean esPro) { this.esPro = esPro; }

    @Override
    public Image[] getFrames() {
        if (esPro) {
            return ImageFacade.loadFrames("/Imagenes/imagenes/carronormal", 4, ".png");
        } else {
            return ImageFacade.loadFrames("/Imagenes/imagenes/carronormal", 4, ".png");
        }
    }

    @Override public int getDelay() { return 10; }
    @Override public int getPaso() { return 9; }
    @Override public String getTipo() { return "carro"; }
}
