package main;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import models.Cliente;

public class TestJDBC implements Serializable {

	// init database constants
	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/fabrica_bart";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	private static final String MAX_POOL = "250"; // set your own limit

	private static final long serialVersionUID = 4971075361003058584L;

	// init connection object
	private Connection connection;
	// init properties object
	private Properties properties;

	// create properties
	private Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", USERNAME);
			properties.setProperty("password", PASSWORD);
			properties.setProperty("MaxPooledStatements", MAX_POOL);
			properties.setProperty("database_name", "fabrica_bart");
		}
		return properties;
	}

	// Conecta a la base de datos
	public Connection connect() {
		if (connection == null) {
			try {
				Class.forName(DATABASE_DRIVER);
				connection = DriverManager.getConnection(DATABASE_URL, getProperties());
			} catch (ClassNotFoundException | SQLException e) {
				// Java 7+
				e.printStackTrace();
			}
		}
		return connection;
	}

	// "Desconecta" de la bd
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet execSQL(String sql) {

		connect();
		ResultSet rs = null;

		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();

			rs = statement.getResultSet();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// disconnect();
		}

		return rs;

	}

	public static String user_aleatorio() {

		char[] elementos = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		char[] conjunto = new char[8];

		for (int i = 0; i < 8; i++) {
			int el = (int) (Math.random() * 26);
			conjunto[i] = (char) elementos[el];
		}
		return new String(conjunto);
	}

	public static int int_aleatorio(int N, int M) {
		int valorEntero = (int) Math.floor(Math.random() * (N - M + 1) + M); // Valor entre M y N, ambos incluidos.

		return valorEntero;
	}

	// RELLENAR PROGRAMATICAMENTE LA BBDD
	public static void insertarCliente(TestJDBC test, ResultSet rs) {
		for (int i = 0; i < 11; i++) {
			rs = test.execSQL("insert into cliente(idcliente, nombre, idf) \n" + "values ('" + int_aleatorio(1000, 9999)
					+ "', '" + user_aleatorio() + "', '" + int_aleatorio(100000, 999999) + "');");
		}
	}

	public static void insertarProveedor(TestJDBC test, ResultSet rs) {
		for (int i = 0; i < 11; i++) {
			rs = test.execSQL(
					"insert into proveedor(idproveedor, nombre, idf) \n" + "values ('" + int_aleatorio(1000, 9999)
							+ "', '" + user_aleatorio() + "', '" + int_aleatorio(100000, 999999) + "');");
		}
	}

	public static void insertarProducto(TestJDBC test, ResultSet rs) {
		for (int i = 0; i < 11; i++) {
			rs = test.execSQL("insert into producto(idproducto, nombre, precio_compra, precio_venta) \n" + "values ('"
					+ int_aleatorio(1000, 9999) + "', '" + user_aleatorio() + "', '" + int_aleatorio(100000, 999999)
					+ "', '" + int_aleatorio(10, 9999) + "');");
		}
	}

	// PARA PODER RELLENAR LA BBDD PARA MAPEAR USAR LOS DE LA CLASE EJERCICIOS
	public static ArrayList<String> recogerClientes(TestJDBC test, ResultSet rs) {
		ArrayList<String> clientes = new ArrayList<>();
		rs = test.execSQL("(select idcliente from cliente);");
		try {
			while (rs.next()) {
				clientes.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public static ArrayList<String> recogerProveedores(TestJDBC test, ResultSet rs) {
		ArrayList<String> proveedores = new ArrayList<>();
		rs = test.execSQL("(select idproveedor from proveedor);");
		try {
			while (rs.next()) {
				proveedores.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proveedores;
	}

	public static ArrayList<String> recogerProductos(TestJDBC test, ResultSet rs) {
		ArrayList<String> productos = new ArrayList<>();
		rs = test.execSQL("(select idproducto from producto);");
		try {
			while (rs.next()) {
				productos.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}

	public static ArrayList<String> recogerPedidos(TestJDBC test, ResultSet rs) {
		ArrayList<String> productos = new ArrayList<>();
		rs = test.execSQL("(select idpedido from pedido);");
		try {
			while (rs.next()) {
				productos.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}

	public static ArrayList<String> recogerEncargos(TestJDBC test, ResultSet rs) {
		ArrayList<String> productos = new ArrayList<>();
		rs = test.execSQL("(select idencargo from encargo);");
		try {
			while (rs.next()) {
				productos.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}

	public static void insertarPedidos(TestJDBC test, ResultSet rs) {

		ArrayList<String> clientes = recogerClientes(test, rs);

		int numProductos;
		int idPedido;
		String fecha;

		// Recorro la lista
		for (String s : clientes) {
			// creo cantidad de pedidos aleatrios
			numProductos = int_aleatorio(0, 3);
			// Voy añadiendo x pedidos por cliente
			for (int i = 0; i < numProductos; i++) {
				// Creo fecha aleatoria
				fecha = int_aleatorio(1999, 2018) + "-" + int_aleatorio(1, 12) + "-" + int_aleatorio(1, 31);
				// Creo el id de pedido aleatorio
				idPedido = int_aleatorio(10000, 99999);
				rs = test.execSQL("insert into pedido(idpedido, id_cliente, fecha) values(" + idPedido + ", " + s + ", "
						+ "'" + fecha + "'" + ");");

				System.out.println("Insertando pedido");
			}
		}
	}

	public static void insertarDetallesPedido(TestJDBC test, ResultSet rs) {
		ArrayList<String> productos = recogerProductos(test, rs);
		ArrayList<String> pedidos = recogerPedidos(test, rs);

		int indice;
		int numProductos;

		for (String s : pedidos) {
			// creo cantidad de productos aleatrios
			numProductos = int_aleatorio(0, 6);
			for (int i = 0; i < numProductos; i++) {
				// creo indice aleatorio para elegir un producto
				indice = int_aleatorio(-1, productos.size());
				rs = test.execSQL("insert into detalle_pedidos(id_dp_pedido, id_dp_producto, cantidad) values(" + s
						+ ", " + productos.get(indice) + ", " + int_aleatorio(1, 10) + ");");
			}
		}
	}

	public static void insertarEncargos(TestJDBC test, ResultSet rs) {

		ArrayList<String> proveedores = recogerProveedores(test, rs);

		int numProductos;
		int idPedido;
		String fecha;
		int iva;
		// Recorro la lista
		for (String s : proveedores) {
			// creo cantidad de pedidos aleatrios
			numProductos = int_aleatorio(0, 3);
			// Añado x encargos por proveedor
			for (int i = 0; i < numProductos; i++) {
				// Creo fecha aleatoria
				fecha = int_aleatorio(1999, 2018) + "-" + int_aleatorio(1, 12) + "-" + int_aleatorio(1, 31);
				// iva aleatorio
				iva = int_aleatorio(-1, 1);
				// Creo el id de pedido aleatorio
				idPedido = int_aleatorio(10000, 99999);
				rs = test.execSQL("insert into encargo(idencargo, id_proveedor, fecha, iva) values(" + idPedido + ", "
						+ s + ", " + "'" + fecha + "'," + iva + ");");
				System.out.println("Insertando encargo");
			}
		}
	}

	public static void insertarDetallesEncargos(TestJDBC test, ResultSet rs) {
		ArrayList<String> productos = recogerProductos(test, rs);
		ArrayList<String> encargos = recogerEncargos(test, rs);

		int indice;
		int numProductos;

		for (String s : encargos) {
			// creo cantidad de productos aleatrios
			numProductos = int_aleatorio(0, 6);
			for (int i = 0; i < numProductos; i++) {
				// creo indice aleatorio para elegir un producto
				indice = int_aleatorio(0, productos.size());
				rs = test.execSQL("insert into detalle_encargo(id_encargo, id_producto, cantidad) values(" + s + ", "
						+ productos.get(indice) + ", " + int_aleatorio(0, 10) + ");");
			}
		}
	}

	public static void main(String[] args) {

		TestJDBC test = new TestJDBC();

		try {
			test.connect();
			ResultSet rs = null;
			//
			// insertarCliente(test, rs);
			// insertarProveedor(test, rs);
			// insertarProducto(test, rs);
			// insertarPedidos(test, rs);
			// insertarDetallesPedido(test, rs);
			// insertarEncargos(test, rs);
			// insertarDetallesEncargos(test, rs);
			
			Cliente c = new Cliente(1, "123A", "Pedro");			
			Calendar cal = Calendar.getInstance();
			Date hoy = cal.getTime();
			cal.add(Calendar.YEAR, -1); 
			Date anioPasado = cal.getTime();	

			Ejercicios.recogerTodo(test, rs);
			System.out.println("Ejercicio 1");
			Ejercicios.ejer1(test, rs);
			System.out.println("Ejercicio 2");
			Ejercicios.ejer2(test, rs);
			System.out.println("Ejercicio 3");
			Ejercicios.ejer3(test, rs, 12);
			System.out.println("Ejercicio 4");
			Ejercicios.ejer4(test, rs,c);
			System.out.println("Ejercicio 5");
			Ejercicios.ejer5(test, rs);
			System.out.println("Ejercicio 5 ejecutado");
			System.out.println("Ejercicio 6");
			Ejercicios.ejer6(test, rs);
			System.out.println("Ejercicio 7");
			Ejercicios.ejer7(test, rs);
			System.out.println("Ejercicio 8");
			Ejercicios.ejer8(test, rs);
			System.out.println("Ejercicio E1");
			Ejercicios.ejerE1(test, rs);
			System.out.println("Ejercicio E2");
			Ejercicios.ejerE2(test, rs, c, hoy, anioPasado);
			System.out.println("Ejercicio E3");
			Ejercicios.ejerE3(test, rs);
		} finally {
			 test.disconnect();
		}
	}
}
