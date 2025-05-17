package TimeUpx.ViasEVozes.Backend.values;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.*;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Embeddable
@Accessors (fluent = true)
@Builder (setterPrefix = "with")
@Getter
public class Address {
	private long cep;
	private String state;
	private String city;
	private String neighborhood;
	private String street;
	private int number;
	private String complement;

	public record DTO(
			@NotNull long cep,
			@NotBlank String state,
			@NotBlank String city,
			String neighborhood,
			String street,
			int number,
			String complement
	) {}

	public static DTO toDTO (Address address) {
		if (address == null) return null;
		return new DTO(
				address.cep,
				address.state,
				address.city,
				address.neighborhood,
				address.street,
				address.number,
				address.complement
		);
	}

	public static Address of (DTO dto) {
		return Address.builder()
				.withCep(dto.cep())
				.withState(dto.state())
				.withCity(dto.city())
				.withNeighborhood(dto.neighborhood())
				.withStreet(dto.street())
				.withNumber(dto.number())
				.withComplement(dto.complement())
				.build();
	}
}
