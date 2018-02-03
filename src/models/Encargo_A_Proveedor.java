package models;

import java.sql.Date;
import java.util.ArrayList;

public class Encargo_A_Proveedor {

	private int id_encargo;
	private String nif_proveedor;
	private Date fecha;
	private ArrayList<Item> lista;
	private int B;

	public Encargo_A_Proveedor(int id_encargo, String nif_proveedor, Date fecha, ArrayList<Item> lista,
			int b) {
		this.id_encargo = id_encargo;
		this.nif_proveedor = nif_proveedor;
		this.fecha = fecha;
		this.lista = lista;
		this.B = b;
	}

	public Encargo_A_Proveedor(int id_encargo, String nif_proveedor, Date fecha, int b) {
		super();
		this.id_encargo = id_encargo;
		this.nif_proveedor = nif_proveedor;
		this.fecha = fecha;
		B = b;
	}

	
	public int getId_encargo() {
		return id_encargo;
	}

	public void setId_encargo(int id_encargo) {
		this.id_encargo = id_encargo;
	}

	public String getNif_proveedor() {
		return nif_proveedor;
	}

	public void setNif_proveedor(String nif_proveedor) {
		this.nif_proveedor = nif_proveedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Item> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Item> lista) {
		this.lista = lista;
	}

	public int getB() {
		return B;
	}

	public void setB(int b) {
		B = b;
	}

	@Override
	public String toString() {
		return "Encargo_A_Proveedor [id_encargo=" + id_encargo + ", nif_proveedor=" + nif_proveedor
				+ ", fecha=" + fecha + ", lista=" + lista.toString() + ", B=" + B + "]";
	}
}
