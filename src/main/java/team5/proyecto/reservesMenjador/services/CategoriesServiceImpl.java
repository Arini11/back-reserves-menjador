package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team5.proyecto.reservesMenjador.dao.ICategoriesDAO;
import team5.proyecto.reservesMenjador.dto.Categories;

@Service
public class CategoriesServiceImpl implements ICategoriesService{

	@Autowired 
	ICategoriesDAO iCategoriesDao;
	
	@Override
	public List<Categories> getCategories() {
		return iCategoriesDao.findAll();
	}

	@Override
	public Categories saveCategory(Categories categ) {
		return iCategoriesDao.save(categ);
	}

	@Override
	public Categories categoryById(int id) {
		return iCategoriesDao.findById(id).get();
	}

	@Override
	public void deleteCategory(int id) {
		iCategoriesDao.deleteById(id);
	}

}
