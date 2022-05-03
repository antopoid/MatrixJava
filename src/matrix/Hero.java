package matrix;

import java.util.*;

import erreur.ErreurDeplacementException;
import erreur.ErreurInventaireBleException;
import erreur.ErreurInventaireBoisException;
import erreur.ErreurInventairePierreException;

/**
 * Classe Hero.
 * Classe qui stock toutes les methodes relatives au fonctionnement du Hero.
 * @author benlacheheb
 *
 */
public class Hero {
	private String nom;
	private List<Ressource> ble;
	private List<Ressource> bois;
	private Pierre pierre;
	private ObjetManufacture farine;
	private ObjetManufacture feu;
	private int poids;
	private int nbPartie;
	private int[] position;
	private int phase;

	public Hero(String nom) {
		this.nom = nom;
		this.phase = 0;

		this.ble = new ArrayList<Ressource>();
		this.bois = new ArrayList<Ressource>();
		this.pierre = null;

		this.farine = null;
		this.feu = null;

		this.poids = 0;
		this.nbPartie = 0;

		this.position = new int[2];
		position[0] = 0;
		position[1] = 0;
	}
	
	/**
	 * Fonction debug, elle permet d'afficher tous les parametres d'un joueur afin de mieux voir l'avancement du jeu.
	 */
	public void debugJoueur() {
		System.out.println("Pseudo : " + nom);
		System.out.println("Phase : " + phase);
		System.out.println("Nombre de coups : " + nbPartie);
		System.out.println("Nombre de bois : " + bois.size());
		System.out.println("Nombre de ble : " + ble.size());
		System.out.println("Poids : " + poids);
		System.out.println("L : " + position[0] + " | C : " + position[1]);
		if (pierre != null) {
			System.out.println("Pierre OK");
		}
		if (farine != null) {
			System.out.println("Farine OK");
		}
		if (feu != null) {
			System.out.println("Feu OK");
		}
		System.out.println("============================================");
	}
	
	/**
	 * Fonction qui permet de ce deplacer.
	 * @param Prend en parametres une direction, les coordonnees du joueur sont modifier en fonction de la direction donner en parametres.
	 * @throws ErreurDeplacementException est lance, en cas de sortie de map.
	 */
	public void seDeplacer(Direction direction) throws ErreurDeplacementException {
		switch (direction) {
		case HAUT:
			if ((position[0] -= 1) < 0) {
				position[0] += 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		case BAS:
			if ((position[0] += 1) > 9) {
				position[0] -= 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		case GAUCHE:
			if ((position[1] -= 1) < 0) {
				position[1] += 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		case DROITE:
			if ((position[1] += 1) > 9) {
				position[1] -= 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		}
		nbPartie += 1;
	}
	
	/**
	 * Fonction qui permet de prendre un objet. La fonction bloque le ramassage de bois tant que la farine n'est pas fabrique.
	 * Cela permet de ramasser la pierre ainsi que le ble en premier afin de fabriquer de la farine.
	 * @param Prend en parametres une ressource r.
	 * @throws ErreurInventaireBoisException est lance, Si la farine n'est pas cree impossible de ramasser du bois. 
	 * @throws ErreurInventaireBleException est lance, Si la farine est cree, il n'est plus necessaire de ramasser du ble.
	 * @throws ErreurInventairePierreException est lance. Si la farine est cree ou que nous avons deja une pierre sur nous, il n'est plus necessaire de ramasser une pierre. 
	 */
	public void prendre(Ressource r) throws ErreurInventaireBoisException,ErreurInventaireBleException,ErreurInventairePierreException{
		if (r instanceof Ble) {
			if (ble.size() <= 9 && farine == null) {
				ble.add(r);
				poids += r.getPoids();
			}else {
				throw new ErreurInventaireBleException(r);
			}
		} else if (r instanceof Pierre) {
			if (pierre == null && farine == null) {
				pierre = new Pierre("pierre");
				poids += r.getPoids();
			}else {
				throw new ErreurInventairePierreException(r);
			}
		} else if (r instanceof Bois) {
			if (bois.size() <= 4 && farine != null && feu == null) {
				bois.add(r);
				poids += r.getPoids();
			}else {
				throw new ErreurInventaireBoisException(r);
			}
		}
	}
	
	/**
	 * Permet de jeter le type d'element donner en parametre.
	 * @param prend en parametre une ressource r.
	 */
	public void jeter(Ressource r) {
		if (r instanceof Pierre) {
			if (pierre != null) {
				pierre = null;
				poids -= r.getPoids();
			}
		} else if (r instanceof Bois) {
			bois.remove(bois.size() - 1); //C'est le dernier morceau de bois ajoute a l'inventaire qui est jete.
			poids -= r.getPoids();
		} else if (r instanceof Ble) {
			ble.remove(ble.size() - 1);//C'est le dernier ble ajoute a l'inventaire qui est jete.
			poids -= r.getPoids();
		}
	}
	
	/**
	 * Permet de faire du pain.
	 * @return Retourne le resultat de la fabrication du pain.
	 */
	public boolean fairePain() {
		boolean resultat;

		if (farine != null && feu != null) {
			resultat = true;
		} else {
			resultat = false;
		}
		return resultat;
	}
	
	/**
	 * Permet de faire de la farine si les conditions sont respectees.
	 */
	public void faireFarine() {
		if (ble.size() == 10 && farine == null && pierre != null) {
			ble.clear();
			farine = new ObjetManufacture("farine");
			poids -= 10;
		}
	}
	
	/**
	 * permet de faire du feu si les conditions sont respectees.
	 */
	public void faireFeu() {
		if (bois.size() == 5 && feu == null) {
			bois.clear();
			feu = new ObjetManufacture("feu");
			poids -= 10;
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Ressource> getBle() {
		return ble;
	}

	public void setBle(List<Ressource> ble) {
		this.ble = ble;
	}

	public List<Ressource> getBois() {
		return bois;
	}

	public void setBois(List<Ressource> bois) {
		this.bois = bois;
	}

	public Pierre getPierre() {
		return pierre;
	}

	public void setPierre(Pierre pierre) {
		this.pierre = pierre;
	}

	public ObjetManufacture getFarine() {
		return farine;
	}

	public void setFarine(ObjetManufacture farine) {
		this.farine = farine;
	}

	public ObjetManufacture getFeu() {
		return feu;
	}

	public void setFeu(ObjetManufacture feu) {
		this.feu = feu;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public int getNbPartie() {
		return nbPartie;
	}

	public void setNbPartie(int nbPartie) {
		this.nbPartie = nbPartie;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}
}
