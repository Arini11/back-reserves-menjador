package team5.proyecto.reservesMenjador.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Category;

public interface ICategoriesDAO extends JpaRepository<Category,Integer>{

}
