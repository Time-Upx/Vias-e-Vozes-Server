package TimeUpx.ViasEVozes.Backend.values;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Accessors(fluent = true)
@Value
public class Image
{
	byte[] content;

	private Image(byte[] data) {
		this.content = data;
	}
	public static Image of(byte[] data) {
		if (data == null || data.length == 0) {
			return new Image(new byte[0]);
		} else {
			return new Image(data);
		}
	}

	@jakarta.persistence.Converter(autoApply = true)
	public static class Converter implements AttributeConverter<Image, byte[]> {
		@Override public byte[] convertToDatabaseColumn(Image image) {
			return image.content;
		}
		@Override public Image convertToEntityAttribute(byte[] data) {
			return Image.of(data);
		}
	}
}
