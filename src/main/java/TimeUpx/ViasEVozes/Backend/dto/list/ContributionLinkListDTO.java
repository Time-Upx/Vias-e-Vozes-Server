package TimeUpx.ViasEVozes.Backend.dto.list;

import TimeUpx.ViasEVozes.Backend.entities.*;
import lombok.*;

import java.util.*;

@Builder (setterPrefix = "with")
public record ContributionLinkListDTO(
		Long id,
		String name,
		String url
) {
	public static ContributionLinkListDTO of (ContributionLink link) {
		if (link == null) {
			return null;
		}
		return builder()
				.withId(link.id())
				.withName(link.name())
				.withUrl(link.url())
				.build();
	}

	public static ContributionLinkListDTO[] fromList(List<ContributionLink> linkList) {
		return linkList.stream()
				.map(ContributionLinkListDTO::of)
				.toArray(ContributionLinkListDTO[]::new);
	}
}
