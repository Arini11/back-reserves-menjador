package team5.proyecto.reservesMenjador.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Users;

public interface IUserDAO extends JpaRepository<Users, String> {

	public Users findByUsername(String username);
	
}