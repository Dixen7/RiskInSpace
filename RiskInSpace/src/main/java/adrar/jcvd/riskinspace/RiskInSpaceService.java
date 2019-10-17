package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import adrar.jcvd.riskinspace.repositories.PlanetRepository;
import adrar.jcvd.riskinspace.repositories.PlayerRepository;

@Service
public class RiskInSpaceService {

	@Autowired
	private SpeciesService speciesService;
	@Autowired
	private PlanetRepository planetRepo;
	@Autowired
	private PlayerRepository playerRepo;

	
	//Génère l'ordre des joueurs
	public List<Player> orderPlayerTurn(List<Player> players) {
		Collections.shuffle(players);
		//		 System.out.println(players.toString());
		return players;
	}
	
	
	
	//Renomme les planètes aléatoirement en début de jeu
	public void renamePlanets(List<Planet> planetList) {
		String []planetNames = {"Mercure","Venus","Terre","Mars","Neptune","Pluton","Naboo","Tatooine","Endor","Coruscant","Hoth","Magrathea","Kakrafoon Kappa","Krikket","Bételgeuse","P3X-888","Lantea","Dakara","Celestis","Talos IV","Risa","Qo'noS","Acamar III","Raxacoricofallapatorius","Metebelis III","Alfava Metraxis","Gallifrey","Kobol","Caprica","Gemenon","Leonis","Alpha Corvus","Meirrion","Troy","Solaria","Pandora","Krypton","Alderaan","Aldebaran","Babel","Betazed","Khitomer","Ligon II","Corellia","Dagobah","Mustafar","Yavin IV"};
		List<String> pla = Arrays.asList(planetNames);
		Collections.shuffle(pla);
		for(int i =0; i < planetList.size();i++) {
			planetList.get(i).setPlanetName(pla.get(i));
			planetRepo.save(planetList.get(i));
		}
	}
	
	
	
	//Attribut les planètes aux joueurs et place 1 troupe sur celles-ci
	public void placeShipInitial(List<Planet> planetList, Player player1, Player player2) {

		ArrayList<Planet> planetListPlayer1 = new ArrayList<Planet>();
		for(int i = 0; i < 15; i++) {
			int randomIndex = (int) Math.ceil(Math.random()*planetList.size()-1);
			Planet randomPlanet = planetList.get(randomIndex);
			randomPlanet.setPlanetOwner(player1);
			randomPlanet.setPlanetShipsNbr(1);
			planetRepo.save(randomPlanet);
			planetListPlayer1.add(randomPlanet);
			planetList.remove(randomPlanet);
		}
		ArrayList<Planet> planetListPlayer2 = new ArrayList<Planet>();
		planetListPlayer2.addAll(planetList);
		for(int j = 0; j<planetListPlayer2.size(); j++) {
			Planet pla = planetListPlayer2.get(j);
			pla.setPlanetOwner(player2);
			pla.setPlanetShipsNbr(1);
			planetRepo.save(pla);
		}
	}
	
	
	
	// Donne les troupes selon le nombre de planettes
	public int shipsPerTurn(Player player) {

		//List<Planet> planetListPlayer = player.getPlanets();
		List<Planet> planetListPlayer = planetRepo.findAllByPlanetOwner(player);

		int nmbrPlanets = planetListPlayer.size();
		double shipsTurn = nmbrPlanets / 3;
		int shipsPerTurn = (int) Math.floor(shipsTurn);
		return shipsPerTurn;
	}
}