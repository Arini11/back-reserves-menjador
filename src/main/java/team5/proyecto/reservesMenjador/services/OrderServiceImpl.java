package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.IOrderDAO;
import team5.proyecto.reservesMenjador.dto.Order;
import team5.proyecto.reservesMenjador.dto.Users;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderDAO orderDAO;

	@Override
	public List<Order> getOrders() {
		return orderDAO.findAll();
	}

	@Override
	public Order saveOrder(Order order) {
		return orderDAO.save(order);
	}

	@Override
	public Order orderById(int id) {
		return orderDAO.findById(id).orElse(null);
	}

	@Override
	public Order updateOrder(Order order) {
		return orderDAO.save(order);
	}

	@Override
	public void deleteOrder(int id) {
		orderDAO.deleteById(id);
	}

	@Override
	public List<Order> findByUser(Users user) {
		return orderDAO.getOrdersByUser(user);
	}

}
