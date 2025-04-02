package TimeUpx.ViasEVozes.Backend.dto.update;

public record AddressUpdateDTO
(
	Long id,
	String CEP,
	String state,
	String city,
	String neighborhood,
	String street,
	String number,
	String complement
) {}
