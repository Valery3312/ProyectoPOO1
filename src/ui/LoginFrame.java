package ui;

import dao.UsuarioDAO;
import model.Usuario;
import model.Rol;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Ingresar");

        add(new JLabel("Usuario:"));
        add(txtUsuario);
        add(new JLabel("Contraseña:"));
        add(txtPassword);
        add(new JLabel());
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            UsuarioDAO dao = new UsuarioDAO();
            String usuarioTexto = txtUsuario.getText().trim();
            String passwordTexto = new String(txtPassword.getPassword()).trim();

            if (usuarioTexto.isEmpty() || passwordTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese usuario y contraseña.");
                return;
            }

            Usuario usuario = dao.verificar(usuarioTexto, passwordTexto);
            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getNombreUsuario() + ". Rol: " + usuario.getRol());
                this.dispose(); // cerrar login

                // Mostrar menú o formulario según el rol
                if (usuario.getRol() == Rol.ADMIN) {
                    new UsuarioForm().setVisible(true); // o un menú admin con más opciones
                } else if (usuario.getRol() == Rol.CAJERO) {
                    new FacturaForm().setVisible(true); // formulario para registrar facturas/ventas
                } else {
                    JOptionPane.showMessageDialog(null, "Rol no reconocido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }
        });
    }
}
