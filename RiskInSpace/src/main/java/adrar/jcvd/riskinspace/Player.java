package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="playerId")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected int playerId;
	protected String playerName;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name ="player_species_id")
	protected Species species;
	protected int playerMoney;
	
	@OneToMany(mappedBy="planetOwner")
	private List<Planet> planets ;
	
	
	public Player() {}

	public Player(String playerName, Species espece) {
		this.playerName = playerName;
		this.species = espece;
	}

	public Player(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}


	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	
	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public int getPlayerMoney() {
		return playerMoney;
	}
	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}

	public String toString() {
		return this.playerId + ", " + this.playerName + ", " + this.species;
	}
}
