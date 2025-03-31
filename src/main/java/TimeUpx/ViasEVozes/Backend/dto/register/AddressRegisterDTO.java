package TimeUpx.ViasEVozes.Backend.dto.register;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder (setterPrefix = "with")
public record AddressRegisterDTO
(
	@NotBlank
	@Pattern(regexp = "^\\d{5}-\\d{3}$")
	String CEP,

	@NotBlank
	String state,

	@NotBlank
	String city,

	@NotBlank
	String neighborhood,

	String street,

	String number,

	String complement
) {}
