package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dto.register.*;
import TimeUpx.ViasEVozes.Backend.services.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

import java.time.*;
import java.util.*;


@AllArgsConstructor (access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
@Accessors (fluent = true)
@Builder (setterPrefix = "with")
@Getter
@Entity
public class Contribution
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ContributionType type;

	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;

	@OneToMany(mappedBy = "contribution", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContributionLink> links;

	@Lob
	@Convert(converter = Image.Converter.class)
	private Image image;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	private LocalDateTime timeOfCreation;

	private boolean isAnonymous;

	private int quantityOfLikes;

	private ContributionStatus status;

	private boolean isActive;

	@OneToOne (mappedBy = "contribution", cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "contribution_id")
	private Address address;

	public static Contribution of(ContributionRegisterDTO dto, UserService userService)
	{
		if (dto == null) {
			return null;
		}

		Image image = Image.of(dto.imageContent());
		User author = userService.retrieveFromId(dto.authorId());
		List<ContributionLink> links = ContributionLink.listFrom(dto.links());
		LocalDateTime timeOfCreation = LocalDateTime.now();
		// Sets default value as "true"
		boolean isAnonymous = dto.isAnonymous() == null || dto.isAnonymous();
		Address address = Address.of(dto.address());

		Contribution contribution = builder()
				.withType(dto.type())
				.withName(dto.name())
				.withDescription(dto.description())
				.withImage(image)
				.withAuthor(author)
				.withLinks(links)
				.withTimeOfCreation(timeOfCreation)
				.withIsAnonymous(isAnonymous)
				.withQuantityOfLikes(0)
				.withStatus(ContributionStatus.ANALYSING)
				.withIsActive(true)
				.withAddress(address)
				.build();

		// Setting back reference in needed attributes
		contribution.links.forEach(l -> l.withContribution(contribution));
		contribution.address.withContribution(contribution);

		return contribution;
	}
}
