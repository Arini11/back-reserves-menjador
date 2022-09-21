package team5.proyecto.reservesMenjador.services;

import java.util.List;
import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team5.proyecto.reservesMenjador.dao.IUserDAO;
import team5.proyecto.reservesMenjador.dto.Dish;
import team5.proyecto.reservesMenjador.dto.Users;


@Service
public class UserServiceImpl implements IUserService,UserDetailsService {
	
	@Autowired
	IUserDAO iuserDAO;

	@Override
	public List<Users> getUsers() {
		return iuserDAO.findAll();
	}

	@Override
	public Users saveUser(Users user) {
		return iuserDAO.save(user);
	}

	@Override
	public Users findByUsername(String username) {	
		return iuserDAO.findByUsername(username);
	}

	@Override
	public void deleteUser(Users username) {
		iuserDAO.delete(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = iuserDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.getUsername(), user.getPassword(), emptyList());
	}
	
	public Users updateUserImage(String username, byte[] imatge) {
		Users u = iuserDAO.findByUsername(username);
		u.setImage(imatge);
		return iuserDAO.save(u);
	}
	
}
