package TimeUpx.ViasEVozes.Backend.dto.list;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import lombok.*;

import java.time.*;

@Builder(setterPrefix = "with")
public record ContributionListDTO(
		Long id,
		ContributionType type,
		String name,
		String description,
		ContributionLinkListDTO[] links,
		byte[] imageContent,
		UserListDTO author,
		LocalDateTime timeOfCreation,
		boolean isAnonymous,
		int quantityOfLikes,
		ContributionStatus status,
		AddressListDTO address
) {
	public static ContributionListDTO of(Contribution contribution)
	{
		if (contribution == null) return null;

		var links = ContributionLinkListDTO.fromList(contribution.links());
		byte[] imageContent = contribution.image().content();
		var author = UserListDTO.of(contribution.author());
		var address = AddressListDTO.of(contribution.address());

		return builder()
				.withId(contribution.id())
				.withType(contribution.type())
				.withName(contribution.name())
				.withDescription(contribution.description())
				.withLinks(links)
				.withImageContent(imageContent)
				.withAuthor(author)
				.withTimeOfCreation(contribution.timeOfCreation())
				.withIsAnonymous(contribution.isAnonymous())
				.withQuantityOfLikes(contribution.quantityOfLikes())
				.withStatus(contribution.status())
				.withAddress(address)
				.build();
	}
}