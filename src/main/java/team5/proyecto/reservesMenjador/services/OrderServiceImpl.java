package team5.proyecto.reservesMenjador.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.IOrderDAO;
import team5.proyecto.reservesMenjador.dto.DeliveryStatus;
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

	@Override
	public Order addOrder(Order o) {
		// Ignorem el que posa l'usuari a aquests tres camps i posem aixo:
		o.setCreatedOn(getCurrentDateTime());
		o.setModifiedOn(getDefaultDateTime());
		o.setDelivered(DeliveryStatus.P);
		
		// Per fer proves, treure-ho després pq sera l'usuari que hagi fer la ordre
		Users u = new Users();
		u.setUsername("Cliente1");
		if(o.getUser().getUsername()==null) o.setUser(u);
		if(o.getDeliveryOn()==null) o.setDeliveryOn(getDeliveryDate());
		return orderDAO.save(o);
	}
	
	@Override
	public Order updateOrder(Order o) {
		Order ordre = findById(o.getId());
        ordre.setModifiedOn(getCurrentDateTime());
        ordre.setDeliveryOn(o.getDeliveryOn());
        return orderDAO.save(ordre);
	}
	
	@Override
	public Order updateStatus(Order o) {
        Order ordre = findById(o.getId());
        ordre.setModifiedOn(getCurrentDateTime());
        ordre.setDelivered(o.getDelivered());
        return orderDAO.save(ordre);
    }
	
	@Override
	public Order addDishesToOrder(Order o) {
		return orderDAO.save(o);
	}

	@Override
	public Order deleteOrder(int id) {
		//Quan "eliminem", guardem a modifiedOn la data d'eliminacio, i posem delivered a C (cancelled)
		Order o = findById(id);

		o.setModifiedOn(getCurrentDateTime());
		o.setDelivered(DeliveryStatus.C); // Canceled
		return orderDAO.save(o);
	}
	
	private Date getCurrentDateTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String s = LocalDateTime.now().toString();
		Date d;
		try {
			d = f.parse(s);
		} catch (ParseException e) {
			System.out.println("HA PETAT");
			d = new Date();
			e.printStackTrace();
		}
		return d;
	}
	
	private Date getDefaultDateTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = "9999-12-31 00:00:00";
		Date d;
		try {
			d = f.parse(s);
		} catch (ParseException e) {
			// No petarà mai, però cal posar un try catch
			d = new Date();
			e.printStackTrace();
		}
		return d;
	}
	
	private Date getDeliveryDate() {
		Date d = getCurrentDateTime();
		d.setHours(d.getHours()+2);
		return d;
	}


}
