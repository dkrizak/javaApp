package hr.java.vjezbe.iznimke;
/**
 * Predstavlja iznimku koja sa baca u sluèaju kad postoji više najmlaðih studenata.
 * 
 * @author Denis Križak
 *
 */
public class PostojiViseNajmladjihStudenataException extends RuntimeException {

	private static final long serialVersionUID = 1324053465120359404L;
	
	public PostojiViseNajmladjihStudenataException() {
		super("Postoji više najmladjih studenata!");
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
