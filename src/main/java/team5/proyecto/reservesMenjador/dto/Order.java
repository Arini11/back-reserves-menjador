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

	@Temporal(TemporalType.DATE)
	private @Getter @Setter Date date;
	
	@Exclude @ManyToOne	@JoinColumn(name = "user_id")
	private @Getter @Setter Users user;
	
	@JsonIgnore	@Exclude @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private @Getter @Setter List<DishesOrders> dishesOrders;
}
