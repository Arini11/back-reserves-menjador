package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.IDishesDAO;
import team5.proyecto.reservesMenjador.dto.Dishes;

@Service 
public class DishesServiceImpl implements IDishesService{
	
	@Autowired
	IDishesDAO iDishesDao;

	@Override
	public List<Dishes> getDishes() {
		return iDishesDao.findAll();
	}

	@Override
	public Dishes saveDish(Dishes dish) {
		return iDishesDao.save(dish);
	}

	@Override
	public Dishes dishById(int id) {
		return iDishesDao.findById(id).get();
	}

	@Override
	public void deleteDish(int id) {
		iDishesDao.deleteById(id);
	}

}
