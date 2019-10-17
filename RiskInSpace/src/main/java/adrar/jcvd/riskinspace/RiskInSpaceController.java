package adrar.jcvd.riskinspace;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
		riskService.placeShipsPlayer(player1);
		
		

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
		
		//fight.fight(nbrAttDice, nbrDefDice, planetAtt.getPlanetShipsNbr(), planetDef.getPlanetShipsNbr(), planetAtt, planetDef);

		riskService.shipsPerTurn(player1);
		System.out.println(planetAtt.getPlanetId() +" " +planetAtt.getPlanets());
		System.out.println(planetList);
		ModelAndView view = new ModelAndView("riskinspace");
		view.addObject("player1",player1);
		view.addObject("player2",player2);
		view.addObject("planets",planetList);
		view.addObject("planetsPlayer1",planetsPlayer1);
		view.addObject("planetsPlayer2",planetsPlayer2);
		return view;

	}
	
	@GetMapping("/")
	public ModelAndView home() {
		List<Species> species = speciesService.findAll();
		ModelAndView view = new ModelAndView("init");
		view.addObject("species",species);
		return view;

		
	}
	
	//@RequestMapping(value="/",method = RequestMethod.POST) 
	@PostMapping("/")
	public void insertPlayer(HttpServletRequest request) {
		String playerName1 = "";
		String playerName2 = "";
		String playerSpecies1 = "";
		String playerSpecies2 = "";
        try {           

            playerName1 = (String) request.getParameter("player-name");
            playerName2 = (String) request.getParameter("player-name2");
            playerSpecies1 = (String) request.getParameter("player-species");
            playerSpecies2 = (String) request.getParameter("player-species2");
           
            Species specie1 = speciesService.findOne(Integer.parseInt(playerSpecies1));
            Species specie2 = speciesService.findOne(Integer.parseInt(playerSpecies2));
            
            Player player1 = new Player(playerName1, specie1);
            Player player2 = new Player(playerName2, specie2);
            playerRepo.save(player1);
            playerRepo.save(player2);
            
            
        } catch (Exception e) {

        }
		
	}
}
