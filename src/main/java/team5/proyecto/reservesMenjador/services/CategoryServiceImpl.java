package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.ICategoryDAO;
import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired 
	ICategoryDAO iCategoriesDao;
	
	@Override
	public List<Category> getCategories() {
		return iCategoriesDao.findAll();
	}

	@Override
	public Category saveCategory(Category categ) {
		return iCategoriesDao.save(categ);
	}

	@Override
	public Category findById(int id) {
		return iCategoriesDao.findById(id).orElse(null);
	}

	@Override
	public void deleteCategory(int id) {
		iCategoriesDao.deleteById(id);
	}
	
	@Override
    public Category findByName(String name) {
    	return iCategoriesDao.findByName(name);
    }
	
	@Override
	public List<Category> findByDishes(Dish dish){
		return iCategoriesDao.findByDishes(dish);
	}

}
