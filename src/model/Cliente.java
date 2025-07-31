package model;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String correo;

    public Cliente(int idSeleccionado, String text, String text1) {
    }

    public Cliente() {

    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
