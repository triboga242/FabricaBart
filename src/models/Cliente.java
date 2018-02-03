package models;

public class Cliente {

	private int ID_cliente;
	private String Nif_cliente;
	private String nombre_cliente;

	public Cliente(int iD_cliente, String nif_cliente, String nombre_cliente) {
		this.ID_cliente = iD_cliente;
		this.Nif_cliente = nif_cliente;
		this.nombre_cliente = nombre_cliente;
	}
	public int getID_cliente() {
		return ID_cliente;
	}
	public void setID_cliente(int iD_cliente) {
		this.ID_cliente = iD_cliente;
	}
	public String getNif_cliente() {
		return Nif_cliente;
	}
	public void setNif_cliente(String nif_cliente) {
		this.Nif_cliente = nif_cliente;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	@Override
	public String toString() {
		return "Cliente [ID_cliente=" + ID_cliente + ", Nif_cliente=" + Nif_cliente + ", nombre_cliente="
				+ nombre_cliente + "]";
	}
}
