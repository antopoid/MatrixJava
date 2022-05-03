package erreur;

/**
 * Classe erreur d'inventaire de bois.
 * En cas d'impossibilite de recuperer du bois, c'est cette erreur qui est lance.
 */
import matrix.Ressource;

public class ErreurInventaireBoisException extends Exception {
	private static final long serialVersionUID = 1L;

	private Ressource ressource;

	public ErreurInventaireBoisException(Ressource r) {
		super();
		this.ressource = r;
	}

	public Ressource getRessource() {
		return ressource;
	}

}
