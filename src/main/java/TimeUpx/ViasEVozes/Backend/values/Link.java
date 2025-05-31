package TimeUpx.ViasEVozes.Backend.values;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor (access = AccessLevel.PROTECTED)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Getter
@Setter
@Embeddable
public class Link {
	public String display;
	@NotBlank
	public String url;
}
