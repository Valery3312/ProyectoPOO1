package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Factura {
    private int idFactura;
    private Date fecha;
    private int idCliente;
    private BigDecimal total;

    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public void setFecha(java.util.Date fecha) {
    }
}
