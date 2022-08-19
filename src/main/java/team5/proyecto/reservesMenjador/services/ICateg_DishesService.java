package team5.proyecto.reservesMenjador.services;

import java.util.List;

import team5.proyecto.reservesMenjador.dto.Categories_dishes;

public interface ICateg_DishesService {
	//CRUD	
	public List<Categories_dishes> getCategories_dishes();

	public Categories_dishes saveCategory_dish(Categories_dishes categ_dish);

	public Categories_dishes category_dishById(int id);

	public void deleteCategory_dish(int id);

}
