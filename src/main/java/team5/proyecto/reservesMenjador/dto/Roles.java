package team5.proyecto.reservesMenjador.dto;


import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Roles {

	//ATRIBUTOS TABLA ROLES
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private @Getter @Setter int id;
	private @Getter @Setter String name;
	
	@JsonIgnore 
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "role")
	private @Getter @Setter List <Users> users;
	
	
}
