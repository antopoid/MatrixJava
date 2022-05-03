package erreur;

import matrix.Ressource;

/**
 * Classe erreur d'inventaire de ble.
 * En cas d'impossibilite de recuperer du ble, c'est cette erreur qui est lance.
 * @author benlacheheb
 *
 */
public class ErreurInventaireBleException extends Exception {
	private static final long serialVersionUID = 1L;

	private Ressource ressource;

	public ErreurInventaireBleException(Ressource r) {
		super();
		this.ressource = r;
	}

	public Ressource getRessource() {
		return ressource;
	}

}
