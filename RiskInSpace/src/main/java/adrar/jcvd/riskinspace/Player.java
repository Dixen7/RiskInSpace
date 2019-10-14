package adrar.jcvd.riskinspace;

import java.util.ArrayList;

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
	 @ManyToOne
	protected Species playerSpeciesId;
	protected int playerMoney;
	
	@OneToMany(mappedBy="planetOwner")
    private ArrayList<Planet> planets ;

	public Player() {}

	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public Species getPlayerSpeciesId() {
		return playerSpeciesId;
	}
	public void setPlayerSpeciesId(Species playerSpeciesId) {
		this.playerSpeciesId = playerSpeciesId;
	}
	public int getPlayerMoney() {
		return playerMoney;
	}
	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}
}
