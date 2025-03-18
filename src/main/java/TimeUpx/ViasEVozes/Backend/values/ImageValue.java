package TimeUpx.ViasEVozes.Backend.values;

import TimeUpx.ViasEVozes.Backend.exceptions.*;
import io.micrometer.common.lang.NonNullApi;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.core.io.*;
import org.springframework.lang.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.nio.file.*;

@RequiredArgsConstructor(staticName = "of")
@Accessors(fluent = true)
@Value
public class ImageValue
{
	byte[] content;

	@jakarta.persistence.Converter(autoApply = true)
	public static class Converter implements AttributeConverter<ImageValue, byte[]> {

		@Override public byte[] convertToDatabaseColumn(ImageValue image) {
			return image.content;
		}

		@Override public ImageValue convertToEntityAttribute(byte[] data) {
			return ImageValue.of(data);
		}
	}
}
