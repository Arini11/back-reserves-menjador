package team5.proyecto.reservesMenjador.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "orders")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter int id;

	@Temporal(TemporalType.TIMESTAMP)
	private @Getter @Setter Date createdOn;
	
	@Temporal(TemporalType.TIMESTAMP)
	private @Getter @Setter Date modifiedOn;
	
	@Temporal(TemporalType.TIMESTAMP)
	private @Getter @Setter Date deliveryOn;
	
	private @Getter @Setter char delivered;
	
	@Exclude @ManyToOne	@JoinColumn(name = "user_id")
	private @Getter @Setter Users user;
	
	//creacion tabla NM intermedia
	@JoinTable(name = "orders_dishes", 
			joinColumns = { @JoinColumn(name = "orders") },
			inverseJoinColumns = { @JoinColumn(name = "dish") })
	
	@Exclude @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private @Getter @Setter List<Dish> dishes;

}
