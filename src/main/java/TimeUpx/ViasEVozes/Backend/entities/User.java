package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dto.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.*;

import java.time.*;
import java.util.*;

@Table(name = "Users")
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

	private String name;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	private String password;

	@Lob
	@Convert(converter = Image.Converter.class)
	private Image profilePicture;

	@Email
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

	public static User toEntity(UserRegisterDTO dto)
	{
		if (dto == null) {
			return null;
		}
		Image profilePicture =
				dto.profilePicture() == null || dto.profilePicture().content().length == 0 ?
				Image.of(new byte[0]) : dto.profilePicture();
		return builder()
				.withId(null)
				.withName(dto.name())
				.withRole(dto.role())
				.withPassword(dto.password())
				.withProfilePicture(profilePicture)
				.withEmail(dto.email())
				.withPreferAnonymous(dto.preferAnonymous())
				.withIsActive(true)
				.withDateOfArrival(LocalDateTime.now())
				.withSavedContributions(new ArrayList<>())
				.build();
	}
}
