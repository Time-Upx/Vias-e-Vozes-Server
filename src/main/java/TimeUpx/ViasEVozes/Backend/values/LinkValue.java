package TimeUpx.ViasEVozes.Backend.values;

import lombok.*;
import lombok.experimental.*;

@RequiredArgsConstructor(staticName = "of")
@Accessors(fluent = true)
@Value
public class LinkValue
{
	String name;
	String url;
}
