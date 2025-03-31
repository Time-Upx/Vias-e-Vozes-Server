package TimeUpx.ViasEVozes.Backend.dto.register;

import jakarta.validation.constraints.*;

public record ContributionLinkRegisterDTO(
		String name,
		@NotBlank
		String url
) {}
