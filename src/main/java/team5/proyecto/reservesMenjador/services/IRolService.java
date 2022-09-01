package team5.proyecto.reservesMenjador.services;
import java.util.List;
import  team5.proyecto.reservesMenjador.dto.Rol;

public interface IRolService {

	//ALL
	public List<Rol> getRoles ();
	
	//CREATE || UPDATE
	public Rol saveRol (Rol roles);
	
	//READ
	public Rol findById (Long Id);
	
	//DELETE
	public void deleteRol (Long Id);	
	
}
