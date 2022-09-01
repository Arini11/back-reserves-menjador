package team5.proyecto.reservesMenjador.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import team5.proyecto.reservesMenjador.services.DishServiceImpl;


@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl catServImpl;
	
	@Autowired
	DishServiceImpl dishServiceImpl;

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
	
	@DeleteMapping("/categories/delete/{id}")
	public void deleteCategory(@PathVariable(name="id") int id) {
		catServImpl.deleteCategory(id);
	}
	
	@PostMapping("/categories/add") //crear y actualizar
	public Category saveCategory(@RequestBody Category category) {				
		//validamos que no exista(comprobando nombre) en CategServiceImpl
		return catServImpl.saveCategory(category);
	}
	
}
