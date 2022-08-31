package team5.proyecto.reservesMenjador.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "dishes")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter int id;

	private @Getter @Setter String name;
	private @Getter @Setter byte[] image;
	private @Getter @Setter int popularity;
	private @Getter @Setter boolean status;
	
	

	//creacion tabla NM intermedia
	@JoinTable(name = "categories_dishes", 
			joinColumns = { @JoinColumn(name = "dish") },
			inverseJoinColumns = { @JoinColumn(name = "category") })
	
	//creacion atributo de categorias que puede tener ese plato(asignadas a traves
	//de la tabla categories_dishes;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private @Getter @Setter List<Category> categories  = new ArrayList<>();
	
	@JsonIgnore	@Exclude @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dishes")
	private @Getter @Setter List<Order> orders  = new ArrayList<>();

	public Dish(int id) {
		super();
		this.id = id;
	}
}
