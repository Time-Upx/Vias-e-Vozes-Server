package TimeUpx.ViasEVozes.Backend.dtos;

import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

public record ContributionSavingDTO(
		@NotBlank String title,
		@NotBlank String description,
		@NotNull ContributionType type,
		@Valid Link[] links,
		@NotBlank String imagePlaceholder,
		@NotNull Long authorId,
		Boolean isAnonymous,
		@Valid @NotNull Address.DTO address
) {}
