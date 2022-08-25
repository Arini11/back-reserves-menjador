package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.proyecto.reservesMenjador.dao.IRolesDAO;
import team5.proyecto.reservesMenjador.dto.Roles;

@Service
public class RolesServiceImpl implements IRolesService{

	@Autowired
	IRolesDAO irolesDAO;

	@Override
	public List<Roles> getRoles() {		
		return irolesDAO.findAll();
	}

	@Override
	public Roles saveRol(Roles roles) {
		return irolesDAO.save(roles);
	}

	@Override
	public Roles rolesById(Long Id) {		
		return irolesDAO.findById(Id).orElse(null);
	}

	@Override
	public Roles updateRol(Roles roles) {		
		return irolesDAO.save(roles);
	}

	@Override
	public void deleteRol(Long Id) {
		irolesDAO.deleteById(Id);		
	}	
	
}
