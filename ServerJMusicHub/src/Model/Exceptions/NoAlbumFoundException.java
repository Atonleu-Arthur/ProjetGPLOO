package Model.Exceptions;

public class NoAlbumFoundException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NoAlbumFoundException (String msg) {
		super(msg);
	}
}