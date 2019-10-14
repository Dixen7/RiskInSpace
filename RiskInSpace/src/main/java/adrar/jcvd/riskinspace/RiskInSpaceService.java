package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adrar.jcvd.riskinspace.repositories.SpeciesRepository;

@Service
public class RiskInSpaceService {
	
	@Autowired
	SpeciesRepository speciesRepo;
	//insertion des joueurs
	public ArrayList<Player> insertPlayer() {
		ArrayList<Player> players = new ArrayList<Player>();
		Scanner sc = new Scanner(System.in);
		System.out.println("rentre le nbr de joueurs");
		int numberPlayer = 2;//sc.nextInt();
		while(numberPlayer > 0) {
			//Scanner sc2 = new Scanner(System.in);
			System.out.println("rentre le nom du joueur");
			String playerName = sc.nextLine();
			players.add(new Player(1,playerName,speciesRepo.findById(1)));
			numberPlayer--;
		}
		System.out.println(players.toString());
		return players;
	}
	
	public ArrayList<Player> orderPlayerTurn(ArrayList<Player> players) {
		 Collections.shuffle(players);
		 System.out.println(players.toString());
		 return players;
	}
	
	
	
	public ArrayList<Planet> placeShip(ArrayList<Planet> planetList) {
		
		 // = planetRepository.FindAll();
		for(int i = 1; i <= 30; i++ ) {
			planetList.add(new Planet(i,"Planet"+i,3,null));
			
		}
		ArrayList<Planet> planetListPlayer1 = new ArrayList<Planet>();
		for(int i = 0; i < 15; i++) {
			int randomIndex = (int) Math.ceil(Math.random()*planetList.size()-1);
			 Planet randomPlanet = planetList.get(randomIndex);
			 planetListPlayer1.add(randomPlanet);
			 planetList.remove(randomPlanet);
		}
		ArrayList<Planet> planetListPlayer2 = new ArrayList<Planet>();
		planetListPlayer2.addAll(planetList);
		
		//System.out.println(planetList.toString());
		System.out.println(planetListPlayer1);
		System.out.println(planetListPlayer2);
		return planetList;
		
	}
	
	
}
