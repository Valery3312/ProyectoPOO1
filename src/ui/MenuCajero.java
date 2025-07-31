package ui;

import javax.swing.*;
import java.awt.*;

public class MenuCajero extends JFrame {
    public MenuCajero() {
        setTitle("Menú Cajero");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnJugadores = new JButton("Gestión de Jugadores");
        JButton btnProductos = new JButton("Gestión de Productos");
        JButton btnClientes = new JButton("Gestión de Clientes");
        JButton btnFacturas = new JButton("Gestión de Facturas");
        JButton btnSalir = new JButton("Salir");

        add(btnJugadores);
        add(btnProductos);
        add(btnClientes);
        add(btnFacturas);
        add(btnSalir);

        btnJugadores.addActionListener(e -> {
            new JugadorForm().setVisible(true);
            this.dispose();
        });

        btnProductos.addActionListener(e -> {
            new ProductoForm().setVisible(true);
            this.dispose();
        });

        btnClientes.addActionListener(e -> {
            new ClienteForm().setVisible(true);
            this.dispose();
        });

        btnFacturas.addActionListener(e -> {
            new FacturaForm().setVisible(true);
            this.dispose();
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }
}
