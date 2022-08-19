package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.proyecto.reservesMenjador.dao.IRolesDAO;
import team5.proyecto.reservesMenjador.dto.Roles;

@Service
public class rolesServiceImpl implements IrolesService{

	@Autowired
	IRolesDAO irolesDAO;

	@Override
	public List<Roles> listarRoles() {
		
		return irolesDAO.findAll();
	}

	@Override
	public Roles guardarRol(Roles roles) {
		return irolesDAO.save(roles);
	}

	@Override
	public Roles rolesPorId(Long Id) {
		
		return irolesDAO.findById(Id).get();
	}

	@Override
	public Roles actualizarRol(Roles roles) {
		
		return irolesDAO.save(roles);
	}

	@Override
	public void eliminarRol(Long Id) {
		irolesDAO.deleteById(Id);
		
	}
	
	
}
