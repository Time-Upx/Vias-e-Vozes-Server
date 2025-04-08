package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dto.register.*;
import TimeUpx.ViasEVozes.Backend.dto.update.*;
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

	public void setInactive() {
		this.isActive = false;
	}

	public User update(UserUpdateDTO dto)
	{
		if (dto == null) return null;

		if (dto.name() != null) this.name = dto.name();
		if (dto.role() != null) this.role = dto.role();
		if (dto.password() != null) this.password = dto.password();
		if (dto.pictureContent() != null) this.profilePicture = Image.of(dto.pictureContent());
		if (dto.email() != null) this.email = dto.email();
		if (dto.preferAnonymous() != null) this.preferAnonymous = dto.preferAnonymous();

		return this;
	}

	public static User of(UserRegisterDTO dto)
	{
		if (dto == null) return null;

		// Defines default values or processes dto data into more usable options
		Image image = Image.of(dto.profilePictureContent());
		LocalDateTime dateOfArrival = LocalDateTime.now();
		boolean preferAnonymous = dto.preferAnonymous() == null || dto.preferAnonymous(); // Sets default value as "true"
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
