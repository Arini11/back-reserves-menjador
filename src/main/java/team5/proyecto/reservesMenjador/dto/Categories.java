package team5.proyecto.reservesMenjador.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="categories")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Categories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter int id;
	
	private @Getter @Setter String nameC;
	
	@JsonIgnore
	@Exclude
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "categories")
    private @Getter @Setter List<Dishes> dishes;

    //por si volvemos a basico @OneToMany(fetch = FetchType.LAZY, mappedBy = "idCategory")
}
