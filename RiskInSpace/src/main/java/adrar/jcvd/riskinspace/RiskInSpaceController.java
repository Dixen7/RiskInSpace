package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	@Autowired
	SpeciesService speciesService;
	@Autowired
	RiskInSpaceService riskService;

	@GetMapping("/riskinspace")
	public void Test() {

		
		Fight fight = new Fight();
		riskService.insertPlayer();

		int nbrDiceAtt = 3;
		int nbrDiceDef = 2;
		fight.fight(nbrDiceAtt,nbrDiceDef);



		List<Player> players = playerRepo.findAll(new Sort(Sort.Direction.DESC, "playerId"));
		
		Player player1 = players.get(1);
		Player player2 = players.get(0);
		riskService.orderPlayerTurn(players);
		List<Planet> planetList =  planetRepo.findAll();
		riskService.renamePlanets(planetList);
		riskService.placeShip(planetList, player1, player2);
		riskService.placeShipsPlayer(player1);
		


		
	}
}
