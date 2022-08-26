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

import team5.proyecto.reservesMenjador.dto.Users;
import team5.proyecto.reservesMenjador.services.UsersServiceImpl;

@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired UsersServiceImpl userServiceImpl;

	@GetMapping("/users")
	public List<Users> getUsers() {
		return userServiceImpl.getUsers();
	}
	@PostMapping("/users/add")
	public Users saveUser(Users users) {
		return userServiceImpl.saveUser(users);
	}
	@GetMapping("/users/{id}")
	public Users userByUsername(@PathVariable(name = "username")String username) {
		Users userByUsername = new Users();
		userByUsername = userServiceImpl.userByUsername(username);
		return userByUsername;
	}
	@PutMapping("/users/{id}")
	 
	public Users updateUser(@PathVariable(name = "username") String username, @RequestBody Users users){
		Users user_seleccionado = new Users();
		Users user_actualizado = new Users();
		user_seleccionado = userServiceImpl.userByUsername(username);
		user_seleccionado.setUsername(users.getUsername());
		user_actualizado = userServiceImpl.updateUser(user_seleccionado);
		
		return user_actualizado;
	}
	@DeleteMapping("/users/{id}")
	public void deleteUser(Users username) {
		userServiceImpl.deleteUser(username);
	}
	
	
}
