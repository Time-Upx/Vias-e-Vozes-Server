package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dto.register.*;
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
