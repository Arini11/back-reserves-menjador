package team5.proyecto.reservesMenjador.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Order;
import team5.proyecto.reservesMenjador.dto.Users;
import team5.proyecto.reservesMenjador.services.DishServiceImpl;
import team5.proyecto.reservesMenjador.services.OrderServiceImpl;

import team5.proyecto.reservesMenjador.services.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderServiceImpl orderServ;
	
	@Autowired
	DishServiceImpl dishServ;

	@Autowired
	UserServiceImpl usersServiceImpl;

	@GetMapping("/orders")
	public List<Order> getAll() {
		return orderServ.getOrders();
	}

	@GetMapping("/orders/{id}")
	public Order getById(@PathVariable(name = "id") int id) {
		return orderServ.findById(id);
	}
	
	@GetMapping("/orders/{id}/dishes")
	public List<Dish> getOrderDishes(@PathVariable(name = "id") int id) {
		Order o = orderServ.findById(id);
		return o.getDishes();
	}

	@GetMapping("/orders/user/{username}")
	public List<Order> ordersByUser(@PathVariable(name = "username") String username) {
		Users userSel = usersServiceImpl.findByUsername(username);
		return orderServ.findByUser(userSel);
	}

	@PostMapping("orders/add")
	public Order addOrder(@RequestBody Order order) {
		return orderServ.addOrder(order);
	}

	@PutMapping("orders/update") //para clientes
	public Order update(@RequestBody Order order) {
		return orderServ.updateOrder(order);
	}
	
	@DeleteMapping("orders/delete/{id}")
	public Order delete(@PathVariable(name = "id") int id) {
		return orderServ.deleteOrder(id);
	}
	
	@PutMapping("/orders/add/dishes")
	public Order addDishesToOrder(@RequestBody Order o) {
		Order newOrder = orderServ.findById(o.getId());
		
		newOrder.getDishes().clear();

		newOrder.getDishes().addAll(
				o.getDishes()
				.stream()
				.map(dish -> {
					Dish dd = dishServ.findById(dish.getId());
					dd.getOrders().add(newOrder);
					return dd;
				}).collect(Collectors.toList()));
		
		return orderServ.addDishesToOrder(newOrder);
	}
}
