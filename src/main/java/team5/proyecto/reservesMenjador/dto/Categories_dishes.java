package team5.proyecto.reservesMenjador.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="categories_dishes")
public class Categories_dishes {
	@Id
	@ManyToOne
    @JoinColumn(name = "idDish")
    private @Getter @Setter Dishes dish;
 
	@Id
    @ManyToOne
    @JoinColumn(name = "idCategory")
    private @Getter @Setter Categories category;

}

//creacion de esta tabla a traves de Dishes - ver como seria mejor