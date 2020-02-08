package rafa.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_pedido database table.
 * 
 */
@Entity
@Table(name="detalle_pedido")
@NamedQuery(name="DetallePedido.findAll", query="SELECT d FROM DetallePedido d")
public class DetallePedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle")
	private Integer idDetalle;

	private BigDecimal cantidad;

	@Column(name="detalle_personalizacion")
	private String detallePersonalizacion;

	private Boolean personalizacion;

	@Column(name="precio_unitario_venta")
	private BigDecimal precioUnitarioVenta;

	//bi-directional many-to-one association to FormaPago
	@ManyToOne
	@JoinColumn(name="id_forma_pago_forma_pago")
	private FormaPago formaPago;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="id_pedido_pedido")
	private Pedido pedido;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto_producto")
	private Producto producto;

	public DetallePedido() {
	}

	public Integer getIdDetalle() {
		return this.idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getDetallePersonalizacion() {
		return this.detallePersonalizacion;
	}

	public void setDetallePersonalizacion(String detallePersonalizacion) {
		this.detallePersonalizacion = detallePersonalizacion;
	}

	public Boolean getPersonalizacion() {
		return this.personalizacion;
	}

	public void setPersonalizacion(Boolean personalizacion) {
		this.personalizacion = personalizacion;
	}

	public BigDecimal getPrecioUnitarioVenta() {
		return this.precioUnitarioVenta;
	}

	public void setPrecioUnitarioVenta(BigDecimal precioUnitarioVenta) {
		this.precioUnitarioVenta = precioUnitarioVenta;
	}

	public FormaPago getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}