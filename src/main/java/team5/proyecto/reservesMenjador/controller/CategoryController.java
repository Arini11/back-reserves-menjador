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
	
//	@GetMapping("/categories/nombre/{nombre}")
//	public List<Departamentos> findByNombre(@PathVariable(name="nombre") String nombre) {
//		return departServImpl.findByName(nombre);
//	}	
	
	@DeleteMapping("/categories/{id}")
	public void deleteDepartamento(@PathVariable(name="id") int id) {
		catServImpl.deleteCategory(id);
	}
	
	@PostMapping("/categories") //crear
	public String saveCategory(@RequestBody Category category) {				
		//validar datos que entran por body , que no se repita el nombre
		boolean exists = false;
		
		for (Category c : catServImpl.getCategories()) {
			if(c.getName().equals(category.getName())) {
				exists = true;
			}
		}
		if(!exists) {
			catServImpl.saveCategory(category);
			return "Categoria guardada!";
		}
		return "La categoria ya existe!";			
	}
	
		
	@PutMapping("/categories/{id}")
	public Category guardarEmpleado(@PathVariable(name="id") int id, @RequestBody Category category) {
		Category cat_selec = catServImpl.findById(id);
		
		cat_selec.setName(category.getName());	
		
		return catServImpl.saveCategory(cat_selec);
	}
	
	@PutMapping("/categories/{id}/dish/{idDish}")
	public void addDishToCategory(int id, int idDish) {
		Category category = catServImpl.findById(id);
		//category.getDishes().add(new Dish(idDish));
		catServImpl.saveCategory(category);
		
	}
}
