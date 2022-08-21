package team5.proyecto.reservesMenjador.services;

import java.util.List;

import team5.proyecto.reservesMenjador.dto.Category;

public interface ICategoriesService {
	//CRUD	
	public List<Category> getCategories();

	public Category saveCategory(Category categ); //CREATE || UPDATE

	public Category findCategoryById(int id);

	public void deleteCategory(int id);

}
