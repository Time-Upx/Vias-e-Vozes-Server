package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.boot.context.properties.bind.*;

import java.util.*;

@Table(name = "users")
@Entity

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with")
@EqualsAndHashCode(of = "id")
@Accessors(fluent = true)
@NoArgsConstructor
@Getter

public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@NotNull
	private String password;

	@Lob
	@Convert(converter = ImageValue.Converter.class)
	private ImageValue profilePicture;

	@Email
	@NotNull
	private String email;

	@OneToMany
	private List<Contribution> savedContributions;

	private boolean preferAnonymous = true;

	private boolean isActive = true;
}
