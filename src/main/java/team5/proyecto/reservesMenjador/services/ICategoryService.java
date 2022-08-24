package team5.proyecto.reservesMenjador.services;

import java.util.List;

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;

public interface ICategoryService {
	//CRUD	
	public List<Category> getCategories();	

	public Category findById(int id);
	
	public Category findByName(String name);
	
	public List<Category> findByDishes(Dish dish);
	
	public String saveCategory(Category categ); //CREATE || UPDATE

	public void deleteCategory(int id);	

}
