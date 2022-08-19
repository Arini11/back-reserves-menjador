package team5.proyecto.reservesMenjador.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

//creacion de esta tabla a traves de Dishes - ver como seria mejor
@Entity
@Table(name="categories_dishes")
public class Categories_dishes {
	@Id
	@ManyToOne
    @JoinColumn(name = "idDish")
    Dishes dish;
 
	@Id
    @ManyToOne
    @JoinColumn(name = "idCategory")
    Categories category;

}
