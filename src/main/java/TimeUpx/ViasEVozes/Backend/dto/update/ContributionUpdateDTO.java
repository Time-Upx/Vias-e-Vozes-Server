package TimeUpx.ViasEVozes.Backend.dto.update;

import TimeUpx.ViasEVozes.Backend.dto.register.*;
import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.constraints.*;

public record ContributionUpdateDTO
(
	@NotNull
	Long id,
	@NotNull
	User responsible,
	ContributionType type,
	String name,
	String description,
	ContributionLinkUpdateDTO[] links,
	byte[] imageContent,
	Boolean isAnonymous,
	ContributionStatus status,
	AddressUpdateDTO address
) {}
