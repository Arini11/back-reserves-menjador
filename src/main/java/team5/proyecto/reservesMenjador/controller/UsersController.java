package team5.proyecto.reservesMenjador.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Users;
import team5.proyecto.reservesMenjador.services.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired UserServiceImpl userServiceImpl;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsersController(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@GetMapping("/users")
	public List<Users> getUsers() {
		return userServiceImpl.getUsers();
	}
	
	@GetMapping("/users/usernames")
	public List<String> getAllUsernames(){
		return userServiceImpl.getAllUsernames();
	}
	
	@GetMapping("/response-entity-builder-with-http-headers")
	public ResponseEntity<String> usingResponseEntityBuilderAndHttpHeaders() {
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("Baeldung-Example-Header", 
	      "Value-ResponseEntityBuilderWithHttpHeaders");

	    return ResponseEntity.ok()
	      .headers(responseHeaders)
	      .body("Response with header using ResponseEntity");
	}
	
	@PostMapping("/users/add")
	public Users saveUser(@RequestBody Users user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userServiceImpl.saveUser(user);
		return user;
	}
	
	@GetMapping("/users/{username}")
	public Users userByUsername(@PathVariable(name = "username")String username) {
		return userServiceImpl.findByUsername(username);
	}
	
	@PutMapping("/users/update/{username}") 
	public Users updateUser(@PathVariable(name = "username") String username, @RequestBody Users user){
		Users user_seleccionado = userServiceImpl.findByUsername(username);
		user_seleccionado.setEmail(user.getEmail());
		return userServiceImpl.saveUser(user_seleccionado);
	}

	@DeleteMapping("/users/delete/{id}")
	public void deleteUser(Users username) {
		userServiceImpl.deleteUser(username);
	}	
	
	@PostMapping("/users/update-img/{username}")
	public Users updateImgUser(@RequestPart(value="file") MultipartFile file, @PathVariable(name = "username")String username) throws IOException {
		return userServiceImpl.updateUserImage(username,file.getBytes());
	}
	
}
