package models;

public class Detalle_Encargo {

	public int id_pedido;
	public int id_producto;
	public int cantidad;
	public Detalle_Encargo(int id_pedido, int id_producto, int cantidad) {
		super();
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Detalle_Encargo [id_pedido=" + id_pedido + ", id_producto=" + id_producto + ", cantidad=" + cantidad
				+ "]";
	}
}
