package team5.proyecto.reservesMenjador.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import team5.proyecto.reservesMenjador.services.UsersServiceImpl;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderServiceImpl orderServ;
	
	@Autowired
	DishServiceImpl dishServ;

	@Autowired
	UsersServiceImpl usersServiceImpl;

	@GetMapping("/orders")
	public List<Order> getAll() {
		return orderServ.getOrders();
	}

	@GetMapping("/orders/{id}")
	public Order getById(@PathVariable(name = "id") int id) {
		return orderServ.orderById(id);
	}
	
	@GetMapping("/orders/{id}/dishes")
	public List<Dish> getOrderDishes(@PathVariable(name = "id") int id) {
		Order o = orderServ.orderById(id);
		return o.getDishes();
	}

	@GetMapping("/orders/user/{username}")
	public List<Order> ordersByUser(@PathVariable(name = "username") String username) {
		Users userSel = usersServiceImpl.userByUsername(username);

		return orderServ.findByUser(userSel);
	}
	
	@PostMapping("/orders/{id}/dishes/{idDish}")
	public Order prova(@PathVariable(name = "id")int id, @PathVariable(name = "idDish")int idDish) {
		Order order = orderServ.orderById(id);
		Order newOrder = new Order();
		//category.getDishes().add(new Dish(idDish)); por eso habia un constructor de plato solo con id - revisar
		List<Dish> dishes = order.getDishes();
		dishes.add(dishServ.findById(idDish));
		
		newOrder.setCreatedOn(order.getCreatedOn());
		newOrder.setModifiedOn(order.getModifiedOn());
		newOrder.setDeliveryOn(order.getDeliveryOn());
		newOrder.setDelivered(order.getDelivered());
		newOrder.setUser(order.getUser());
		newOrder.setDishes(dishes);
        return orderServ.saveOrder(newOrder);
	}

	@PostMapping("orders/add")
	public Order save(@RequestBody Order order) {
		return orderServ.saveOrder(order);
	}

	@PutMapping("orders/update/{id}")
	public Order update(@PathVariable(name = "id") int id, @RequestBody Order order) {

		Order orderSel = orderServ.orderById(id);
		orderSel.setCreatedOn(order.getCreatedOn());
		orderSel.setModifiedOn(order.getModifiedOn());
		orderSel.setDeliveryOn(order.getDeliveryOn());
		orderSel.setDelivered(order.getDelivered());
		orderSel.setUser(order.getUser());

		return orderServ.updateOrder(orderSel);
	}

	@DeleteMapping("orders/delete/{id}")
	public void delete(@PathVariable(name = "id") int id) {
		orderServ.deleteOrder(id);
	}
}
