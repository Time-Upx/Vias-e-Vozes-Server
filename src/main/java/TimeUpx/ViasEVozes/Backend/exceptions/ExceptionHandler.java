package TimeUpx.ViasEVozes.Backend.exceptions;

public class ExceptionHandler
{
	public static void handle(Exception e) {
		System.out.println(e.getStackTrace());
	}
}
