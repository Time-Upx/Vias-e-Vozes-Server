package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dtos.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

import java.time.*;
import java.util.*;

import static TimeUpx.ViasEVozes.Backend.values.Address.toDTO;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@EqualsAndHashCode (of = {"id"})
@Accessors (fluent = true)
@Builder (setterPrefix = "with")
@Getter
@Entity
@Table (name = "contributions")
public class Contribution {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated (EnumType.STRING)
	private ContributionType type;

	private String title;

	@Column (columnDefinition = "TEXT")
	private String description;

	@ElementCollection
	@CollectionTable(name = "contribution_links", joinColumns = @JoinColumn(name = "contribution_id"))
	private List<Link> links;

	@Embedded
	@Setter
	private Image image;

	@ManyToOne
	@JoinColumn (name = "author_id")
	private User author;

	private LocalDateTime timeOfCreation;

	private boolean isAnonymous;

	private int quantityOfLikes;
	public Contribution adjustLikes(int value) {
		quantityOfLikes += value;
		if (quantityOfLikes < 0) quantityOfLikes = 0;
		return this;
	}

	private ContributionStatus status;

	@Setter
	private boolean isActive;

	@Embedded
	private Address address;

	@ManyToMany
	@JoinTable (
			name = "user_favorites",
			joinColumns = @JoinColumn (name = "contribution_id"),
			inverseJoinColumns = @JoinColumn (name = "user_id")
	)
	private Set<User> favoritedBy;

	public ContributionDetailingDTO details() {
		return ContributionDetailingDTO.of(this);
	}

	public Contribution update (ContributionUpdatingDTO dto) {
		if (dto == null) return null;

		if (dto.type() != null) type = dto.type();
		if (dto.title() != null) title = dto.title();
		if (dto.description() != null) description = dto.description();
		if (dto.isAnonymous() != null) isAnonymous = dto.isAnonymous();
		if (dto.status() != null) status = dto.status();
		if (dto.address() != null) address = Address.of(dto.address());
		if (dto.links() != null) links = Arrays.asList(dto.links());

		return this;
	}

	public static Contribution of (ContributionSavingDTO dto, User author) {
		if (dto == null) return null;

		// Sets default value as "true"
		boolean isAnonymous = dto.isAnonymous() == null || dto.isAnonymous();

		return builder().withType(dto.type())
				.withTitle(dto.title())
				.withDescription(dto.description())
				.withImage(Image.draft(dto.imagePlaceholder()))
				.withAuthor(author)
				.withLinks(dto.links() != null ? Arrays.asList(dto.links()) : new ArrayList<>())
				.withTimeOfCreation(LocalDateTime.now())
				.withIsAnonymous(isAnonymous)
				.withQuantityOfLikes(0)
				.withStatus(ContributionStatus.ANALYSING)
				.withIsActive(true)
				.withAddress(Address.of(dto.address()))
				.withFavoritedBy(new HashSet<>())
				.build();
	}
}
