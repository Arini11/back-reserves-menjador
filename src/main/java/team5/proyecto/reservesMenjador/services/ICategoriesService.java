package team5.proyecto.reservesMenjador.services;

import java.util.List;

import team5.proyecto.reservesMenjador.dto.Categories;

public interface ICategoriesService {
	//CRUD	
	public List<Categories> getCategories();

	public Categories saveCategory(Categories categ); //CREATE || UPDATE

	public Categories categoryById(int id);

	public void deleteCategory(int id);

}
