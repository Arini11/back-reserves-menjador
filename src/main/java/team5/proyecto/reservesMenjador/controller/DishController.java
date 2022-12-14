package team5.proyecto.reservesMenjador.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;
import team5.proyecto.reservesMenjador.services.CategoryServiceImpl;
import team5.proyecto.reservesMenjador.services.DishServiceImpl;

@RestController
@CrossOrigin()
@RequestMapping("/api")
public class DishController {

	@Autowired
	DishServiceImpl dishServiceImpl;
	
	@Autowired
	CategoryServiceImpl catServImpl;

	@GetMapping("/dishes")
	public List<Dish> getDishes() {
		return dishServiceImpl.getDishes();
	}

	@GetMapping("/dishes/{id}")
	public Dish findById(@PathVariable(name = "id") int id) {
		return dishServiceImpl.findById(id);
	}
	
	@GetMapping("/dishes/status/{status}")
	public List<Dish> findByStatus(@PathVariable(name = "status") boolean status) {
		return dishServiceImpl.findByStatus(status);
	}
	
	@GetMapping("/dishes/name/{name}")
	public Dish findByName(@PathVariable(name = "name") String name) {
		return dishServiceImpl.findByName(name);
	}
	
	@GetMapping("/dishes/popularity/{popularity}")
	public List<Dish> findByPopularity(@PathVariable(name = "popularity") int popularity) {
		return dishServiceImpl.findByPopularity(popularity);
	}
	
	@GetMapping("/dishes/categories")
	public List<Dish> findByCategories(@RequestBody Category category) {
		return dishServiceImpl.findByCategories(category);
	}
	
	@GetMapping("/dishes/orders")
	public List<Dish> findByOrders(@RequestBody Order order) {
		return dishServiceImpl.findByOrders(order);
	}	
		
	@GetMapping("/dishes/{id}/categories")
	public List<Category> categoriesOfDish(@PathVariable(name = "id") int id) {
		return dishServiceImpl.findById(id).getCategories();
	}
	
	@GetMapping("/dishes/categories")
	public List<Dish> findByCategories(@RequestBody Category category) {
		return dishServiceImpl.findByCategories(category);
	}

	@DeleteMapping("/dishes/delete/{id}")
	public void deleteDish(@PathVariable(name = "id") int id) {
		dishServiceImpl.deleteDish(id);
	}

	@PostMapping("/dishes/add") // crear
	public Dish saveDish(@RequestBody Dish dish) {
		//en ServiceImpl comprobamos que no exista ya ese nombre de plato
		return dishServiceImpl.saveDish(dish);
	}

	@PutMapping("/dishes/update")
	public Dish updateDish(@RequestBody Dish dish) throws IOException {
		return dishServiceImpl.updateDish(dish);
	}
	
	@PostMapping("/dishes/update/{id}")
	public Dish updateDish(@RequestPart(value="file") MultipartFile file, @PathVariable(name = "id") int id) throws IOException {
		return dishServiceImpl.updateDishImage(id,file.getBytes());
	}
	
	@PutMapping("/dishes/add/categories")
	public Dish addCategoriesToDish(@RequestBody Dish dish) {
		Dish newDish = dishServiceImpl.findById(dish.getId());
		
		newDish.getCategories().clear();

		newDish.getCategories().addAll(
				dish.getCategories()
				.stream()
				.map(c -> {
					Category cc = catServImpl.findById(c.getId());
					cc.getDishes().add(newDish);
					return cc;
				}).collect(Collectors.toList()));
		
		return dishServiceImpl.updateDish(newDish);
	}

}
