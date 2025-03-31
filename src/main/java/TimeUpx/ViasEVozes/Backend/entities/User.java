package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dto.register.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

import java.time.*;
import java.util.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with")
@EqualsAndHashCode(of = "id")
@Accessors(fluent = true)
@NoArgsConstructor
@Getter
@Entity
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	private String password;

	@Lob
	@Convert(converter = Image.Converter.class)
	private Image profilePicture;

	private String email;

	private boolean preferAnonymous;

	private boolean isActive;

	private LocalDateTime dateOfArrival;

	@ManyToMany
	@JoinTable(
			name = "saved_contributions",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "contribution_id")
	)
	private List<Contribution> savedContributions;

	public static User of(UserRegisterDTO dto)
	{
		if (dto == null) {
			return null;
		}

		// Defines default values or processes dto data into more usable options
		Image image = Image.of(dto.profilePictureContent());
		LocalDateTime dateOfArrival = LocalDateTime.now();
		// Sets default value as "true"
		boolean preferAnonymous = dto.preferAnonymous() == null || dto.preferAnonymous();
		boolean isActive = true;
		List<Contribution> savedContributions = new ArrayList<>();

		return builder()
				.withName(dto.name())
				.withRole(dto.role())
				.withPassword(dto.password())
				.withProfilePicture(image)
				.withEmail(dto.email())
				.withPreferAnonymous(preferAnonymous)
				.withIsActive(isActive)
				.withDateOfArrival(dateOfArrival)
				.withSavedContributions(savedContributions)
				.build();
	}
}
