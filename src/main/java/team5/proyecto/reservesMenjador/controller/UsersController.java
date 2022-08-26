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
	@GetMapping("/users/{username}")
	public Users userByUsername(@PathVariable(name = "username")String username) {
		return userServiceImpl.userByUsername(username);
	}
	@PutMapping("/users/{username}")
	public Users updateUser(@PathVariable(name = "username") String username, @RequestBody Users users){
		Users user_seleccionado = userServiceImpl.userByUsername(username);
		user_seleccionado.setEmail(users.getEmail());
		return userServiceImpl.updateUser(user_seleccionado);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(Users username) {
		userServiceImpl.deleteUser(username);
	}
	
	
}
