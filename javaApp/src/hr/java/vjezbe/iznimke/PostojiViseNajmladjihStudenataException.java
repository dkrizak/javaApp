package hr.java.vjezbe.iznimke;
/**
 * Predstavlja iznimku koja sa baca u slu�aju kad postoji vi�e najmla�ih studenata.
 * 
 * @author Denis Kri�ak
 *
 */
public class PostojiViseNajmladjihStudenataException extends RuntimeException {

	private static final long serialVersionUID = 1324053465120359404L;
	
	public PostojiViseNajmladjihStudenataException() {
		super("Postoji vi�e najmladjih studenata!");
	}
	
	public PostojiViseNajmladjihStudenataException(String message) {
		super(message);
	}
	
	public PostojiViseNajmladjihStudenataException(Throwable cause) {
		super(cause);
	}
	
	public PostojiViseNajmladjihStudenataException(String message, Throwable cause) {
		super(message, cause);
	}
}
