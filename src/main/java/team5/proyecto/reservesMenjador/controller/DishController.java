package team5.proyecto.reservesMenjador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.services.IDishService;

@RestController
@RequestMapping("/api")
public class DishController {

	@Autowired
	private IDishService dishesServiceImpl;

	@GetMapping("/dishes")
	public List<Dish> getDishes() {
		return dishesServiceImpl.getDishes();
	}

	@GetMapping("/dishes/{id}")
	public Dish findDishById(@PathVariable(name = "id") int id) {
		return dishesServiceImpl.findById(id);
	}

	@DeleteMapping("/dishes/{id}")
	public void deleteDish(@PathVariable(name = "id") int id) {
		dishesServiceImpl.deleteDish(id);
	}

	@PostMapping("/dishes") // crear
	public String saveDish(@RequestBody Dish dish) {
		// validar datos que entran por body, que no se repita el nombre
		boolean exists = false;

		for (Dish iterateDish : dishesServiceImpl.getDishes()) {
			if (iterateDish.getName().equals(dish.getName())) {
				exists = true;
			}
		}
		if (!exists) {
			dishesServiceImpl.saveDish(dish);
			return "Plato guardado!";
		}
		return "El plato ya existe!";
	}

	@PutMapping("/dishes/{id}")
	public Dish updateDish(@PathVariable(name = "id") int id, @RequestBody Dish dish) {
		Dish dishSelected = dishesServiceImpl.findById(id);

		dishSelected.setName(dish.getName());
		dishSelected.setImage(dish.getImage());
		dishSelected.setPopularity(dish.getPopularity());

		return dishesServiceImpl.saveDish(dishSelected);
	}

}
