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

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;
import team5.proyecto.reservesMenjador.services.DishServiceImpl;

@RestController
@RequestMapping("/api")
public class DishController {

	@Autowired
	DishServiceImpl dishServiceImpl;

	@GetMapping("/dishes")
	public List<Dish> getDishes() {
		return dishServiceImpl.getDishes();
	}

	@GetMapping("/dishes/{id}")
	public Dish findById(@PathVariable(name = "id") int id) {
		return dishServiceImpl.findById(id);
	}
	
	@GetMapping("/dishes/name/{name}")
	public Dish findByName(@PathVariable(name = "name") String name) {
		return dishServiceImpl.findByName(name);
	}
	
	@GetMapping("/dishes/popularity/{popularity}")
	public List<Dish> findByPopularity(@PathVariable(name = "popularity") int popularity) {
		return dishServiceImpl.findByPopularity(popularity);
	}
	
	@GetMapping("/dishes/orders")
	public List<Dish> findByOrders(@RequestBody Order order) {
		return dishServiceImpl.findByOrders(order);
	}	
	
	@GetMapping("/dishes/categories")
	public List<Dish> findByCategories(@RequestBody Category category) {
		return dishServiceImpl.findByCategories(category);
	}

	@DeleteMapping("/dishes/{id}")
	public String deleteDish(@PathVariable(name = "id") int id) {
		dishServiceImpl.deleteDish(id);
		return "El plato con id "+id+" ha sido borrado!";
	}

	@PostMapping("/dishes") // crear
	public String saveDish(@RequestBody Dish dish) {
		//en ServiceImpl comprobamos que no exista ya ese nombre de plato
		return dishServiceImpl.saveDish(dish);
	}

	//pasa lo mismo que en category, save() ya te actualiza si le das un id que existe
	//se podria quitar directamente? PREGUNTAR
	@PutMapping("/dishes/{id}")
	public String updateDish(@PathVariable(name = "id") int id, @RequestBody Dish dish) {
		Dish dishSelected = dishServiceImpl.findById(id);

		dishSelected.setNameD(dish.getNameD());
		dishSelected.setImage(dish.getImage());
		dishSelected.setPopularity(dish.getPopularity());

		return dishServiceImpl.saveDish(dishSelected);
	}

}
