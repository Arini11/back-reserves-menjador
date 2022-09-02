package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.proyecto.reservesMenjador.dao.IRolDAO;
import team5.proyecto.reservesMenjador.dto.Rol;

@Service
public class RolServiceImpl implements IRolService{

	@Autowired
	IRolDAO irolesDAO;

	@Override
	public List<Rol> getRoles() {		
		return irolesDAO.findAll();
	}

	@Override
	public Rol saveRol(Rol roles) {
		return irolesDAO.save(roles);
	}

	@Override
	public Rol findById(int Id) {		
		return irolesDAO.findById(Id).orElse(null);
	}

	@Override
	public void deleteRol(int Id) {
		irolesDAO.deleteById(Id);		
	}	
	
}
