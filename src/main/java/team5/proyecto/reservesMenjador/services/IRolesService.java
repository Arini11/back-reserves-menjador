package team5.proyecto.reservesMenjador.services;
import java.util.List;
import  team5.proyecto.reservesMenjador.dto.Roles;

public interface IRolesService {

	//ALL
	public List<Roles> getRoles ();
	
	//CREATE
	public Roles saveRol (Roles roles);
	
	//READ
	public Roles rolesById (Long Id);
	
	//UPDATE
	public Roles updateRol (Roles roles);
	
	//DELETE
	public void deleteRol (Long Id);	
	
}
