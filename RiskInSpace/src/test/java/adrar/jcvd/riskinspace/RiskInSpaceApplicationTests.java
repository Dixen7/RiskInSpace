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
	@Autowired
	RiskInSpaceService riskService;

	@Test
	public void Test() {

		List<Player> players = playerRepo.findAll(new Sort(Sort.Direction.DESC, "playerId"));
		
		Player player1 = players.get(1);
		Player player2 = players.get(0);
		riskService.orderPlayerTurn(players);
		List<Planet> planetList =  planetRepo.findAll();
		riskService.renamePlanets(planetList);
		riskService.placeShipInitial(planetList, player1, player2);
		
		Fight fight = new Fight();
		
		Planet planetAtt = new Planet();
		Planet planetDef = new Planet();

		fight.fight(3, 2, planetAtt, planetDef);

		riskService.shipsPerTurn(player1);

		
	}



}
