package team5.proyecto.reservesMenjador.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;

public interface IDishDAO extends JpaRepository<Dish,Integer>{

	public Dish findByName(String name);	
	public List<Dish> findByPopularity(int popularity);	
	public List<Dish> findByOrders(Order order);	
	public List<Dish> findByCategories(Category category);
	public List<Dish> findByStatus(boolean status);
	
}