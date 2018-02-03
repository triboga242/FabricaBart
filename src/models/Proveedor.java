package models;

public class Proveedor {

	private int ID_proveedor;
	private String Nif_proveedor;
	private String nombre_proveedor;
	
	public Proveedor(int ID_proveedor, String nif_proveedor, String nombre_proveedor) {
		this.ID_proveedor=ID_proveedor;
		this.Nif_proveedor = nif_proveedor;
		this.nombre_proveedor = nombre_proveedor;
	}

	public String getNif_proveedor() {
		return Nif_proveedor;
	}

	public void setNif_proveedor(String nif_proveedor) {
		this.Nif_proveedor = nif_proveedor;
	}

	public String getNombre_proveedor() {
		return nombre_proveedor;
	}

	public void setNombre_proveedor(String nombre_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
	}

	public int getID_proveedor() {
		return ID_proveedor;
	}

	public void setID_proveedor(int iD_proveedor) {
		ID_proveedor = iD_proveedor;
	}

	@Override
	public String toString() {
		return "Proveedor [ID_proveedor=" + ID_proveedor + ", Nif_proveedor=" + Nif_proveedor + ", nombre_proveedor="
				+ nombre_proveedor + "]";
	}
}
