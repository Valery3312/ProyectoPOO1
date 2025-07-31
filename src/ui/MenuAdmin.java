package ui;

import javax.swing.*;
import java.awt.*;

public class MenuAdmin extends JFrame {
    public MenuAdmin() {
        setTitle("Menú Administrador");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnUsuarios = new JButton("Gestión de Usuarios");
        JButton btnJugadores = new JButton("Gestión de Jugadores");
        JButton btnProductos = new JButton("Gestión de Productos");
        JButton btnClientes = new JButton("Gestión de Clientes");
        JButton btnFacturas = new JButton("Gestión de Facturas");
        JButton btnSalir = new JButton("Salir");

        add(btnUsuarios);
        add(btnJugadores);
        add(btnProductos);
        add(btnClientes);
        add(btnFacturas);
        add(btnSalir);

        btnUsuarios.addActionListener(e -> {
            new UsuarioForm().setVisible(true);
            this.dispose();
        });

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
