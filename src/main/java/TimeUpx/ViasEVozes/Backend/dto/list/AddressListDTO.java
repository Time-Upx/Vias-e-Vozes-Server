package TimeUpx.ViasEVozes.Backend.dto.list;

import TimeUpx.ViasEVozes.Backend.entities.*;
import lombok.*;

@Builder (setterPrefix = "with")
public record AddressListDTO
(
	String CEP,
	String state,
	String city,
	String neighborhood,
	String street,
	String number,
	String complement
) {
	public static AddressListDTO of (Address address) {
		if (address == null) {
			return null;
		}
		return builder()
				.withCEP(address.CEP())
				.withState(address.state())
				.withCity(address.city())
				.withNeighborhood(address.neighborhood())
				.withStreet(address.street())
				.withNumber(address.number())
				.withComplement(address.complement())
				.build();
	}
}
