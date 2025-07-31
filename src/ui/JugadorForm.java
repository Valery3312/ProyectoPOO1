package ui;

import dao.JugadorDAO;
import model.Jugador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class JugadorForm extends JFrame {

    private JTextField txtNombre, txtEdad, txtNivel, txtPuntaje;
    private JButton btnGuardar, btnActualizar, btnEliminar, btnLimpiar;
    private JTable tablaJugadores;
    private DefaultTableModel modelo;
    private JugadorDAO jugadorDAO;
    private int idSeleccionado = -1;

    public JugadorForm() {
        jugadorDAO = new JugadorDAO();

        setTitle("Gestión de Jugadores");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelForm.add(txtEdad);

        panelForm.add(new JLabel("Nivel:"));
        txtNivel = new JTextField();
        panelForm.add(txtNivel);

        panelForm.add(new JLabel("Puntaje:"));
        txtPuntaje = new JTextField();
        panelForm.add(txtPuntaje);

        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // Tabla
        modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Edad", "Nivel", "Puntaje"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };
        tablaJugadores = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaJugadores);

        add(panelForm, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        cargarJugadores();

        // Listeners botones
        btnGuardar.addActionListener(e -> guardarJugador());
        btnActualizar.addActionListener(e -> actualizarJugador());
        btnEliminar.addActionListener(e -> eliminarJugador());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // Listener para seleccionar fila en tabla
        tablaJugadores.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tablaJugadores.getSelectedRow() != -1) {
                int fila = tablaJugadores.getSelectedRow();
                idSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                txtEdad.setText(modelo.getValueAt(fila, 2).toString());
                txtNivel.setText(modelo.getValueAt(fila, 3).toString());
                txtPuntaje.setText(modelo.getValueAt(fila, 4).toString());
            }
        });
    }

    private void cargarJugadores() {
        modelo.setRowCount(0); // limpiar tabla
        List<Jugador> lista = jugadorDAO.listar();
        for (Jugador j : lista) {
            modelo.addRow(new Object[]{
                    j.getIdJugador(),
                    j.getNombre(),
                    j.getEdad(),
                    j.getNivel(),
                    j.getPuntaje()
            });
        }
    }

    private void guardarJugador() {
        if (validarCampos()) {
            Jugador j = new Jugador();
            j.setNombre(txtNombre.getText());
            j.setEdad(Integer.parseInt(txtEdad.getText()));
            j.setNivel(Integer.parseInt(txtNivel.getText()));
            j.setPuntaje(Integer.parseInt(txtPuntaje.getText()));

            jugadorDAO.insertar(j);
            JOptionPane.showMessageDialog(this, "Jugador guardado");
            limpiarCampos();
            cargarJugadores();
        }
    }

    private void actualizarJugador() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un jugador para actualizar");
            return;
        }
        if (validarCampos()) {
            Jugador j = new Jugador();
            j.setIdJugador(idSeleccionado);
            j.setNombre(txtNombre.getText());
            j.setEdad(Integer.parseInt(txtEdad.getText()));
            j.setNivel(Integer.parseInt(txtNivel.getText()));
            j.setPuntaje(Integer.parseInt(txtPuntaje.getText()));

            jugadorDAO.actualizar(j);
            JOptionPane.showMessageDialog(this, "Jugador actualizado");
            limpiarCampos();
            cargarJugadores();
            idSeleccionado = -1;
        }
    }

    private void eliminarJugador() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un jugador para eliminar");
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este jugador?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            jugadorDAO.eliminar(idSeleccionado);
            JOptionPane.showMessageDialog(this, "Jugador eliminado");
            limpiarCampos();
            cargarJugadores();
            idSeleccionado = -1;
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtNivel.setText("");
        txtPuntaje.setText("");
        tablaJugadores.clearSelection();
        idSeleccionado = -1;
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() ||
                txtEdad.getText().isEmpty() ||
                txtNivel.getText().isEmpty() ||
                txtPuntaje.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos");
            return false;
        }
        try {
            Integer.parseInt(txtEdad.getText());
            Integer.parseInt(txtNivel.getText());
            Integer.parseInt(txtPuntaje.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad, Nivel y Puntaje deben ser números enteros");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JugadorForm().setVisible(true);
        });
    }
}
