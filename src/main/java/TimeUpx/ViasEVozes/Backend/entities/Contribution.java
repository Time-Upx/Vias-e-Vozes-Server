package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dto.*;
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

	public static Contribution of(ContributionRegisterDTO dto, UserService userService)
	{
		Image image =
				dto.image() == null || dto.image().content().length == 0 ?
				Image.of(new byte[0]) : dto.image();

		User author = userService.retrieveFromId(dto.authorId());

		List<ContributionLink> links =
				dto.links() == null ?
				new ArrayList<>() : Arrays.stream(dto.links()).map(ContributionLink::of).toList();

		return builder()
				.withId(null)
				.withType(dto.type())
				.withName(dto.name())
				.withDescription(dto.description())
				.withLinks(links)
				.withImage(image)
				.withAuthor(author)
				.withTimeOfCreation(LocalDateTime.now())
				.withIsAnonymous(dto.isAnonymous())
				.withQuantityOfLikes(0)
				.withStatus(ContributionStatus.ANALYSING)
				.withIsActive(true)
				.build();
	}
}
