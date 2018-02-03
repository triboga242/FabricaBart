package models;

public class Producto {
	private int ID_producto;
	private String nombre_producto;
	private float precio_comprar;
	private float precio_vender;

	public Producto(int iD_producto, String nombre_producto, float precio_comprar,
			float precio_vender) {
		this.ID_producto = iD_producto;
		this.nombre_producto = nombre_producto;
		this.precio_comprar = precio_comprar;
		this.precio_vender = precio_vender;
	}

	public int getID_producto() {
		return ID_producto;
	}

	public void setID_producto(int iD_producto) {
		ID_producto = iD_producto;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public float getPrecio_comprar() {
		return precio_comprar;
	}

	public void setPrecio_comprar(float precio_comprar) {
		this.precio_comprar = precio_comprar;
	}

	public float getPrecio_vender() {
		return precio_vender;
	}

	public void setPrecio_vender(float precio_vender) {
		this.precio_vender = precio_vender;
	}

	@Override
	public String toString() {
		return "Producto [ID_producto=" + ID_producto + ", nombre_producto=" + nombre_producto + ", precio_comprar="
				+ precio_comprar + ", precio_vender=" + precio_vender + "]";
	}
}
