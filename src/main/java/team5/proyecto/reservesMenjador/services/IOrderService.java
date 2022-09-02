package team5.proyecto.reservesMenjador.services;

import java.util.Date;
import java.util.List;

import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;
import team5.proyecto.reservesMenjador.dto.Users;

public interface IOrderService {

	public List<Order> getOrders();	

	public Order findById(int id);
	
	public List<Order> findByUser(Users user);
	
	public List<Order> findByCreatedOn(Date date);
	
	public List<Order> findByDeliveryOn(Date date);
	
	public List<Order> findByDelivered(char status);

	public Order addOrder(Order order);
	
	public Order updateOrder(Order order);

	public void deleteOrder(int id);
	
	
}
