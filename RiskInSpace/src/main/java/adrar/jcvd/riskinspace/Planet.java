package adrar.jcvd.riskinspace;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity

public class Planet {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int planetId;
	private int planetShipsNbr;
	private String planetName;
	@ManyToOne()
	@JoinColumn(name ="planet_owner")
	@JsonManagedReference
	private Player planetOwner;
	private int planetBonus;
	private String planetImage;
	@JsonIgnore
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="frontiers",
	joinColumns={@JoinColumn(name="planet_id1")},
	inverseJoinColumns={@JoinColumn(name="planet_id2")})
	public Set<Planet> planetsInitial = new HashSet<Planet>();

	@JsonIgnore
	@ManyToMany(mappedBy="planetsInitial")
	public Set<Planet> planetsNear = new HashSet<Planet>();

	public Planet() {}

	public Planet(int planetId, String planetName, int planetShipsNbr, Player planetOwner) {
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

	public Player getPlanetOwner() {
		return planetOwner;
	}
	public void setPlanetOwner(Player player1) {
		this.planetOwner = player1;
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

	public Set<Planet> getPlanets() {
		return planetsInitial;
	}

	public void setPlanets(Set<Planet> planets) {
		this.planetsInitial = planets;
	}

	public Set<Planet> getPlanetsNear() {
		return planetsNear;
	}

	public void setPlanetsNear(Set<Planet> planetsNear) {
		this.planetsNear = planetsNear;
	}


}