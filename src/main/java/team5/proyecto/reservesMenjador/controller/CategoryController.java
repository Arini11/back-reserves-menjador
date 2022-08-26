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
import team5.proyecto.reservesMenjador.services.CategoryServiceImpl;


@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl catServImpl;

	@GetMapping("/categories")
	public List<Category> getCategories(){
		return catServImpl.getCategories();
	}

	@GetMapping("/categories/{id}")
	public Category findByID(@PathVariable(name="id") int id) {
		return catServImpl.findById(id);
	}
	
	@GetMapping("/categories/name/{name}")
	public Category findByName(@PathVariable(name="name") String name) {
		return catServImpl.findByName(name);
	}	
	
	@GetMapping("/categories/dishes")
	public List<Category> findByDishes(@RequestBody Dish dish){
		return catServImpl.findByDishes(dish);
	}
	
	@DeleteMapping("/categories/{id}")
	public String deleteDepartamento(@PathVariable(name="id") int id) {
		catServImpl.deleteCategory(id);
		return "La categoria con id "+id+" ha sido borrada!";
	}
	
	@PostMapping("/categories") //crear y actualizar
	public String saveCategory(@RequestBody Category category) {				
		//validamos que no exista(comprobando nombre) en CategServiceImpl
		//tanto te crea como te actualiza - no hace falta el metodo Put 
		//al solo tener nombre e id no tiene sentido que tenga metodo put
		return catServImpl.saveCategory(category);
	}
	
	/**
	 * NO NECESARIO - A COMENTAR
	 * no hace falta findByID(), save ya te actualiza si le pasas por body el id
	 * que sntido tiene cambiar el nombre? si le cambias ya es otra
	 * categoria y ya esta - a no ser que sea para corregir fallo
	@PutMapping("/categories")
	public String updateCategory(@RequestBody Category category) {
		return catServImpl.saveCategory(category);
	}
	*/
	
	/**TODO
	 * revisar como añadir registros a la tabla intermedia a traves de api
	 * si fuera en controller, en category o en dish controller?
	 * metodo en dto? 
	 */
	@PutMapping("/categories/{id}/dish/{idDish}")
	public void addDishToCategory(int id, int idDish) {
		Category category = catServImpl.findById(id);
		//category.getDishes().add(new Dish(idDish)); por eso habia un constructor de plato solo con id - revisar
		//category.getDishes().add(new Dish(idDish));
		catServImpl.saveCategory(category);
		
	}
}
