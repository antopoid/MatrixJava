package matrix;

/**
 * Classe Terrain.
 * Permet de gerer les matrices qui forme le terrain.
 * @author benlacheheb
 *
 */

public class Terrain {
	
	/**
	 * Fonction static qui permet de creer la map du jeu.
	 * Le code des matrices a tester est a copier coller ici.
	 * @return Renvoie la map du jeu.
	 */
	public static Ressource[][] creationTerrain() {
		
		Ressource[][] matrix6 = new Ressource[10][10];
		
		matrix6 [0][0] = new Pierre("Jacques");
        matrix6 [1][1] = new Ble("blé 12");
        matrix6 [0][2] = new Ble("blé 12");
        matrix6 [1][3] = new Ble("blé 12");
        matrix6 [0][4] = new Ble("blé 12");
        matrix6 [1][5] = new Ble("blé 12");
        matrix6 [0][6] = new Ble("blé 12");
        matrix6 [1][7] = new Ble("blé 12");
        matrix6 [0][8] = new Ble("blé 12");
        matrix6 [5][4] = new Ble("blé 12");
        matrix6 [1][9] = new Ble("blé 12");    
        matrix6 [5][5] = new Pierre("Jacques");
        matrix6 [6][6] = new Pierre("Jacques");
        matrix6 [3][1] = new Bois("bois31");
        matrix6 [4][8] = new Bois("bois48");
        matrix6 [1][0] = new Bois("bois48");
        matrix6 [1][2] = new Bois("bois48");
        matrix6 [0][1] = new Bois("bois48");
        matrix6 [0][3] = new Bois("bois48");
        matrix6 [0][5] = new Bois("bois48");
        matrix6 [0][7] = new Bois("bois48");
        matrix6 [6][2] = new Bois("bois48");
        matrix6 [6][1] = new Bois("bois48");
        matrix6 [6][3] = new Bois("bois48");
        matrix6 [6][5] = new Bois("bois48");
        matrix6 [6][7] = new Bois("bois48");
        
		return matrix6;
	}
}
