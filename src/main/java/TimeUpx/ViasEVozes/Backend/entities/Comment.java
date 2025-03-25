package TimeUpx.ViasEVozes.Backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.*;


@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with")
@EqualsAndHashCode(of = "id")
@Accessors(fluent = true)
@NoArgsConstructor
@Getter

public class Comment
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@NotNull
	private Contribution contribution;

	@OneToOne
	@NotNull
	private User author;

	@NotNull
	private String text;
}
