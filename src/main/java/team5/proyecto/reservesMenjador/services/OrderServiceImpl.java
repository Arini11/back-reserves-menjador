package team5.proyecto.reservesMenjador.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
		// Quan "eliminem", guardem a modifiedOn la data d'eliminacio, i posem delivered a false
		Order o = orderById(id);

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = LocalDateTime.now().toString();
		Date d;
		try {
			d = f.parse(s);
		} catch (ParseException e) {
			// No petarà mai, però cal posar un try catch
			d = new Date();
			e.printStackTrace();
		}
		
		o.setModifiedOn(d);
		o.setDelivered('C'); // Canceled
	}

	@Override
	public List<Order> findByUser(Users user) {
		return orderDAO.getOrdersByUser(user);
	}

}
