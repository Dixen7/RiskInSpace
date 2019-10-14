package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected int playerId;
	protected String playerName;
	@ManyToOne(targetEntity=Species.class)
	protected Optional<Species> playerSpeciesId;
	protected int playerMoney;

	@OneToMany(mappedBy="planetOwner")
	//private ArrayList<Planet> planets ;
	private Set<Planet> planets = new HashSet<Planet>();

	public Player(int i, String playerName, Optional<Species> espece) {
		this.playerId = i;
		this.playerName = playerName;
		this.playerSpeciesId = espece;
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

	public Optional<Species> getPlayerSpeciesId() {
		return playerSpeciesId;
	}
	public void setPlayerSpeciesId(Optional<Species> playerSpeciesId) {
		this.playerSpeciesId = playerSpeciesId;
	}
	public int getPlayerMoney() {
		return playerMoney;
	}
	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}

	public String toString() {
		return this.playerId+","+this.playerName+", "+this.playerSpeciesId;
	}
}
