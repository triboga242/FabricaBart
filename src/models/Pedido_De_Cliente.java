package models;

import java.sql.Date;
import java.util.ArrayList;

public class Pedido_De_Cliente {

	private int id_pedido;
	private Cliente cliente;
	private Date fecha;
	private ArrayList<Item> lista;

	public Pedido_De_Cliente(int id_pedido, Cliente cliente, Date fecha, ArrayList<Item> lista) {
		this.id_pedido = id_pedido;
		this.cliente = cliente;
		this.fecha = fecha;
		this.lista = lista;
	}

	public Pedido_De_Cliente(int id_pedido, Cliente cliente, Date fecha) {
		super();
		this.id_pedido = id_pedido;
		this.cliente = cliente;
		this.fecha = fecha;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		return "Pedido_De_Cliente [id_pedido=" + id_pedido + ", nif_cliente=" + cliente.toString() + ", fecha=" + fecha
				+ ", lista=" + lista + "]";
	}
}
