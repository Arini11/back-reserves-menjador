package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.ICategories_dishesDAO;
import team5.proyecto.reservesMenjador.dto.Categories_dishes;

@Service
public class Categ_DishesServiceImpl implements ICateg_DishesService{
	
	@Autowired
	ICategories_dishesDAO iCategDishDao;

	@Override
	public List<Categories_dishes> getCategories_dishes() {
		return iCategDishDao.findAll();
	}

	@Override
	public Categories_dishes saveCategory_dish(Categories_dishes categ_dish) {
		return iCategDishDao.save(categ_dish);
	}

	@Override
	public Categories_dishes category_dishById(int id) {
		return iCategDishDao.findById(id).get();
	}

	@Override
	public void deleteCategory_dish(int id) {
		iCategDishDao.deleteById(id);
	}

}
