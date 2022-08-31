package team5.proyecto.reservesMenjador.services;

import java.util.List;
import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team5.proyecto.reservesMenjador.dao.IUsersDAO;
import team5.proyecto.reservesMenjador.dto.Users;


@Service
public class UsersServiceImpl implements IUsersService,UserDetailsService {
	
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
	public Users userByUsername(String username) {	
		return iusersDAO.findByUsername(username);
	}

	@Override
	public Users updateUser(Users users) {		
		return iusersDAO.save(users);
	}

	@Override
	public void deleteUser(Users username) {
		iusersDAO.delete(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = iusersDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getUsername(), user.getPassword(), emptyList());
	} 

	
}
