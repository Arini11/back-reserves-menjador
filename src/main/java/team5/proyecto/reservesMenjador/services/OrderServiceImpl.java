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
	public Order findById(int id) {
		return orderDAO.findById(id).orElse(new Order());
	}
	
	@Override
	public List<Order> findByUser(Users user) {
		return orderDAO.findByUser(user);
	}

	@Override
	public List<Order> findByCreatedOn(Date date) {		
		return orderDAO.findByCreatedOn(date);
	}

	@Override
	public List<Order> findByDeliveryOn(Date date) {
		return orderDAO.findByDeliveryOn(date);
	}

	@Override
	public List<Order> findByDelivered(char status) {		
		return orderDAO.findByDelivered(status);
	}

	@Override //save or update
	public Order saveOrder(Order order) {
		//la fecha de creacion se podria establecer aqui igual que en delete, new Date() tamb se podria hacer en el constructor de order
		//this.createdOn = new Date() y no haria falta try/catch
		//la fecha de modificacion, comprobar cn un if --> si es nuevaorden ponerla a null sino newDate() (mirar orderController metodo put - como
		//alternativa
		return orderDAO.save(order);
	}
	
	@Override
	public void deleteOrder(int id) {
		//Quan "eliminem", guardem a modifiedOn la data d'eliminacio, i posem delivered a C (cancelled)
		Order o = findById(id);

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

}
