package TimeUpx.ViasEVozes.Backend.dto;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Builder (setterPrefix = "with")
public record ContributionListDTO(
	Long id,
	ContributionType type,
	String name,
	String description,
	List<ContributionLink> links,
	byte[] image,
	User author,
	LocalDateTime timeOfCreation,
	boolean isAnonymous,
	int quantityOfLikes,
	ContributionStatus status
) {
	public static ContributionListDTO of(Contribution contribution) {
		return builder()
				.withId(contribution.id())
				.withType(contribution.type())
				.withName(contribution.name())
				.withDescription(contribution.description())
				.withLinks(contribution.links())
				.withImage(contribution.image().content())
				.withAuthor(contribution.author())
				.withTimeOfCreation(contribution.timeOfCreation())
				.withIsAnonymous(contribution.isAnonymous())
				.withQuantityOfLikes(contribution.quantityOfLikes())
				.withStatus(contribution.status())
				.build();
	}
}