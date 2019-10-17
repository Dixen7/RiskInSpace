package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		Planet planetAtt = new Planet();
		Planet planetDef = new Planet();

		fight.fight(3, 2, 3, 2, planetAtt, planetDef);

		riskService.shipsPerTurn(player1);
	

	}
	@GetMapping("/")
	public ModelAndView home() {
		List<Species> species = speciesService.findAll();
		ModelAndView view = new ModelAndView("init");
		view.addObject("species",species);
		return view;
		
	}
	
	@RequestMapping(value="/",method = RequestMethod.POST) 
	public void insertPlayer(@RequestParam(value="name") String name) {
		/*
		String playerName = request.getParameter("name");
		Player player = new Player(playerName,espece);
		//riskService.insertPlayer();*/
	}
}
