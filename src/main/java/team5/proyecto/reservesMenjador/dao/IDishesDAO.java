package team5.proyecto.reservesMenjador.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Dishes;

public interface IDishesDAO extends JpaRepository<Dishes,Integer>{

}
