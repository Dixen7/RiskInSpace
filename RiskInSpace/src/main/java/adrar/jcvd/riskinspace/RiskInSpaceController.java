package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import adrar.jcvd.riskinspace.repositories.PlanetRepository;
import adrar.jcvd.riskinspace.repositories.PlayerRepository;

@Controller
public class RiskInSpaceController {

	@Autowired
	PlanetRepository planetRepo;
	@Autowired
	PlayerRepository playerRepo;

	@GetMapping("/riskinspace")
	public void Test() {


		Fight fight = new Fight();
		RiskInSpaceService riskService = new RiskInSpaceService();


		ArrayList<Integer>attack = fight.rollDice(3);
		ArrayList<Integer>defense = fight.rollDice(2);

		fight.compareDice(attack,defense);
		ArrayList<Player> players = riskService.insertPlayer();
		riskService.orderPlayerTurn(players);
		Player player1 = players.get(0);
		Player player2 = players.get(1);
		List<Planet> planetList =  planetRepo.findAll();
		riskService.renamePlanets(planetList);
		riskService.placeShip(planetList, player1, player2);

		
	}
}
