package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.ICategoriesDAO;
import team5.proyecto.reservesMenjador.dto.Category;

@Service
public class CategoriesServiceImpl implements ICategoriesService{

	@Autowired 
	ICategoriesDAO iCategoriesDao;
	
	@Override
	public List<Category> getCategories() {
		return iCategoriesDao.findAll();
	}

	@Override
	public Category saveCategory(Category categ) {
		return iCategoriesDao.save(categ);
	}

	@Override
	public Category findCategoryById(int id) {
		return iCategoriesDao.findById(id).orElse(null);
	}

	@Override
	public void deleteCategory(int id) {
		iCategoriesDao.deleteById(id);
	}

}
