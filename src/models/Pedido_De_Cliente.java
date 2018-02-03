package models;

import java.sql.Date;
import java.util.ArrayList;

public class Pedido_De_Cliente {

	private int id_pedido;
	private String nif_cliente;
	private Date fecha;
	private ArrayList<Item> lista;

	public Pedido_De_Cliente(int id_pedido, String nif_cliente, Date fecha, ArrayList<Item> lista) {
		this.id_pedido = id_pedido;
		this.nif_cliente = nif_cliente;
		this.fecha = fecha;
		this.lista = lista;
	}

	public Pedido_De_Cliente(int id_pedido, String nif_cliente, Date fecha) {
		super();
		this.id_pedido = id_pedido;
		this.nif_cliente = nif_cliente;
		this.fecha = fecha;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getNif_cliente() {
		return nif_cliente;
	}

	public void setNif_cliente(String nif_cliente) {
		this.nif_cliente = nif_cliente;
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

	@Override
	public String toString() {
		return "Pedido_De_Cliente [id_pedido=" + id_pedido + ", nif_cliente=" + nif_cliente
				+ ", fecha=" + fecha + ", lista=" + lista + "]";
	}
}
