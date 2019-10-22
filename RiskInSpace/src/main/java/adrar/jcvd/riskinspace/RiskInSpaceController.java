package adrar.jcvd.riskinspace;



import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import adrar.jcvd.riskinspace.repositories.PlanetRepository;
import adrar.jcvd.riskinspace.repositories.PlayerRepository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
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
	public ModelAndView Riskinspace() {
		List<Player> players = playerRepo.findAll(new Sort(Sort.Direction.DESC, "playerId"));
		List<Planet> planetList =  planetRepo.findAll();

		riskService.renamePlanets(planetList);
		Player player1 = players.get(1);
		Player player2 = players.get(0);
		riskService.orderPlayerTurn(players);

		riskService.placeShipInitial(planetList, player1, player2);
		planetList = planetRepo.findAll(new Sort(Sort.Direction.ASC, "planetId"));
		List<Planet> planetsPlayer1 = planetRepo.findAllByPlanetOwner(player1);
		List<Planet> planetsPlayer2 = planetRepo.findAllByPlanetOwner(player2);
		riskService.placeShipsPlayer(player1, riskService.shipsPerTurn(player1));


		Fight fight = new Fight();

		// tests
		Planet planetAtt = planetList.get(5);
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

		riskService.moveShips(planetAtt, player1, 20);

		ModelAndView view = new ModelAndView("riskinspace");
		view.addObject("player1",player1);
		view.addObject("player2",player2);
		view.addObject("planets",planetList);
		view.addObject("planetsPlayer1",planetsPlayer1);
		view.addObject("planetsPlayer2",planetsPlayer2);
		return view;
	}



	@GetMapping("/")
	public List<Species> home() {
		return speciesService.findAll();
		
	}
	
	@GetMapping("/planet")
	public ResponseEntity<?> planet() {
		List<Planet> planets = planetRepo.findAll(new Sort(Sort.Direction.ASC, "planetId")); 
		List<Player> players = playerRepo.findAll(new Sort(Sort.Direction.DESC, "playerId"));
		Player player1 = players.get(1);
		Player player2 = players.get(0);
		int countPlanetPlayer1 = planetRepo.findAllByPlanetOwner(player1).size();
		int countPlanetPlayer2 = planetRepo.findAllByPlanetOwner(player2).size();
		HashMap<String, Object> hmap = new HashMap<String, Object>();
	    hmap.put("planets", planets);
	    hmap.put("player1", player1);
	    hmap.put("player2", player2);
	    hmap.put("countPlanetPlayer1", countPlanetPlayer1);
	    hmap.put("countPlanetPlayer2", countPlanetPlayer2);
	    return new ResponseEntity<HashMap<String, Object>>(hmap, HttpStatus.OK);
		
	}




	//@RequestMapping(value="/",method = RequestMethod.POST) 
	@PostMapping("/createplayer")
	public void insertPlayer(@RequestBody String req) throws ParseException {

		System.out.println(req);
		Object obj = new JSONParser().parse(req);
		JSONObject jo = (JSONObject) obj; 
		 String playerName1 = (String) jo.get("playerName");
		 String playerName2 = (String) jo.get("playerName2");
		 int playerSpecies = ((Long) jo.get("playerSpecies")).intValue();
		 int playerSpecies2 = ((Long) jo.get("playerSpecies2")).intValue();

		System.out.println(playerName1);
		try {


			Species specie1 = speciesService.findOne(playerSpecies);
			Species specie2 = speciesService.findOne(playerSpecies2);

			Player player1 = new Player(playerName1, specie1);
			Player player2 = new Player(playerName2, specie2);
			playerRepo.save(player1);
			playerRepo.save(player2);


		} catch (Exception e) {
		}
	}
}