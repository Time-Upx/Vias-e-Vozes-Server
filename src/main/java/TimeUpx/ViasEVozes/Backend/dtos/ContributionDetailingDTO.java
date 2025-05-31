package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import lombok.*;

import java.time.*;

@Builder (setterPrefix = "with")
public record ContributionDetailingDTO(
		long id,
		String title,
		String description,
		ContributionType type,
		Link[] links,
		Image.DTO image,
		UserListingDTO author,
		LocalDateTime timeOfCreation,
		boolean isAnonymous,
		long quantityOfLikes,
		ContributionStatus status,
		boolean isActive,
		Address.DTO address
) {
	public static ContributionDetailingDTO of (Contribution contribution) {
		if (contribution == null) return null;

		return ContributionDetailingDTO.builder()
				.withId(contribution.id())
				.withType(contribution.type())
				.withTitle(contribution.title())
				.withDescription(contribution.description())
				.withLinks(contribution.links().toArray(Link[]::new))
				.withImage(Image.toDTO(contribution.image()))
				.withAuthor(UserListingDTO.of(contribution.author()))
				.withTimeOfCreation(contribution.timeOfCreation())
				.withIsAnonymous(contribution.isAnonymous())
				.withQuantityOfLikes(contribution.quantityOfLikes())
				.withStatus(contribution.status())
				.withIsActive(contribution.isActive())
				.withAddress(Address.toDTO(contribution.address()))
				.build();
	}
}
