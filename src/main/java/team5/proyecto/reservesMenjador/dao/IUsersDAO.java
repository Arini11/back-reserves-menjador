package team5.proyecto.reservesMenjador.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Users;

public interface IUsersDAO extends JpaRepository<Users, Long> {

	Users findByUsername(String username);
}
