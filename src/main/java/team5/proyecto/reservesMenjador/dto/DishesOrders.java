package team5.proyecto.reservesMenjador.dto;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "dishes_orders")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DishesOrders {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Exclude @ManyToOne @JoinColumn(name = "dish")
	private @Getter @Setter Dishes dish;

	@Exclude @ManyToOne @JoinColumn(name = "order")
	private @Getter @Setter Order order;

}
