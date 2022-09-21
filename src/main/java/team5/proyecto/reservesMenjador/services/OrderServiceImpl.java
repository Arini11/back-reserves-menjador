package team5.proyecto.reservesMenjador.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.IDishDAO;
import team5.proyecto.reservesMenjador.dao.IOrderDAO;
import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.DeliveryStatus;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;
import team5.proyecto.reservesMenjador.dto.Users;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderDAO orderDAO;
	
	@Autowired
	IDishService dishServ;
	
	@Autowired
	IUserService userServ;

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
		o.setCreatedOn(LocalDateTime.now().atOffset(ZoneOffset.UTC));
		o.setModifiedOn(LocalDateTime.now().atOffset(ZoneOffset.UTC));
		o.setDelivered(DeliveryStatus.P);
		
		System.out.println(LocalDateTime.now().atOffset(ZoneOffset.UTC));
		System.out.println(o.getCreatedOn());
		System.out.println(o.getCreatedOn().toInstant());
		
		
		Users u = userServ.findByUsername(o.getUser().getUsername());
		o.setUser(u);
		
		List<Dish> dishesList = new ArrayList<Dish>();
		for(Dish d : o.getDishes()) {
			Dish dd = dishServ.findById(d.getId());
			dishesList.add(dd);
		}
		o.setDishes(dishesList);

		return orderDAO.save(o);
	}
	
	@Override
	public Order updateOrder(Order o) {
		Order ordre = findById(o.getId());
        ordre.setModifiedOn(LocalDateTime.now().atOffset(ZoneOffset.UTC));
        ordre.setDeliveryOn(o.getDeliveryOn());
        
        ordre.getDishes().clear();

        ordre.getDishes().addAll(
				o.getDishes()
				.stream()
				.map(dish -> {
					Dish dd = dishServ.findById(dish.getId());
					dd.getOrders().add(ordre);
					return dd;
				}).collect(Collectors.toList()));
		
        return orderDAO.save(ordre);
	}
	
	@Override
	public Order updateStatus(Order o) {
        Order ordre = findById(o.getId());
        ordre.setModifiedOn(LocalDateTime.now().atOffset(ZoneOffset.UTC));
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

		o.setModifiedOn(LocalDateTime.now().atOffset(ZoneOffset.UTC));
		o.setDelivered(DeliveryStatus.C); // Canceled
		return orderDAO.save(o);
	}
/*
	private Date getDefaultDateTime() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt.getYear());
		System.out.println(ldt.getMonth());
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getYear());
		System.out.println(ldt.getYear());

		return null;
	}

*/
}
