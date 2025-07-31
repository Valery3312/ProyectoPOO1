package ui;

import dao.ClienteDAO;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteForm extends JFrame {
    private JTextField txtNombre, txtCorreo;
    private JTable tabla;
    private DefaultTableModel modelo;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private int idSeleccionado = -1;

    public ClienteForm() {
        setTitle("Gestión de Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2));
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelFormulario.add(txtCorreo);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        // Tabla
        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Correo"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.SOUTH);

        listarClientes();

        // Eventos
        btnGuardar.addActionListener(e -> {
            Cliente c = new Cliente(idSeleccionado, txtNombre.getText(), txtCorreo.getText());
            clienteDAO.insertar(c);
            listarClientes();
            limpiarCampos();
        });

        btnActualizar.addActionListener(e -> {
            if (idSeleccionado != -1) {
                Cliente c = new Cliente(idSeleccionado, txtNombre.getText(), txtCorreo.getText());
                clienteDAO.actualizar(c);
                listarClientes();
                limpiarCampos();
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado != -1) {
                clienteDAO.eliminar(idSeleccionado);
                listarClientes();
                limpiarCampos();
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                idSeleccionado = (int) modelo.getValueAt(fila, 0);
                txtNombre.setText((String) modelo.getValueAt(fila, 1));
                txtCorreo.setText((String) modelo.getValueAt(fila, 2));
            }
        });

        setVisible(true);
    }

    private void listarClientes() {
        modelo.setRowCount(0);
        List<Cliente> clientes = clienteDAO.listar();
        for (Cliente c : clientes) {
            modelo.addRow(new Object[]{c.getIdCliente(), c.getNombre(), c.getCorreo()});
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCorreo.setText("");
        idSeleccionado = -1;
        tabla.clearSelection();
    }
}
