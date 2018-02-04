package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import models.Cliente;
import models.Detalle_Encargo;
import models.Detalle_Pedido;
import models.Encargo_A_Proveedor;
import models.Item;
import models.Pedido_De_Cliente;
import models.Producto;
import models.Proveedor;

public class Ejercicios {

	public static ArrayList<Cliente> clientes = new ArrayList<>();
	public static ArrayList<Proveedor> proveedores = new ArrayList<>();
	public static ArrayList<Pedido_De_Cliente> pedidos = new ArrayList<>();
	public static ArrayList<Encargo_A_Proveedor> encargos = new ArrayList<>();
	public static ArrayList<Detalle_Pedido> detallePedidos = new ArrayList<>();
	public static ArrayList<Detalle_Encargo> detallesEncargos = new ArrayList<>();
	public static ArrayList<Producto> productos = new ArrayList<>();
	public static String auxiliar;

	// 1. Muestra un listado de todos los clientes.
	public static void ejer1(TestJDBC test, ResultSet rs) {
		// clientes = new ArrayList<>();
		// clientes = recogerClientes(test, rs);
		for (Cliente c : clientes) {
			System.out.println(c.toString());
		}
		System.out.println("Ejercicio 1 ejecutado");
	}

	// 2. Muestra un listado de todos los proveedores.
	public static void ejer2(TestJDBC test, ResultSet rs) {
		// proveedores = new ArrayList<>();
		// proveedores = recogerProveedores(test, rs);
		for (Proveedor p : proveedores) {
			System.out.println(p.toString());
		}
		System.out.println("Ejercicio 2 ejecutado");
	}

	// 3. Muestra un listado de todos encargos realizados en los últimos doce meses.
	public static void ejer3(TestJDBC test, ResultSet rs, int meses) {
		// encargos = new ArrayList<>();
		// encargos = recogerEncargos(test, rs);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -meses);
		Date prevYear = cal.getTime();
		for (Encargo_A_Proveedor e : encargos) {
			if (e.getFecha().after(prevYear)) {
				System.out.println(e.toString());
			}
		}
		System.out.println("Ejercicio 3 ejecutado");
	}

	// 4. Muestra un listado con todos los pedidos realizados por un determinado
	// cliente.
	public static void ejer4(TestJDBC test, ResultSet rs, Cliente cliente) {
		// pedidos = new ArrayList<>();
		// pedidos = recogerPedidos(test, rs);
		for (Pedido_De_Cliente p : pedidos) {
			if (p.getCliente().getNif_cliente().equals(cliente.getNif_cliente())) {
				System.out.println(p.toString());
			}
		}
		System.out.println("Ejercicio 4 ejecutado");
	}

	// 5. Determina, si existe algún cliente que también sea proveedor.
	public static ArrayList<Cliente> ejer5(TestJDBC test, ResultSet rs) {
		ArrayList<Cliente> c = new ArrayList<>();
		// clientes = new ArrayList<>();
		// clientes = recogerClientes(test, rs);
		// proveedores = new ArrayList<>();
		// proveedores = recogerProveedores(test, rs);
		// ArrayList<String> cifs = new ArrayList<>();
		for (int i = 0; i < clientes.size(); i++) {
			for (int j = 0; j < proveedores.size(); j++) {
				if (clientes.get(i).getNif_cliente().equals(proveedores.get(j).getNif_proveedor())) {
					c.add(clientes.get(i));
					System.out.println(clientes.get(i));
				}
			}
		}
		return c;
	}

	// 6. Determina cual es el producto más vendido.
	public static void ejer6(TestJDBC test, ResultSet rs) {
		// detallePedidos = new ArrayList<>();
		// detallePedidos = recogerDetallesPedidos(test, rs);
		int maxCant = 0;
		int maxId = 0;
		Map<Integer, Integer> cantidades = new HashMap<>();
		for (Detalle_Pedido d : detallePedidos) {
			if (cantidades.get(d.id_producto) == null) {
				cantidades.put(d.id_producto, d.getCantidad());
			} else {
				Integer valor = cantidades.get(d.id_producto);
				valor += d.getCantidad();
				cantidades.put(d.getId_producto(), valor);
				if (valor > maxCant) {
					maxCant = valor;
					maxId = d.getId_producto();
				}
			}
		}
		for (Producto p :productos) {
			if (p.getID_producto() == maxId) {
				System.out.println(p.toString());
			}
		}
		System.out.println("Producto " + maxId + " se ha vendido " + maxCant + " veces.");
		System.out.println("Ejercicio 6 ejecutado");
	}

	// 7. Muestra un resumen de todos los pedidos. Para ello se pide
	public static void ejer7(TestJDBC test, ResultSet rs) {
		// pedidos = new ArrayList<>();
		// pedidos = recogerPedidos(test, rs);
		for (Pedido_De_Cliente p : pedidos) {
			System.out.println(p.toString());
		}

		System.out.println("Ejercicio 7 ejecutado");
	}

	// 8. Determina cual es el cliente que mayor volumen de pedidos ha generado.
	public static void ejer8(TestJDBC test, ResultSet rs) {
		// pedidos = new ArrayList<>();
		// pedidos = recogerPedidos(test, rs);
		Map<String, Integer> cantidadPedidos = new HashMap<>();
		for (Pedido_De_Cliente p : pedidos) {
			if (cantidadPedidos.get(p.getCliente().getNif_cliente()) == null) {
				cantidadPedidos.put(p.getCliente().getNif_cliente(), 1);
			} else {
				Integer valor = cantidadPedidos.get(p.getCliente().getNif_cliente());
				valor++;
				cantidadPedidos.put(p.getCliente().getNif_cliente(), valor);
			}
		}
		sortByValue(cantidadPedidos, "pedidos");
		for (Cliente c:clientes) {
			if (c.getNif_cliente().equals(auxiliar)) {
				System.out.println(c.toString());
			}
		}		
		System.out.println("Ejercicio 8 ejecutado");
	}

	// -BART: Obtén el cliente que haya gastado mas dinero en comprar productos y
	// que a su
	// mismo tiempo es proveedor.
	public static void ejerE1(TestJDBC test, ResultSet rs) {
		// pedidos=new ArrayList<>();
		// pedidos = recogerPedidos(test, rs);
		// clientes = new ArrayList<>();
		// clientes = recogerClientes(test, rs);
		// proveedores = new ArrayList<>();
		// proveedores = recogerProveedores(test, rs);

		ArrayList<Cliente> clienteYproveedor = new ArrayList<>();
		clienteYproveedor = ejer5(test, rs);

		Map<String, Float> valorPedidos = new HashMap<>();
		for (Cliente c : clienteYproveedor) {
			for (Pedido_De_Cliente p : pedidos) {
				if (p.getCliente().getNif_cliente().equals(c.getNif_cliente())) {
					for (Item i : p.getLista()) {
						if (valorPedidos.get(p.getCliente().getNif_cliente()) == null) {
							valorPedidos.put(p.getCliente().getNif_cliente(),
									i.getProducto().getPrecio_vender() * i.getCantidad());
						} else {
							Float valor = valorPedidos.get(p.getCliente().getNif_cliente());
							valor += i.getProducto().getPrecio_vender() * i.getCantidad();
							valorPedidos.put(p.getCliente().getNif_cliente(), valor);
						}
					}
				}
			}
		}
		sortByValue(valorPedidos, "euros en pedidos");
		System.out.println("Ejercicio E1 ejecutado");
	}

	// Crea una función que devuelva todos los pedidos asociados a un cliente
	// (pasado por parámetro)
	// cuyas fechas de pedido estarán entre dos fechas pasadas por parámetros. Si la
	// consulta
	// no encuentra resultados, se mostrará un mensaje informativo.
	public static ArrayList<Pedido_De_Cliente> ejerE2(TestJDBC test, ResultSet rs, Cliente c, Date desde, Date hasta) {

		// pedidos=new ArrayList<>();
		// pedidos = recogerPedidos(test, rs);
		ArrayList<Pedido_De_Cliente> pedidosQueCumplen = new ArrayList<>();
		boolean existe = false;
		for (Pedido_De_Cliente p : pedidos) {
			if (String.valueOf(p.getCliente().getID_cliente()).equals(String.valueOf(c.getID_cliente()))) {
				if (p.getFecha().before(desde) && p.getFecha().after(hasta)) {
					pedidosQueCumplen.add(p);
					System.out.println(p.toString());
					existe = true;
				}
			}
		}
		if (!existe)
			System.out.println("No hay pedido con esas caracteristicas.");

		System.out.println("Ejercicio E2 ejecutado");
		return pedidosQueCumplen;
	}

	// Muestra el nombre de los clientes cuyo nombre contenga la ’a’ y el código del
	// pedido sea menor que 2, o la cantidad del pedido sea exactamente 23 y el
	// código del pedido mayor que 1. Ademas la consulta debe estar ordenada por el
	// precio de
	// ventas.

	@SuppressWarnings("rawtypes")
	public static void ejerE3(TestJDBC test, ResultSet rs) {
		 clientes = new ArrayList<>();
		 clientes = recogerClientes(test, rs);
		ArrayList<Cliente> clientesQueCumplen = new ArrayList<>();
		for (Cliente c : clientes) {
			if (c.getNombre_cliente().contains("e")) {
				clientesQueCumplen.add(c);
			}
		}
		if (clientesQueCumplen.isEmpty()) {
			System.out.println("No existe cliente que cumpla los requisitos 1");
		} else {
			ArrayList<Pedido_De_Cliente> pedidos = recogerPedidos(test, rs);

			Pedido_De_Cliente pedidoMenor2 = null;

			Map<String, Integer> cantidadPedidos = new HashMap<>();
			for (Pedido_De_Cliente p : pedidos) {

				if (p.getId_pedido() == 1) {
					pedidoMenor2 = p;
				} else {
					if (cantidadPedidos.get(p.getCliente().getNif_cliente()) == null) {
						cantidadPedidos.put(p.getCliente().getNif_cliente(), 1);
					} else {
						Integer valor = cantidadPedidos.get(p.getCliente().getNif_cliente());
						valor++;
						cantidadPedidos.put(p.getCliente().getNif_cliente(), valor);
					}
				}
			}
			Iterator it = cantidadPedidos.entrySet().iterator();
			int clienteID = 0;
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				if ((int) e.getValue() == 23) {
					clienteID = (int) e.getKey();
				}
			}
			int cont = clientesQueCumplen.size();
			int control = 0;
			for (Cliente c : clientesQueCumplen) {
				if (c.getID_cliente() == clienteID) {
					System.out.println(c.toString());
				} else if (c.getNif_cliente().equals(pedidoMenor2.getCliente().getNif_cliente())) {
					System.out.println(c.toString());
				} else {
					control++;
				}
			}
			if (control == cont)
				System.out.println("No existe cliente que cumpla los requisitos 2");
		}
		System.out.println("Ejercicio E3 ejecutado");
	}

	// MAPEAR A CLASES
	public static ArrayList<Cliente> recogerClientes(TestJDBC test, ResultSet rs) {
		ArrayList<Cliente> cs = new ArrayList<>();
		rs = test.execSQL("select * from cliente");
		try {
			while (rs.next()) {
				cs.add(new Cliente(rs.getInt(1), rs.getString(3).toString(), rs.getString(2).toString()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for (Cliente c:cs) {
		// System.out.println(c.toString());
		// }
		return cs;

	}

	public static ArrayList<Proveedor> recogerProveedores(TestJDBC test, ResultSet rs) {
		ArrayList<Proveedor> cs = new ArrayList<>();
		rs = test.execSQL("select * from Proveedor");
		try {
			while (rs.next()) {
				cs.add(new Proveedor(rs.getInt(1), rs.getString(3).toString(), rs.getString(2).toString()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for (Proveedor c:cs) {
		// System.out.println(c.toString());
		// }
		return cs;
	}

	public static ArrayList<Producto> recogerProductos(TestJDBC test, ResultSet rs) {
		ArrayList<Producto> cs = new ArrayList<>();
		rs = test.execSQL("select * from Producto");
		try {
			while (rs.next()) {
				cs.add(new Producto(rs.getInt(1), rs.getString(2).toString(), rs.getFloat(3), rs.getFloat(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for (Producto c:cs) {
		// System.out.println(c.toString());
		// }
		return cs;
	}

	public static ArrayList<Pedido_De_Cliente> recogerPedidos(TestJDBC test, ResultSet rs) {
		ArrayList<Pedido_De_Cliente> al = new ArrayList<>();
		ArrayList<Cliente> alc = recogerClientes(test, rs);

		rs = test.execSQL("select * from pedido");
		try {
			while (rs.next()) {
				ArrayList<Item> items = recogerItemsPedidos(test, rs, rs.getInt(1));
				for (Cliente c : alc) {
					if (String.valueOf(c.getID_cliente()).equals(rs.getString(2))) {
						al.add(new Pedido_De_Cliente(rs.getInt(1), c, rs.getDate(3), items));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for (Pedido_De_Cliente c : al) {
		// System.out.println(c.toString());
		// }

		return al;
	}

	public static ArrayList<Encargo_A_Proveedor> recogerEncargos(TestJDBC test, ResultSet rs) {
		ArrayList<Encargo_A_Proveedor> al = new ArrayList<>();
		ArrayList<Item> items;
		ArrayList<Proveedor> alp = recogerProveedores(test, rs);
		rs = test.execSQL("select * from encargo");
		try {
			while (rs.next()) {
				items = new ArrayList<>();
				items = recogerItemsEncargos(test, rs, rs.getInt(1));
				for (Proveedor p : alp) {
					if (String.valueOf(p.getID_proveedor()).equals(rs.getString(2))) {
						al.add(new Encargo_A_Proveedor(rs.getInt(1), p, rs.getDate(3), items, rs.getInt(4)));
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for (Encargo_A_Proveedor c : al) {
		// System.out.println(c.toString());
		// }
		return al;
	}

	public static ArrayList<Item> recogerItemsPedidos(TestJDBC test, ResultSet rs, int id) {
		ArrayList<Item> items = new ArrayList<>();
		ArrayList<Producto> productos = recogerProductos(test, rs);
		rs = test.execSQL("select * from detalle_pedidos;");
		try {
			while (rs.next()) {
				if (rs.getInt(1) == id) {
					for (Producto p : productos) {
						if (p.getID_producto() == rs.getInt(2)) {
							items.add(new Item(p, rs.getInt(3)));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public static ArrayList<Item> recogerItemsEncargos(TestJDBC test, ResultSet rs, int id) {
		ArrayList<Item> items = new ArrayList<>();
		ArrayList<Producto> productos = recogerProductos(test, rs);
		rs = test.execSQL("select * from detalle_encargo;");
		try {
			while (rs.next()) {
				if (rs.getInt(1) == id) {
					for (Producto p : productos) {
						if (p.getID_producto() == rs.getInt(2)) {
							items.add(new Item(p, rs.getInt(3)));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public static ArrayList<Detalle_Pedido> recogerDetallesPedidos(TestJDBC test, ResultSet rs) {
		ArrayList<Detalle_Pedido> al = new ArrayList<>();

		rs = test.execSQL("select * from detalle_pedidos");
		try {
			while (rs.next()) {

				al.add(new Detalle_Pedido(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for (Detalle_Pedido c : al) {
		// System.out.println(c.toString());
		// }

		return al;
	}

	public static ArrayList<Detalle_Encargo> recogerDetallesEncargos(TestJDBC test, ResultSet rs) {
		ArrayList<Detalle_Encargo> al = new ArrayList<>();

		rs = test.execSQL("select * from detalle_encargo");
		try {
			while (rs.next()) {

				al.add(new Detalle_Encargo(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for (Detalle_Encargo c : al) {
		// System.out.println(c.toString());
		// }

		return al;
	}

	// Ordenar maps
	@SuppressWarnings("rawtypes")
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, String info) {
		LinkedList<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		Iterator it = result.entrySet().iterator();
		int aux = result.size();
		int cont = 0;
		while (it.hasNext()) {
			cont++;
			Map.Entry e = (Map.Entry) it.next();
			if (cont == aux)
				System.out.println("Cliente " + e.getKey() + " con " + e.getValue() + " " + info);
				auxiliar=(String) e.getKey();
		}
		return result;
	}

	public static void recogerTodo(TestJDBC test, ResultSet rs) {
		clientes = recogerClientes(test, rs);
		proveedores = recogerProveedores(test, rs);
		pedidos = recogerPedidos(test, rs);
		encargos = recogerEncargos(test, rs);
		detallePedidos = recogerDetallesPedidos(test, rs);
		detallesEncargos = recogerDetallesEncargos(test, rs);
		productos = recogerProductos(test, rs);
	}
}
