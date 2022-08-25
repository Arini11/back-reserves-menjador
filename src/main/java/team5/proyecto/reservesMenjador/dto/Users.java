package team5.proyecto.reservesMenjador.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="users")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	
		@Id
		private @Getter @Setter String username;
		private @Getter @Setter String email;
		
		@ManyToOne @JoinColumn(name="role_id")
		private @Getter @Setter Roles role;
		
}
