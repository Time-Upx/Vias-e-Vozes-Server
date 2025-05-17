package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import lombok.*;

import java.time.*;

@Builder (setterPrefix = "with")
public record ContributionListingDTO(
		Long id,
		String title,
		ContributionType type,
		LocalDateTime timeOfCreation,
		Boolean isAnonymous,
		ContributionStatus status
) {
	public static ContributionListingDTO of (Contribution contribution) {
		if (contribution == null) return null;

		return builder().withId(contribution.id())
				.withType(contribution.type())
				.withTitle(contribution.title())
				.withTimeOfCreation(contribution.timeOfCreation())
				.withIsAnonymous(contribution.isAnonymous())
				.withStatus(contribution.status())
				.build();
	}
}