package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.IDishDAO;
import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;

@Service
public class DishServiceImpl implements IDishService {

	@Autowired
	private IDishDAO iDishesDao;

	@Override
	public List<Dish> getDishes() {
		return iDishesDao.findAll();
	}

	@Override
	public Dish saveDish(Dish dish) {		
		//FIXME: Controlar duplicidad nombre
		return iDishesDao.save(dish);
	}

	@Override
	public Dish findById(int id) {
		return iDishesDao.findById(id).orElse(null);
	}

	@Override
	public void deleteDish(int id) {
		iDishesDao.deleteById(id);
	}

	@Override
	public Dish findByName(String name) {
		return iDishesDao.findByName(name);
	}

	@Override
	public List<Dish> findByPopularity(int popularity) {
		return iDishesDao.findByPopularity(popularity);
	}

	@Override
	public List<Dish> findByOrders(Order order) {
		return iDishesDao.findByOrders(order);
	}

	@Override
	public List<Dish> findByCategories(Category category) {
		return iDishesDao.findByCategories(category);
	}

}
