package adrar.jcvd.riskinspace;

import java.util.ArrayList;


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
		ArrayList<Planet> planetList = new ArrayList<Planet>();
		riskService.placeShip(planetList);


		for(int i = 2; i < 30; i++) {
			Planet planet = new Planet(i, "aaa", 1,playerRepo.findById(1));
			planetRepo.save(planet);
		}
	}
}
