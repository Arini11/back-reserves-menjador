package team5.proyecto.reservesMenjador.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Category;
import team5.proyecto.reservesMenjador.dto.Dish;

public interface ICategoryDAO extends JpaRepository<Category,Integer>{

	public Category findByNameC(String name);
	
	public List<Category> findByDishes(Dish dish);
	
}