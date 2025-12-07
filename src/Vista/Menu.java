package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {

    private JButton btnCarro, btnMoto, btnBici, btnJugar;
    private String vehiculoSeleccionado = "carro";

    public Menu() {
        setTitle("Seleccionar Vehículo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        btnCarro = new JButton("Carro");
        btnMoto = new JButton("Moto");
        btnBici = new JButton("Bicicleta");
        btnJugar = new JButton("Iniciar Juego");

        btnCarro.addActionListener(this);
        btnMoto.addActionListener(this);
        btnBici.addActionListener(this);
        btnJugar.addActionListener(this);

        add(btnCarro);
        add(btnMoto);
        add(btnBici);
        add(btnJugar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnCarro) vehiculoSeleccionado = "carro";
        if (e.getSource() == btnMoto) vehiculoSeleccionado = "moto";
        if (e.getSource() == btnBici) vehiculoSeleccionado = "bicicleta";

        if (e.getSource() == btnJugar) {
            JFrame ventanaJuego = new JFrame("Juego");
            ventanaJuego.setSize(900, 500);
            ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Vista panelJuego = new Vista(vehiculoSeleccionado);
            ventanaJuego.add(panelJuego);
            ventanaJuego.setVisible(true);

            this.dispose();
        }
    }
}
