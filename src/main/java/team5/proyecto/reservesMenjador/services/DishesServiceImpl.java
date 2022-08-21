package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.IDishesDAO;
import team5.proyecto.reservesMenjador.dto.Dish;

@Service
public class DishesServiceImpl implements IDishesService {

	@Autowired
	private IDishesDAO iDishesDao;

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
	public Dish findDishById(int id) {
		return iDishesDao.findById(id).get();
	}

	@Override
	public void deleteDish(int id) {
		iDishesDao.deleteById(id);
	}

}
