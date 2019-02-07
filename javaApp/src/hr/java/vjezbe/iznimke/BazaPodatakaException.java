package hr.java.vjezbe.iznimke;

public class BazaPodatakaException extends Exception{
	
	private static final long serialVersionUID = 6186263838409046177L;

	public BazaPodatakaException() {
		super("Greska u radu sa bazom podataka!");
	}
	
	public BazaPodatakaException(String message) {
		super(message);
	}
	
	public BazaPodatakaException(Throwable cause) {
		super(cause);
	}
	
	public BazaPodatakaException(String message, Throwable cause) {
		super(message, cause);
	}
}
