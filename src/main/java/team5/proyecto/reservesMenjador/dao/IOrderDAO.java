package team5.proyecto.reservesMenjador.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;
import team5.proyecto.reservesMenjador.dto.Users;

public interface IOrderDAO extends JpaRepository<Order, Integer>{
	public List<Order> getOrdersByCreatedOn(Date date);
	public List<Order> getOrdersByDeliveryOn(Date date);
	public List<Order> getOrdersByUser(Users user);
	
}
