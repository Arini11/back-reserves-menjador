package team5.proyecto.reservesMenjador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team5.proyecto.reservesMenjador.dto.Roles;
import team5.proyecto.reservesMenjador.services.RolesServiceImpl;

@RestController
@RequestMapping("/api")
public class RolesController {

	@Autowired RolesServiceImpl rolesServiceImpl;

	@GetMapping("/roles")
	public List<Roles> getAll(){
	return rolesServiceImpl.getRoles();
	}
	@PostMapping("/roles/add")
	public Roles saveRol(@RequestBody Roles roles) {
		return rolesServiceImpl.saveRol(roles);
	}
	@GetMapping("/roles/{id}")
	public Roles rolesById(@PathVariable(name = "id") Long id) {
		Roles rolSelect = rolesServiceImpl.rolesById(id);
		return rolesServiceImpl.rolesById(id);
	}
	@DeleteMapping("roles/delete/{id}")
	public void deleteRol(Long Id) {
		rolesServiceImpl.deleteRol(Id);
	}
	
	
}
