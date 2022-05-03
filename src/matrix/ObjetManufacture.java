package matrix;

/**
 * Classe qui correspond au objet cree via des ressources.
 * Le feu ou encore la farine est de type ObjetManufacture.
 * ObjetManufacture est defini par un type.
 * @author benlacheheb
 *
 */
public class ObjetManufacture {
	
	private String type;
	
	public ObjetManufacture(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
