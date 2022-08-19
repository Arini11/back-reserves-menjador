package team5.proyecto.reservesMenjador.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import team5.proyecto.reservesMenjador.dto.Categories;

public interface ICategoriesDAO extends JpaRepository<Categories,Integer>{

}
