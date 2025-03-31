package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dto.register.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

import java.util.*;

@AllArgsConstructor (access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
@Accessors (fluent = true)
@Builder (setterPrefix = "with")
@Getter
@Entity
public class ContributionLink
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String url;

	@ManyToOne
	@JoinColumn(name = "contribution_id")
	private Contribution contribution;

	public void withContribution(Contribution contribution) {
		this.contribution = contribution;
	}

	public static ContributionLink of(ContributionLinkRegisterDTO dto) {
		if (dto == null) {
			return null;
		}
		return builder()
				.withName(dto.name())
				.withUrl(dto.url())
				.build();
	}

	public static List<ContributionLink> listFrom(ContributionLinkRegisterDTO[] dtoArray) {
		if (dtoArray == null || dtoArray.length == 0) {
			return new ArrayList<>();
		} else {
			return Arrays.stream(dtoArray).map(ContributionLink::of).toList();
		}
	}
}
