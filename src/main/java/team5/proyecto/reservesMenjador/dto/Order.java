package team5.proyecto.reservesMenjador.dto;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.*;

import org.springframework.lang.NonNull;

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

	@NonNull 
	private @Getter @Setter OffsetDateTime createdOn;
	
	@NonNull 
	private @Getter @Setter OffsetDateTime modifiedOn;
	
	@NonNull 
	private @Getter @Setter OffsetDateTime deliveryOn;
	
	@NonNull @Enumerated(EnumType.STRING)
	private @Getter @Setter DeliveryStatus delivered;
	
	@NonNull @Exclude @ManyToOne @JoinColumn(name = "user_id")
	private @Getter @Setter Users user;
	
	//creacion tabla NM intermedia
	@JoinTable(name = "orders_dishes", 
			joinColumns = { @JoinColumn(name = "orders") },
			inverseJoinColumns = { @JoinColumn(name = "dish") })
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private @Getter @Setter List<Dish> dishes;

}
