package models;

public class Item {
	
	private Producto producto;
	private int cantidad;
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Item(Producto producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "Item [producto=" + producto.toString() + ", cantidad=" + cantidad + "]";
	}

	
}