package TimeUpx.ViasEVozes.Backend.dto;

import TimeUpx.ViasEVozes.Backend.entities.*;
import TimeUpx.ViasEVozes.Backend.values.*;

public record ContributionRegisterDTO (
	String name,
	String description,
	ContributionType type,
	ContributionLinkRegisterDTO[] links,
	Image image,
	Long authorId,
	boolean isAnonymous
) {}
