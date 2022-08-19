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

import team5.proyecto.reservesMenjador.dto.Dishes;
import team5.proyecto.reservesMenjador.services.DishesServiceImpl;

@RestController
@RequestMapping("/api")
public class DishesController {

	@Autowired
	DishesServiceImpl dishesServiceImpl;
	
	@GetMapping("/dishes")
	public List<Dishes> getDishes(){
		return dishesServiceImpl.getDishes();
	}
	
	@GetMapping("/dishes/{id}")
	public Dishes dishById(@PathVariable(name="id") int id) {		
		return dishesServiceImpl.dishById(id);	
	}
	
	@DeleteMapping("/dishes/{id}")
	public void deleteDepartamento(@PathVariable(name="id") int id) {
		dishesServiceImpl.deleteDish(id);
	}
	
	@PostMapping("/dishes") //crear
	public String saveDish(@RequestBody Dishes dish) {				
		//validar datos que entran por body, que no se repita el nombre
		boolean exists = false;
		
		for (Dishes d : dishesServiceImpl.getDishes()) {
			if(d.getNameD().equals(dish.getNameD())) {
				exists = true;
			}
		}
		if(!exists) {
			dishesServiceImpl.saveDish(dish);
			return "Plato guardado!";
		}
		return "El plato ya existe!";			
	}
	
	@PutMapping("/dishes/{id}")
	public Dishes updateDish(@PathVariable(name="id") int id, @RequestBody Dishes dish) {
		Dishes dish_selec = dishesServiceImpl.dishById(id);
		
		dish_selec.setNameD(dish.getNameD());
		dish_selec.setImage(dish.getImage());
		dish_selec.setPopularity(dish.getPopularity());		
		
		return dishesServiceImpl.saveDish(dish_selec);
	}
	
}
