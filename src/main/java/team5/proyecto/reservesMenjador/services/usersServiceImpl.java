package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.proyecto.reservesMenjador.dao.IUsersDAO;
import team5.proyecto.reservesMenjador.dto.Users;


@Service
public class usersServiceImpl implements IusersService {
	
	@Autowired
	IUsersDAO iusersDAO;

	@Override
	public List<Users> listarUsers() {
		return iusersDAO.findAll();
	}

	@Override
	public Users guardarUser(Users users) {
		return iusersDAO.save(users);
	}

	@Override
	public Users usersPorId(Long Id) {
	
		return iusersDAO.findById(Id).get();
	}

	@Override
	public Users actualizarUser(Users users) {
		
		return iusersDAO.save(users);
	}

	@Override
	public void eliminarUser(Long Id) {
		iusersDAO.deleteById(Id);
	} 
	
	

}
