package ui;

import javax.swing.*;
import java.awt.*;

public class CajeroPanel extends JFrame {
    public CajeroPanel() {
        setTitle("Panel Cajero");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnClientes = new JButton("Registrar Cliente");
        JButton btnConsultarPrecios = new JButton("Consultar Precio de Productos");
        JButton btnCrearFactura = new JButton("Crear Factura");

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnClientes);
        add(btnConsultarPrecios);
        add(btnCrearFactura);

        btnClientes.addActionListener(e -> {
            new ClienteForm().setVisible(true);
        });

        btnConsultarPrecios.addActionListener(e -> {
            // Para consultar precio puedes abrir ProductoForm o crear uno específico
            JOptionPane.showMessageDialog(this, "Consulta de precios próximamente");
        });

        btnCrearFactura.addActionListener(e -> {
            new FacturaForm().setVisible(true);
        });

        setVisible(true);
    }
}
