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
	private IDishDAO iDishDao;

	@Override
	public List<Dish> getDishes() {
		return iDishDao.findAll();
	}
	
	@Override
	public Dish findById(int id) {
		return iDishDao.findById(id).orElse(null);
	}
	
	@Override
	public Dish findByName(String name) {
		return iDishDao.findByName(name);
	}

	@Override
	public List<Dish> findByPopularity(int popularity) {
		return iDishDao.findByPopularity(popularity);
	}

	@Override
	public List<Dish> findByOrders(Order order) {
		return iDishDao.findByOrders(order);
	}

	@Override
	public List<Dish> findByCategories(Category category) {
		return iDishDao.findByCategories(category);
	}

	@Override
	public String saveDish(Dish dish) {		
	// validar datos que entraran por body, que no se repita el nombre
		boolean exists = false;

		for (Dish iterateDish : getDishes()) {
			if (iterateDish.getName().equals(dish.getName())) {
				exists = true;
			}
		}
		if (!exists) {
			iDishDao.save(dish);
			return "Plato guardado!";
		}
		return "El plato ya existe!";
	}

	@Override
	public void deleteDish(int id) {
		Dish dish = findById(id);
		dish.setStatus(false);
	}

}
