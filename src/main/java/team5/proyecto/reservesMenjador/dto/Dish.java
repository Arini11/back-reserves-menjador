package team5.proyecto.reservesMenjador.dto;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter int id;

	private @Getter @Setter String nameD;
	private @Getter @Setter byte[] image;
	private @Getter @Setter int popularity;

	@JsonIgnore
	@Exclude
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "categories_dishes", 
			joinColumns = { @JoinColumn(name = "dish") },
			inverseJoinColumns = { @JoinColumn(name = "category") })
	private @Getter @Setter List<Category> categories;

	
	//esta tabla podria crearse en dishes o en order, desde dish acceder a 
	//sus comandas o desde comanda ver que platos hay?
	
	@JsonIgnore
	@Exclude
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
	private @Getter @Setter List<DishesOrders> dishesOrders;

	public Dish(int id) {
		this.id = id;
	}

}
