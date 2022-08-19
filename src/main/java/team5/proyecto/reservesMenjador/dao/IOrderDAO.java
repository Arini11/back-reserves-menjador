package team5.proyecto.reservesMenjador.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Order;

public interface IOrderDAO extends JpaRepository<Order, Integer>{
	public List<Order> getOrdersByDate(Date date);
}
