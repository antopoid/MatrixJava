package erreur;

/**
 * Classe erreur d'inventaire de pierre.
 * En cas d'impossibilite de recuperer une pierre, c'est cette erreur qui est lance.
 */
import matrix.Ressource;

public class ErreurInventairePierreException extends Exception {
	private static final long serialVersionUID = 1L;

	private Ressource ressource;

	public ErreurInventairePierreException(Ressource r) {
		super();
		this.ressource = r;
	}

	public Ressource getRessource() {
		return ressource;
	}

}
