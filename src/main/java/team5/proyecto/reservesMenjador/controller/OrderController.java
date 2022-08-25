package team5.proyecto.reservesMenjador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import team5.proyecto.reservesMenjador.dto.Order;
import team5.proyecto.reservesMenjador.dto.Users;
import team5.proyecto.reservesMenjador.services.OrderServiceImpl;

import team5.proyecto.reservesMenjador.services.UsersServiceImpl;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderServiceImpl orderServ;

	@Autowired
	UsersServiceImpl usersServiceImpl;

	@GetMapping("/orders")
	public List<Order> getAll() {
		return orderServ.getOrders();
	}

	@GetMapping("/orders/{id}")
	public Order getById(@PathVariable(name = "id") int id) {
		Order o = orderServ.orderById(id);
		return o != null ? o : new Order();
	}

	@GetMapping("/orders/user/{username}")
	public List<Order> userById(@PathVariable(name = "username") String username) {
		Users userSel = usersServiceImpl.userByUsername(username);

		return orderServ.findByUser(userSel);
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
