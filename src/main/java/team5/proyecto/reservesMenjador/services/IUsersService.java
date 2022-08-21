package team5.proyecto.reservesMenjador.services;
import java.util.List;
import  team5.proyecto.reservesMenjador.dto.Users;

public interface IUsersService {

	public List<Users> getUsers(); //ALL
	
	//CREATE
	public Users saveUser (Users users);
	
	//READ
	public Users userById (Long Id);
	
	//UPDATE
	public Users updateUser (Users users);
	
	//DELETE
	public void deleteUser (Long Id);
	
}
