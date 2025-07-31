package ui;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {
    public AdminPanel() {
        setTitle("Panel Administrador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnUsuarios = new JButton("Gestionar Usuarios");
        JButton btnProductos = new JButton("Gestionar Productos");
        JButton btnReportes = new JButton("Reportes");

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnUsuarios);
        add(btnProductos);
        add(btnReportes);

        btnUsuarios.addActionListener(e -> {
            // Aquí abrirás el formulario de usuarios cuando lo tengas
            JOptionPane.showMessageDialog(this, "Abrir UsuarioForm");
            // new UsuarioForm().setVisible(true);
        });

        btnProductos.addActionListener(e -> {
            new ProductoForm().setVisible(true);
        });

        btnReportes.addActionListener(e -> {
            // Aquí puedes abrir un formulario de reportes cuando esté listo
            JOptionPane.showMessageDialog(this, "Funcionalidad de reportes próximamente");
        });

        setVisible(true);
    }
}
