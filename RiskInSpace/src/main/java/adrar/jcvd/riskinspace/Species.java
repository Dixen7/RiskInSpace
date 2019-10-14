package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Species {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected int speciesId;
	protected String speciesName;

	@OneToMany(mappedBy="playerSpeciesId")
	//private ArrayList<Player> players ;
	private Set<Player> players = new HashSet<Player>();
	
	public Species() {}

	public Species(int speciesId, String speciesName) {
		this.speciesId = speciesId;
		this.speciesName = speciesName;
	}


	public int getSpeciesId() {
		return speciesId;
	}
	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}
	public String getSpeciesName() {
		return speciesName;
	}
	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}
	public String getSpeciesImage() {
		return SpeciesImage;
	}
	public void setSpeciesImage(String speciesImage) {
		SpeciesImage = speciesImage;
	}
	protected String SpeciesImage;
	
	public String toString() {
		return this.speciesName;
	}
}

