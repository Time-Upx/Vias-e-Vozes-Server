package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.*;
import lombok.*;

@Builder (setterPrefix = "with")
public record ContributionUpdatingDTO (
		String title,
		String description,
		ContributionType type,
		@Valid Link[] links,
		Boolean isAnonymous,
		ContributionStatus status,
		@Valid Address address
) {}
