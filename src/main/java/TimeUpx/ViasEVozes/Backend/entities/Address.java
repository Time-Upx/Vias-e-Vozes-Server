package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dto.register.*;
import TimeUpx.ViasEVozes.Backend.dto.update.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
@Builder (setterPrefix = "with")
@EqualsAndHashCode (of = "id")
@Accessors (fluent = true)
@NoArgsConstructor
@Getter
@Entity
public class Address
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	private String CEP;

	private String state;

	private String city;

	private String neighborhood;

	private String street;

	private String number;

	private String complement;

	@OneToOne
	@JoinColumn (name = "address_id")
	private Contribution contribution;

	public void withContribution(Contribution contribution) {
		this.contribution = contribution;
	}

	public Address update(AddressUpdateDTO dto)
	{
		if (dto == null) return null;

		if (dto.neighborhood() != null) neighborhood = dto.neighborhood();
		if (dto.complement() != null) complement = dto.complement();
		if (dto.street() != null) street = dto.street();
		if (dto.number() != null) number = dto.number();
		if (dto.state() != null) state = dto.state();
		if (dto.city() != null) city = dto.city();
		if (dto.CEP() != null) CEP = dto.CEP();

		return this;
	}

	public static Address of (AddressRegisterDTO dto)
	{
		if (dto == null) {
			return null;
		}

		return builder()
				.withCEP(dto.CEP())
				.withState(dto.state())
				.withCity(dto.city())
				.withNeighborhood(dto.neighborhood())
				.withStreet(dto.street())
				.withNumber(dto.number())
				.withComplement(dto.complement())
				.build();
	}
}
