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

import team5.proyecto.reservesMenjador.dto.Categories;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.services.CategoriesServiceImpl;


@RestController
@RequestMapping("/api")
public class CategoriesController {
	
	@Autowired
	CategoriesServiceImpl catServImpl;

	@GetMapping("/categories")
	public List<Categories> getCategories(){
		return catServImpl.getCategories();
	}

	@GetMapping("/categories/{id}")
	public Categories findByID(@PathVariable(name="id") int id) {
		return catServImpl.categoryById(id);
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
	public String saveCategory(@RequestBody Categories category) {				
		//validar datos que entran por body , que no se repita el nombre
		boolean exists = false;
		
		for (Categories c : catServImpl.getCategories()) {
			if(c.getNameC().equals(category.getNameC())) {
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
	public Categories guardarEmpleado(@PathVariable(name="id") int id, @RequestBody Categories category) {
		Categories cat_selec = catServImpl.categoryById(id);
		
		cat_selec.setNameC(category.getNameC());	
		
		return catServImpl.saveCategory(cat_selec);
	}
	
	@PutMapping("/categories/{id}/dish/{idDish}")
	public void addDishToCategory(int id, int idDish) {
		Categories category = catServImpl.categoryById(id);
		category.getDishes().add(new Dish(idDish));
		catServImpl.saveCategory(category);
		
	}
}
