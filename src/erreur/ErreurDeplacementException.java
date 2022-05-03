package erreur;

import matrix.Direction;
/**
 * Classe erreur de deplacement.
 * En cas d'erreur de deplacement, c'est cette erreur qui est lance.
 * @author benlacheheb
 *
 */
public class ErreurDeplacementException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private Direction direction;
	
	public ErreurDeplacementException(Direction d) {
		super();
		this.direction = d;
	}

	public Direction getDirection() {
		return direction;
	}
}
