package matrix;

/**
 * Une ressource est définie par un poids et un id.
 * Une ressource correspond a une pierre, un ble ou encore un bois.
 * @author benlacheheb
 *
 */

public class Ressource {

	private int poids;
	private String id;

	public Ressource(int poids, String id) {
		this.poids = poids;
		this.id = id;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

}
