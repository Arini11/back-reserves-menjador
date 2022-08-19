package team5.proyecto.reservesMenjador.services;

import java.util.List;

import team5.proyecto.reservesMenjador.dto.Order;

public interface IOrderService {

	public List<Order> getOrders();

	public Order saveOrder(Order order);

	public Order orderXId(int id);

	public Order updateOrder(Order order);

	public void deleteOrder(int id);
}
