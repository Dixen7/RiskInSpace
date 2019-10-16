package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import adrar.jcvd.riskinspace.repositories.PlanetRepository;
import adrar.jcvd.riskinspace.repositories.PlayerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskInSpaceApplicationTests {

	@Autowired
	PlanetRepository planetRepo;
	@Autowired
	PlayerRepository playerRepo;

	@Test
	public void Test() {
		Fight fight = new Fight();
		RiskInSpaceService riskService = new RiskInSpaceService();
		riskService.insertPlayer();

		ArrayList<Integer>attack = fight.rollDice(3);
		ArrayList<Integer>defense = fight.rollDice(2);

		fight.compareDice(attack,defense);
		List<Player> players = playerRepo.findAll(new Sort(Sort.Direction.DESC, "player_id"));
		riskService.orderPlayerTurn(players);
		Player player1 = players.get(0);
		Player player2 = players.get(1);
		List<Planet> planetList =  planetRepo.findAll();
		riskService.renamePlanets(planetList);
		riskService.placeShipInitial(planetList, player1, player2);

		
	}



}
