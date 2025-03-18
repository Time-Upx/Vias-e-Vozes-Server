package TimeUpx.ViasEVozes.Backend.entities;

import TimeUpx.ViasEVozes.Backend.values.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.*;

import java.util.*;

@Table(name = "contributions")
@Entity

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with")
@EqualsAndHashCode(of = "id")
@Accessors(fluent = true)
@NoArgsConstructor
@Getter

public class Contribution
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ContributionType type;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@OneToMany
	private List<LinkValue> links;

	@Lob
	@Convert(converter = ImageValue.Converter.class)
	private ImageValue image;

	@OneToOne
	@NotNull
	private User author;

	private boolean isAnonymous;

	private int quantityOfLikes;

	private ContributionStatus status = ContributionStatus.ANALYSING;

	private boolean isActive = true;
}
