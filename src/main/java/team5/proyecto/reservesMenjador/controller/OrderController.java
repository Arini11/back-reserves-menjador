package team5.proyecto.reservesMenjador.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.DeliveryStatus;
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
		// No retornar les ordres cancelades
		List<Order> newOrders = new ArrayList<Order>();
		orderServ.findByUser(userSel).forEach( o -> {
			if(o.getDelivered() != DeliveryStatus.C) {
				newOrders.add(o);
			}
		});
		return newOrders;
	}

	@PostMapping("orders/add")
	public Order addOrder(@RequestBody Order order) {
		return orderServ.addOrder(order);
	}

	@PutMapping("orders/update") //para clientes
	public Order update(@RequestBody Order order) {
		return orderServ.updateOrder(order);
	}
	
	@PutMapping("orders/update/status") //para admin
	public Order updateStatus(@RequestBody Order order) {
		return orderServ.updateStatus(order);
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
	
	private Sort.Direction getSortDirection(String direction) {
	    if (direction.equals("asc")) {
	      return Sort.Direction.ASC;
	    } else if (direction.equals("desc")) {
	      return Sort.Direction.DESC;
	    }

	    return Sort.Direction.ASC;
	  }
	
	// Ordenar llista ordres
	@GetMapping("/orders/sorted")
	  public List<Order> getAllOrdersSorted(@RequestParam(defaultValue = "id,desc") String[] sort) {
	      List<org.springframework.data.domain.Sort.Order> orders = new ArrayList<org.springframework.data.domain.Sort.Order>();

	      if (sort[0].contains(",")) {
	        // will sort more than 2 fields
	        // sortOrder="field, direction"
	        for (String sortOrder : sort) {
	          String[] _sort = sortOrder.split(",");
	          orders.add(new org.springframework.data.domain.Sort.Order(getSortDirection(_sort[1]), _sort[0]));
	        }
	      } else {
	        // sort=[field, direction]
	        orders.add(new org.springframework.data.domain.Sort.Order(getSortDirection(sort[1]), sort[0]));
	      }

	      List<Order> ordres = orderServ.findAll(Sort.by(orders));

	      return ordres;
	  }
}
