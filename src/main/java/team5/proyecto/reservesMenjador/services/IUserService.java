package team5.proyecto.reservesMenjador.services;
import java.util.List;
import  team5.proyecto.reservesMenjador.dto.Users;

public interface IUserService {

	public List<Users> getUsers(); //ALL
	
	//CREATE
	public Users saveUser (Users users);
	
	//READ
	public Users findByUsername (String username);
	
	//DELETE
	public void deleteUser (Users user);
	
}
