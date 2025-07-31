package ui;

import dao.ClienteDAO;
import dao.FacturaDAO;
import model.Cliente;
import model.Factura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FacturaForm extends JFrame {
    private JTextField txtFecha, txtTotal;
    private JComboBox<Cliente> cbClientes;
    private JTable tabla;
    private DefaultTableModel modelo;
    private FacturaDAO facturaDAO = new FacturaDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private int idSeleccionado = -1;

    public FacturaForm() {
        setTitle("Gestión de Facturas");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));

        panelFormulario.add(new JLabel("Fecha (yyyy-MM-dd):"));
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha);

        panelFormulario.add(new JLabel("Cliente:"));
        cbClientes = new JComboBox<>();
        panelFormulario.add(cbClientes);

        panelFormulario.add(new JLabel("Total:"));
        txtTotal = new JTextField();
        panelFormulario.add(txtTotal);

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

        modelo = new DefaultTableModel(new String[]{"ID", "Fecha", "Cliente", "Total"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.SOUTH);

        cargarClientes();
        listarFacturas();

        btnGuardar.addActionListener(e -> guardarFactura());
        btnActualizar.addActionListener(e -> actualizarFactura());
        btnEliminar.addActionListener(e -> eliminarFactura());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                idSeleccionado = (int) modelo.getValueAt(fila, 0);
                txtFecha.setText((String) modelo.getValueAt(fila, 1));
                Cliente cliente = (Cliente) modelo.getValueAt(fila, 2);
                cbClientes.setSelectedItem(cliente);
                txtTotal.setText(modelo.getValueAt(fila, 3).toString());
            }
        });

        setVisible(true);
    }

    private void cargarClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        DefaultComboBoxModel<Cliente> comboModel = new DefaultComboBoxModel<>();
        for (Cliente c : clientes) {
            comboModel.addElement(c);
        }
        cbClientes.setModel(comboModel);
    }

    private void listarFacturas() {
        modelo.setRowCount(0);
        List<Factura> facturas = facturaDAO.listar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Factura f : facturas) {
            Cliente cliente = clienteDAO.listar().stream()
                    .filter(c -> c.getIdCliente() == f.getIdCliente())
                    .findFirst()
                    .orElse(null);
            modelo.addRow(new Object[]{
                    f.getIdFactura(),
                    sdf.format(f.getFecha()),
                    cliente,
                    f.getTotal()
            });
        }
    }

    private void guardarFactura() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date fecha = (java.sql.Date) sdf.parse(txtFecha.getText());
            Cliente cliente = (Cliente) cbClientes.getSelectedItem();
            BigDecimal total = new BigDecimal(txtTotal.getText());

            Factura f = new Factura();
            f.setFecha((java.sql.Date) fecha);
            f.setIdCliente(cliente.getIdCliente());
            f.setTotal(total);

            facturaDAO.insertar(f);
            listarFacturas();
            limpiarCampos();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Fecha inválida. Use formato yyyy-MM-dd.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Total inválido.");
        }
    }

    private void actualizarFactura() {
        if (idSeleccionado == -1) return;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(txtFecha.getText());
            Cliente cliente = (Cliente) cbClientes.getSelectedItem();
            BigDecimal total = new BigDecimal(txtTotal.getText());

            Factura f = new Factura();
            f.setIdFactura(idSeleccionado);
            f.setFecha(fecha);
            f.setIdCliente(cliente.getIdCliente());
            f.setTotal(total);

            facturaDAO.actualizar(f);
            listarFacturas();
            limpiarCampos();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Fecha inválida. Use formato yyyy-MM-dd.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Total inválido.");
        }
    }

    private void eliminarFactura() {
        if (idSeleccionado == -1) return;
        facturaDAO.eliminar(idSeleccionado);
        listarFacturas();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtFecha.setText("");
        cbClientes.setSelectedIndex(0);
        txtTotal.setText("");
        idSeleccionado = -1;
        tabla.clearSelection();
    }
}
