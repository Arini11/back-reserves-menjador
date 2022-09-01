package team5.proyecto.reservesMenjador.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Category;

public interface ICategoryDAO extends JpaRepository<Category,Integer>{

	public Category findByName(String name);	
	
}