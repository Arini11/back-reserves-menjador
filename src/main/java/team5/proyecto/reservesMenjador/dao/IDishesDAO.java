package team5.proyecto.reservesMenjador.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Dish;

public interface IDishesDAO extends JpaRepository<Dish,Integer>{

}
