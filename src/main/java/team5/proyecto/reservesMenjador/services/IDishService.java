package team5.proyecto.reservesMenjador.services;

import java.util.List;

import team5.proyecto.reservesMenjador.dto.Dish;

public interface IDishesService {
	//CRUD
	public List<Dish> getDishes(); //CREATE || UPDATE

	public Dish saveDish(Dish dish);

	public Dish findDishById(int id);

	public void deleteDish(int id);

}
