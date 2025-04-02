package TimeUpx.ViasEVozes.Backend.dto.register;

import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

public record ContributionRegisterDTO
(
	@NotBlank
	String name,

	@NotBlank
	String description,

	@NotNull
	ContributionType type,

	@Valid
	ContributionLinkRegisterDTO[] links,

	byte[] imageContent,

	@NotNull
	Long authorId,

	Boolean isAnonymous,

	@Valid
	@NotNull
	AddressRegisterDTO address
) {}
