package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dtos.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

import java.time.*;
import java.util.*;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@EqualsAndHashCode (of = {"email", "name", "password"})
@Accessors (fluent = true)
@Builder (setterPrefix = "with")
@Getter
@Entity
@Table (name = "users")
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Enumerated (EnumType.STRING)
	private UserRole role;

	private String password;

	@Embedded
	@Setter
	private Image profilePicture;

	private String email;

	private boolean preferAnonymous;

	private LocalDateTime dateOfArrival;

	@ManyToMany
	@JoinTable (
			name = "user_favorites",
			joinColumns = @JoinColumn (name = "user_id"),
			inverseJoinColumns = @JoinColumn (name = "contribution_id")
	)
	private Set<Contribution> favorites;

	@Setter
	private boolean isActive;

	public UserDetailingDTO details() {
		return UserDetailingDTO.of(this);
	}

	public User update (UserUpdatingDTO dto) {
		if (dto == null) return null;

		if (dto.preferAnonymous() != null) this.preferAnonymous = dto.preferAnonymous();
		if (dto.password() != null) this.password = dto.password();
		if (dto.email() != null) this.email = dto.email();
		if (dto.name() != null) this.name = dto.name();
		if (dto.role() != null) this.role = dto.role();

		return this;
	}

	public static User of (UserSavingDTO dto) {
		if (dto == null) return null;

		// Sets default value as "true"
		boolean preferAnonymous = dto.preferAnonymous() == null || dto.preferAnonymous();

		return User.builder()
				.withName(dto.name())
				.withRole(dto.role())
				.withPassword(dto.password())
				.withProfilePicture(Image.draft(dto.picturePlaceholder()))
				.withEmail(dto.email())
				.withPreferAnonymous(preferAnonymous)
				.withIsActive(true)
				.withDateOfArrival(LocalDateTime.now())
				.withFavorites(new HashSet<>())
				.build();
	}
}
