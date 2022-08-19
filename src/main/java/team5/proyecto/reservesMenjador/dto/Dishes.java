package team5.proyecto.reservesMenjador.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="dishes")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dishes {
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter int id;
	
	private @Getter @Setter String nameD;	
	private @Getter @Setter Byte[] image;	
    private @Getter @Setter int popularity;
    
    
	@JsonIgnore	@Exclude @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private @Getter @Setter List<Categories_dishes> catg_dishes;
    
    @JsonIgnore	@Exclude @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private @Getter @Setter List<DishesOrders> dishesOrders;
    
    /*
     * creacion tabla intermedia a traves JoinTable en dishes
	 * @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	 * 
	 * @JoinTable( name = "categories_dishes", joinColumns = {@JoinColumn(name =
	 * "idDish")}, inverseJoinColumns = {@JoinColumn(name = "idCategory")} )
	 */
}
