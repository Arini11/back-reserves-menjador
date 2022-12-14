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
	public Category findById(int id) {
		return iCategoriesDao.findById(id).orElse(null);
	}
	
	@Override
	public Category findByName(String name) {
		return iCategoriesDao.findByName(name);
    }


	@Override
	public Category saveCategory(Category categ) {
	//validar datos, que no se repita el nombre
		boolean exists = false;
		
		for (Category c : getCategories()) {
			if(c.getName().equals(categ.getName())) {
				exists = true;
			}
		}
		if(!exists) {			
			return iCategoriesDao.save(categ);
		}
		return null;	
				
	}
	

	@Override
	public void deleteCategory(int id) {
		iCategoriesDao.deleteById(id);
	}

	@Override
	public Category updateCategory(Category categ) {
		return iCategoriesDao.save(categ);
	}
	
}
