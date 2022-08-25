package team5.proyecto.reservesMenjador.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "categories")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter int id;

	private @Getter @Setter String name;

	@JsonIgnore	@Exclude @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	private @Getter @Setter List<Dish> dishes;
}
