package team5.proyecto.reservesMenjador.services;

import java.util.List;

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;

public interface IDishService {
	//CRUD
	public List<Dish> getDishes(); 
	
	public Dish findById(int id);
	
	public Dish findByName(String name);
	
	public List<Dish> findByPopularity(int popularity);
	
	public List<Dish> findByOrders(Order order); //va junto con tener la @JoinTable order_dishes
	
	public List<Dish> findByCategories(Category category);

	public String saveDish(Dish dish); //CREATE || UPDATE

	public void deleteDish(int id);	

}