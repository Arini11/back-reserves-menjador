package team5.proyecto.reservesMenjador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team5.proyecto.reservesMenjador.dao.IUsersDAO;
import team5.proyecto.reservesMenjador.dto.Users;


@Service
public class UsersServiceImpl implements IUsersService {
	
	@Autowired
	IUsersDAO iusersDAO;

	@Override
	public List<Users> getUsers() {
		return iusersDAO.findAll();
	}

	@Override
	public Users saveUser(Users users) {
		return iusersDAO.save(users);
	}

	@Override
	public Users userById(Long Id) {	
		return iusersDAO.findById(Id).orElse(null);
	}

	@Override
	public Users updateUser(Users users) {		
		return iusersDAO.save(users);
	}

	@Override
	public void deleteUser(Long Id) {
		iusersDAO.deleteById(Id);
	} 

}
