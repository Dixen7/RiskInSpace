package adrar.jcvd.riskinspace;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Planet {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected int planetId;
	protected int planetShipsNbr;
	protected String planetName;
	 @ManyToOne(targetEntity=Player.class)
	protected Optional<Player> planetOwner;
	protected int planetBonus;
	protected String planetImage;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="frontiers",
		joinColumns={@JoinColumn(name="planet_id1")},
		inverseJoinColumns={@JoinColumn(name="planet_id2")})
	private Set<Planet> planets = new HashSet<Planet>();

	@ManyToMany(mappedBy="planets")
	private Set<Planet> planetsNear = new HashSet<Planet>();

	public Planet() {}
	
	public Planet(int planetId, String planetName, int planetShipsNbr, Optional<Player> planetOwner) {
		this.planetId = planetId;
		this.planetName = planetName;
		this.planetShipsNbr = planetShipsNbr;
		this.planetOwner = planetOwner;
	}

	public int getPlanetId() {
		return planetId;
	}
	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}
	public int getPlanetShipsNbr() {
		return planetShipsNbr;
	}
	public void setPlanetShipsNbr(int planetShipsNbr) {
		this.planetShipsNbr = planetShipsNbr;
	}
	
	
	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public Optional<Player> getPlanetOwner() {
		return planetOwner;
	}
	public void setPlanetOwner(Optional<Player> planetOwner) {
		this.planetOwner = planetOwner;
	}
	public int getPlanetBonus() {
		return planetBonus;
	}
	public void setPlanetBonus(int planetBonus) {
		this.planetBonus = planetBonus;
	}
	public String getPlanetImage() {
		return planetImage;
	}
	public void setPlanetImage(String planetImage) {
		this.planetImage = planetImage;
	}
	
	public String toString() {
		return ""+this.planetId + " ";
	}
}
