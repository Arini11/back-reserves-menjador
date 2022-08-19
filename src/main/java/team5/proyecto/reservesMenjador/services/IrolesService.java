package team5.proyecto.reservesMenjador.services;
import java.util.List;
import  team5.proyecto.reservesMenjador.dto.Roles;

public interface IrolesService {

	//ALL
	public List<Roles> listarRoles ();
	
	//CREATE
	public Roles guardarRol (Roles roles);
	
	//READ
	public Roles rolesPorId (Long Id);
	
	//UPDATE
	public Roles actualizarRol (Roles roles);
	
	//DELETE
	public void eliminarRol (Long Id);
	
	
	
}
