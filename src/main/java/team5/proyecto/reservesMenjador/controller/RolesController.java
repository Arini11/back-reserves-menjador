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

import team5.proyecto.reservesMenjador.dto.Rol;
import team5.proyecto.reservesMenjador.services.RolServiceImpl;

@RestController
@RequestMapping("/api")
public class RolesController {

	@Autowired RolServiceImpl rolesServiceImpl;

	@GetMapping("/roles")
	public List<Rol> getAll(){
	return rolesServiceImpl.getRoles();
	}
	@PostMapping("/roles/add")
	public Rol saveRol(@RequestBody Rol roles) {
		return rolesServiceImpl.saveRol(roles);
	}
	@GetMapping("/roles/{id}")
	public Rol rolesById(@PathVariable(name = "id") Long id) {
		return rolesServiceImpl.findById(id);
	}
	@DeleteMapping("roles/delete/{id}")
	public void deleteRol(@PathVariable(name = "id")Long Id) {
		rolesServiceImpl.deleteRol(Id);
	}
	
	
}
