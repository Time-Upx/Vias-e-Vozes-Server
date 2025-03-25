package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.dto.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

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

	public static ContributionLink of(ContributionLinkRegisterDTO dto) {
		return builder()
				.withId(null)
				.withName(dto.name())
				.withUrl(dto.url())
				.withContribution(null)
				.build();
	}
}
