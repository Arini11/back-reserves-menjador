package team5.proyecto.reservesMenjador.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")	
    private @Getter @Setter List<Categories_dishes> catg_dishes;

    //@ManyToMany(fetch = FetchType.LAZY,mappedBy = "idCategory")
}
