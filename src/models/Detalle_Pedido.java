package models;

public class Detalle_Pedido {

	public int id_pedido;
	public int id_producto;
	public int cantidad;

	public Detalle_Pedido(int id_pedido, int id_producto, int cantidad) {
		super();
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Detalle_Pedido [id_pedido=" + id_pedido + ", id_producto=" + id_producto + ", cantidad=" + cantidad
				+ "]";
	}
}
