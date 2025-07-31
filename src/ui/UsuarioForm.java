package ui;

import dao.UsuarioDAO;
import model.Rol;
import model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UsuarioForm extends JFrame {
    private JTextField txtNombreUsuario;
    private JPasswordField txtContrasena;
    private JComboBox<Rol> cbRol;  // ComboBox con enum Rol
    private JTable tabla;
    private DefaultTableModel modelo;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private int idSeleccionado = -1;

    public UsuarioForm() {
        setTitle("Gestión de Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));

        panelFormulario.add(new JLabel("Nombre de Usuario:"));
        txtNombreUsuario = new JTextField();
        panelFormulario.add(txtNombreUsuario);

        panelFormulario.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panelFormulario.add(txtContrasena);

        panelFormulario.add(new JLabel("Rol:"));
        cbRol = new JComboBox<>(Rol.values()); // Cargamos valores del enum
        panelFormulario.add(cbRol);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        modelo = new DefaultTableModel(new String[]{"ID", "Usuario", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        listarUsuarios();

        btnGuardar.addActionListener(e -> guardarUsuario());
        btnActualizar.addActionListener(e -> actualizarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                idSeleccionado = (int) modelo.getValueAt(fila, 0);
                txtNombreUsuario.setText((String) modelo.getValueAt(fila, 1));
                // Seleccionamos el Rol en el combo box
                String rolStr = (String) modelo.getValueAt(fila, 2);
                cbRol.setSelectedItem(Rol.valueOf(rolStr.toUpperCase()));
                txtContrasena.setText(""); // No mostrar contraseña por seguridad
            }
        });

        setVisible(true);
    }

    private void listarUsuarios() {
        modelo.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.listar();
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{u.getId(), u.getNombreUsuario(), u.getRol().name()});
        }
    }

    private void guardarUsuario() {
        String nombre = txtNombreUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        Rol rol = (Rol) cbRol.getSelectedItem();

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombre);
        usuario.setContrasena(contrasena);
        usuario.setRol(rol);

        usuarioDAO.insertar(usuario);
        listarUsuarios();
        limpiarCampos();
        JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.");
    }

    private void actualizarUsuario() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar.");
            return;
        }

        String nombre = txtNombreUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        Rol rol = (Rol) cbRol.getSelectedItem();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario no puede estar vacío.");
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setId(idSeleccionado);
        usuario.setNombreUsuario(nombre);
        usuario.setRol(rol);

        // Actualiza la contraseña sólo si el campo no está vacío
        if (!contrasena.isEmpty()) {
            usuario.setContrasena(contrasena);
        } else {
            // Mantener la contraseña actual si el campo está vacío
            Usuario uExistente = usuarioDAO.obtenerPorId(idSeleccionado);
            if (uExistente != null) {
                usuario.setContrasena(uExistente.getContrasena());
            }
        }

        usuarioDAO.actualizar(usuario);
        listarUsuarios();
        limpiarCampos();
        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
    }

    private void eliminarUsuario() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            usuarioDAO.eliminar(idSeleccionado);
            listarUsuarios();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Usuario eliminado.");
        }
    }

    private void limpiarCampos() {
        txtNombreUsuario.setText("");
        txtContrasena.setText("");
        cbRol.setSelectedIndex(0);
        idSeleccionado = -1;
        tabla.clearSelection();
    }
}
