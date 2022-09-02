package team5.proyecto.reservesMenjador.services;

import java.util.List;

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;

public interface ICategoryService {
	//CRUD	
	public List<Category> getCategories();	

	public Category findById(int id);
	
	public Category findByName(String name);
	
	public Category saveCategory(Category categ); //CREATE 

	public void deleteCategory(int id);	
	
	public Category updateCategory(Category categ); // UPDATE

}
