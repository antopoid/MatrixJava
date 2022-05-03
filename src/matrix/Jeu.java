package matrix;

import java.util.*;

import erreur.ErreurInventaireBoisException;
import outils.Pair;

/**
 * Classe jeu.
 * Classe qui stock toutes les methodes relatives au fonctionnement du jeu.
 * @author benlacheheb
 *
 */
public class Jeu {
	private Hero joueur;
	private Ressource[][] map;

	static List<Pair> boisCoordonnee = new ArrayList<Pair>();
	
	/**
	 * Constructeur de l'objet jeu. Un jeu se compose d'un Hero et d'une map.
	 * @param hero Nom du hero a placer en parametre.
	 * @param map Map sur laquelle jouer.
	 */
	public Jeu(String hero, Ressource[][] map) {
		joueur = new Hero(hero);
		this.map = map;
	}
	
	/**
	 * Fonction Jouer(); qui fait appel à la fonction qui dicte le comportement du joueur.
	 */
	public void jouer() {
		comportementSimpleJoueur();
	}
	
	/**
	 * Suite de verification a faire lors de chaque deplacement.
	 * La fonction verifie dans un premier temps la phase :
	 * - Si le joueur est en phase 1 avec la farine faite et les coordonnees de 5 bois enregister dans "boisCoordonnee". Alors on peut passer a la recuperation du bois et a la fabrication du feu.
	 * - Si Joueur est en phase 2 sans la Farine, alors cela veux dire qu'il manque du ble sur la map. Cela ne sert a rien de continuer, le joueur est redirige directement a la phase final.
	 * 
	 * La fonction permet de recuperer des elements.
	 * La fonction permet de fabriquer de la farine ou bois si on a tous les elements.
	 * A note que lors de la creation de la farine la pierre est utilise puis ensuite directement jetee.
	 * @return
	 */
	public int verificationJoueur() {
		int[] coordonnee = joueur.getPosition();

		if (joueur.getPhase() == 1 && joueur.getFarine() != null && boisCoordonnee.size() >= 5) {
			joueur.setPhase(2);
			return 2;//permet de passer en phase 2.
		}

		if (joueur.getPhase() == 2 && joueur.getFarine() == null) {
			joueur.setPhase(3);//permet de passer en phase 3.
			return 3;
		}

		try {
			joueur.prendre(map[coordonnee[0]][coordonnee[1]]);
			map[coordonnee[0]][coordonnee[1]] = null;
		} catch (ErreurInventaireBoisException e) {
			Pair coordoBois = new Pair(coordonnee[0], coordonnee[1]);
			boisCoordonnee.add(coordoBois);
		} catch (Exception e) {

		}

		if (joueur.getBle().size() >= 10) {
			joueur.faireFarine();
		}
		if (joueur.getBois().size() >= 5) {
			joueur.faireFeu();
		}
		if (joueur.getPierre() != null && joueur.getFarine() != null && map[coordonnee[0]][coordonnee[1]] == null) {
			joueur.jeter(joueur.getPierre());
			map[coordonnee[0]][coordonnee[1]] = new Pierre("pierre");
		}
		return 0;
	}
	
	/**
	 * Permet de ce deplacer directement au coordonnee indique en parametres.
	 * @param l Ligne a laquelle se deplacer.
	 * @param c Colonne a laquelle se deplacer.
	 * @return Si return = 3 alors on passe directement a la phase final, cela veux dire que lors de la phase 1 la farine n'a pas ete fabrique.
	 */
	public int deplacementJoueurCoordonnee(int l, int c) {

		if (joueur.getPosition()[0] > l) {
			while (l != joueur.getPosition()[0]) {
				try {
					joueur.seDeplacer(Direction.HAUT);
					affichageJeu();
				} catch (Exception e) {
				}
			}
		} else {
			while (l != joueur.getPosition()[0]) {
				try {
					joueur.seDeplacer(Direction.BAS);
					affichageJeu();
				} catch (Exception e) {
				}
			}
		}

		if (joueur.getPosition()[1] > c) {
			while (c != joueur.getPosition()[1]) {
				try {
					joueur.seDeplacer(Direction.GAUCHE);
					affichageJeu();
				} catch (Exception e) {
				}
			}
		} else {
			while (c != joueur.getPosition()[1]) {
				try {
					joueur.seDeplacer(Direction.DROITE);
					affichageJeu();
				} catch (Exception e) {
				}
			}
		}

		if (verificationJoueur() == 3)
			return 3;

		return 0;
	}
	/**
	 * Verifie si le joueur a gagner, pour ce faire la fonctionne verifie si la fabrication du pain est possible et si le joueur est bien au coordonnee (9;9).
	 */
	public void verificationWin() {
		if (joueur.fairePain() == true && joueur.getPosition()[0] == 9 && joueur.getPosition()[1] == 9) {
			System.out.println("Pain fabrique");
			System.out.println("You win avec : " + joueur.getNbPartie() + " Coups !!!");
		} else {
			System.out.println("You loose avec : " + joueur.getNbPartie() + " Coups !!!");
		}
	}
	
	/**
	 * Dicte le comportement du joueur.
	 * Comportement en 3 phases :
	 * - 1er phase : Phase de recherche du ble et de la pierre afin de faire la farine.
	 * - 2eme phase : Phase de recuperation du nombre de bois necessaire afin de faire du feu (Coordonnee des morceaux de bois enregister dans la variable "boisCoordonnee" lors de la premiere phase).
	 * - 3eme phase : le joueur est redirige vers la casse (9;9) puis verification du pain et de la position.
	 */
	public void comportementSimpleJoueur() {
		comportementPremierePhase();
		comportementDeuxiemePhase();
		comportementFinalPhase();
	}
	
	/**
	 * Comportement 1er phase, je joueur va parcourir la map de gauche a droite a la recherche de ble et de la pierre.
	 * Chaque ble ou pierre est recuperer. Si le bon nombre de ble ainsi que la pierre est recuperer la farine est cree et le joueur passe a la phase 2.
	 * @return
	 */
	public int comportementPremierePhase() {
		joueur.setPhase(1);
		affichageJeu();
		for (int l = 0; l <= 9; l++) {
			for (int c = 0; c < 9; c++) {
				if (verificationJoueur() == 2)
					return 0;

				if (l % 2 == 0) {
					try {
						affichageJeu();
						joueur.seDeplacer(Direction.DROITE);
					} catch (Exception e) {
					}
				} else {
					try {
						affichageJeu();
						joueur.seDeplacer(Direction.GAUCHE);
					} catch (Exception e) {
					}
				}

			}

			if (verificationJoueur() == 2)
				return 0;

			try {
				affichageJeu();
				joueur.seDeplacer(Direction.BAS);
			} catch (Exception e) {
			}

		}
		return 0;
	}
	
	
	/**
	 * Recupere les morceaux de bois precedemment enregiste.
	 * Quand le joueur a assez de bois, le feu est fabrique.
	 * Si deplacementJoueurCoordonnee return 3 cela veux dire que la farine n'a pas ete cree precedemment, cela ne sert a rien de continuer.
	 * Le jouer et envoye phase 3.
	 * @return
	 */
	public int comportementDeuxiemePhase() {
		joueur.setPhase(2);
		affichageJeu();
		for (int i = 0; i < boisCoordonnee.size(); i++) {
			Pair vTempo = boisCoordonnee.get(i);
			int l = vTempo.getL();
			int c = vTempo.getR();
			if (joueur.getFeu() != null)
				return 0;
			if (deplacementJoueurCoordonnee(l, c) == 3)
				return 0;
		}
		return 0;
	}

	
	/**
	 * Phase final, je joueur est envoyer en case (9;9).
	 * Le jeu verifie si les conditions pour gagne sont reunies.
	 */
	public void comportementFinalPhase() {
		joueur.setPhase(3);
		deplacementJoueurCoordonnee(9, 9);
		verificationWin();
	}
	
	/**
	 * Affiche le jeu ligne par ligne.
	 * B correspond au ble.
	 * P correspond a la pierre.
	 * W correspond au bois.
	 * "-" correspond a une case vide.
	 * La position du joueur est indique par des [].
	 */
	public void affichageJeu() {
		int positionLJoueur = joueur.getPosition()[0];
		int positionCJoueur = joueur.getPosition()[1];
		String affichage = "";

		for (int l = 0; l <= 9; l++) {
			for (int c = 0; c <= 9; c++) {
				if (map[l][c] instanceof Ble && (positionLJoueur != l || positionCJoueur != c)) {
					affichage = affichage + " B " + " ";
				} else if (map[l][c] instanceof Ble && positionLJoueur == l && positionCJoueur == c) {
					affichage = affichage + "[B]" + " ";
				} else if (map[l][c] instanceof Pierre && (positionLJoueur != l || positionCJoueur != c)) {
					affichage = affichage + " P " + " ";
				} else if (map[l][c] instanceof Pierre && positionLJoueur == l && positionCJoueur == c) {
					affichage = affichage + "[P]" + " ";
				} else if (map[l][c] instanceof Bois && (positionLJoueur != l || positionCJoueur != c)) {
					affichage = affichage + " W " + " ";
				} else if (map[l][c] instanceof Bois && positionLJoueur == l && positionCJoueur == c) {
					affichage = affichage + "[W]" + " ";
				} else if (positionLJoueur == l && positionCJoueur == c) {
					affichage = affichage + "[-]" + " ";
				} else {
					affichage = affichage + " - " + " ";
				}
			}
			System.out.println(affichage);
			affichage = "";
		}
		joueur.debugJoueur();
	}
	
	public Hero getJoueur() {
		return joueur;
	}
	
	public void setJoueur(Hero joueur) {
		this.joueur = joueur;
	}

	public Ressource[][] getMap() {
		return map;
	}

}
