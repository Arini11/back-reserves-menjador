package team5.proyecto.reservesMenjador.services;

import java.util.List;

import team5.proyecto.reservesMenjador.dto.Dishes;

public interface IDishesService {
	//CRUD
	public List<Dishes> getDishes(); //CREATE || UPDATE

	public Dishes saveDish(Dishes dish);

	public Dishes dishById(int id);

	public void deleteDish(int id);

}
