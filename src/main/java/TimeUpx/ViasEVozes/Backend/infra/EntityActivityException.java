package TimeUpx.ViasEVozes.Backend.infra;

public class EntityActivityException extends RuntimeException {

	private Class<?> type;
	private boolean activity;

	public EntityActivityException (Class<?> type, boolean activity) {
		super(String.format("%s is %s active", type.getSimpleName(), activity? "already" : "not"));
	}

}
