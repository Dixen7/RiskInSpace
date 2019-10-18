package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="species")
public class Species {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="species_id")
	protected int speciesId;
	protected String speciesName;
	protected String SpeciesImage;
	//@OneToMany
	//private List<Player> players ;
	
	
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

	/*public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}*/

	public String toString() {
		return this.speciesId +" "+this.speciesName;
	}
	
	

	
}

