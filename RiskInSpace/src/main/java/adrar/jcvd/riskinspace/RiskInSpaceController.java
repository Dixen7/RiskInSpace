package adrar.jcvd.riskinspace;


import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
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

		riskService.insertPlayer();

		List<Player> players = playerRepo.findAll(new Sort(Sort.Direction.DESC, "playerId"));

		Player player1 = players.get(1);
		Player player2 = players.get(0);
		riskService.orderPlayerTurn(players);
		List<Planet> planetList =  planetRepo.findAll();
		riskService.renamePlanets(planetList);
		riskService.placeShipInitial(planetList, player1, player2);

		Fight fight = new Fight();

		// tests
		Planet planetAtt = planetList.get(1);
		planetAtt.setPlanetOwner(player1);
		planetAtt.setPlanetShipsNbr(3);

		Planet planetDef = planetList.get(2);
		planetDef.setPlanetOwner(player2);
		planetDef.setPlanetShipsNbr(2);
		//
		
		int nbrAttDice = -1, nbrDefDice = -1;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("FIGHT !");
		System.out.println();
		
		System.out.println("Attaquant, choisissez le nombre de dés (entre 1 et 3)");
		while( nbrAttDice < 0 || nbrAttDice > 3) {
			nbrAttDice = sc.nextInt();
		}
		
		System.out.println("Défenseur, choisissez le nombre de dés (entre 1 et 2)");
		while( nbrDefDice < 0 || nbrDefDice > 2) {
			nbrDefDice = sc.nextInt();
		}
		
		fight.fight(nbrAttDice, nbrDefDice, planetAtt.getPlanetShipsNbr(), planetDef.getPlanetShipsNbr(), planetAtt, planetDef);

		riskService.shipsPerTurn(player1);


	}
}
