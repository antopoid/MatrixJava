package matrix;

import java.util.Scanner;
/**
 * Main du programme.
 * Lance les fonctions qui permette de creer le jeu.
 * @author benlacheheb
 *
 */
public class Main {
	/**
	 * Main du programme.
	 * Recupere le pseudo du joueur.
	 * Cree l'objet jeu qui est constitue d'un nom de joueur et d'une map.
	 * Si la taille du pseudo == 0 alors le joueur sera nomme automatiquement "Joueur Inconnu".
	 * Lance le jeu via la fonction jouer.
	 * @param args
	 */
	public static void main(String[] args) {
		Jeu jeu;
		
		System.out.println("Bonjour et bienvenue dans la matrix NEO !!!");
		String pseudo = enregistrementPseudo();
		
		if(pseudo.length() == 0) {
			jeu = new Jeu("Joueur Inconnu", Terrain.creationTerrain());
		}else {
			jeu = new Jeu(pseudo, Terrain.creationTerrain());
		}
		
		jeu.jouer();
	}
	
	/**
	 * Permet d'enregister le pseudo du joueur.
	 * @return renvoie le pseudo que le joueur a tape.
	 */
	public static String enregistrementPseudo() {
		String pseudo = "";
		Scanner inputClavier;
		boolean error = false;

		System.out.println("Entrer un pseudo :");
		
		do {
			error = false;
			try {
				inputClavier = new Scanner(System.in);
				pseudo = inputClavier.nextLine();
			} catch (Exception e) {
				System.out.println("Vous devez entrer un pseudo !!!");
				error = true;
			}
		} while (error == true);
		
		return pseudo;
	}
}
