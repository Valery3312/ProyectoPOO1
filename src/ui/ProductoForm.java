package ui;

import dao.ProductoDAO;
import model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class ProductoForm extends JFrame {

    private JTextField txtNombre, txtPrecio;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnLimpiar;
    private JTable tablaProductos;
    private DefaultTableModel modelo;
    private ProductoDAO productoDAO;
    private int idSeleccionado = -1;

    public ProductoForm() {
        productoDAO = new ProductoDAO();

        setTitle("Gestión de Productos");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);

        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaProductos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);

        add(panelForm, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        cargarProductos();

        btnGuardar.addActionListener(e -> guardarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tablaProductos.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tablaProductos.getSelectedRow() != -1) {
                int fila = tablaProductos.getSelectedRow();
                idSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                txtPrecio.setText(modelo.getValueAt(fila, 2).toString());
            }
        });
    }

    private void cargarProductos() {
        modelo.setRowCount(0);
        List<Producto> lista = productoDAO.listar();
        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                    p.getIdProducto(),
                    p.getNombre(),
                    p.getPrecio()
            });
        }
    }

    private void guardarProducto() {
        if (validarCampos()) {
            Producto p = new Producto();
            p.setNombre(txtNombre.getText());
            p.setPrecio(new BigDecimal(txtPrecio.getText()));

            productoDAO.insertar(p);
            JOptionPane.showMessageDialog(this, "Producto guardado");
            limpiarCampos();
            cargarProductos();
        }
    }

    private void actualizarProducto() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para actualizar");
            return;
        }
        if (validarCampos()) {
            Producto p = new Producto();
            p.setIdProducto(idSeleccionado);
            p.setNombre(txtNombre.getText());
            p.setPrecio(new BigDecimal(txtPrecio.getText()));

            productoDAO.actualizar(p);
            JOptionPane.showMessageDialog(this, "Producto actualizado");
            limpiarCampos();
            cargarProductos();
            idSeleccionado = -1;
        }
    }

    private void eliminarProducto() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            productoDAO.eliminar(idSeleccionado);
            JOptionPane.showMessageDialog(this, "Producto eliminado");
            limpiarCampos();
            cargarProductos();
            idSeleccionado = -1;
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        tablaProductos.clearSelection();
        idSeleccionado = -1;
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos");
            return false;
        }
        try {
            new BigDecimal(txtPrecio.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Precio debe ser un número válido");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductoForm().setVisible(true);
        });
    }
}
