package team5.proyecto.reservesMenjador.services;
import java.util.List;
import  team5.proyecto.reservesMenjador.dto.Users;

public interface IusersService {

	public List<Users> listarUsers(); //ALL
	
	//CREATE
	public Users guardarUser (Users users);
	
	//READ
	public Users usersPorId (Long Id);
	
	//UPDATE
	public Users actualizarUser (Users users);
	
	//DELETE
	public void eliminarUser (Long Id);
	
}
