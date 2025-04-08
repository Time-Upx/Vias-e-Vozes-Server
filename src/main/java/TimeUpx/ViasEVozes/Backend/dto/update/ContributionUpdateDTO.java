package TimeUpx.ViasEVozes.Backend.dto.update;

import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder (setterPrefix = "with")
public record ContributionUpdateDTO
(
	ContributionType type,
	String name,
	String description,
	ContributionLinkUpdateDTO[] links,
	byte[] imageContent,
	Long authorId,
	Boolean isAnonymous,
	int quantityOfLikes,
	ContributionStatus status,
	AddressUpdateDTO address
) {}
