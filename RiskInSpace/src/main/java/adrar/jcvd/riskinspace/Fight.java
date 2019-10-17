package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Fight {

	//	Lance 1 dé à 6 faces
	public ArrayList<Integer> rollDice(int nbrDice) {
		ArrayList<Integer> Dice = new ArrayList<Integer>();
		while (nbrDice > 0) {
			Dice.add((int) Math.ceil(Math.random()*6));
			nbrDice--;
		}
		Collections.sort(Dice,Collections.reverseOrder());
		System.out.println(Dice);
		return  Dice;
	}



	public void fight(int nbrAttDice, int nbrDefDice, int planetShipsAttNbr, int planetShipsDefNbr, Planet planetAtt, Planet planetDef) {
		System.out.println("troupes : " + nbrAttDice + " de l'attaquant | " + nbrDefDice + " du défenseur");
		int[] resFight = compareDice(rollDice(nbrAttDice), rollDice(nbrDefDice));
		nbrAttDice -= resFight[0];
		nbrDefDice -= resFight[1];
		planetShipsAttNbr -= resFight[0];
		planetShipsDefNbr -= resFight[1];
		System.out.println("troupes : " + nbrAttDice + " de l'attaquant | " + nbrDefDice + " du défenseur");
		if (planetShipsDefNbr <= 0) {
			System.out.println("Le défenseur n'a plus de troupes, le territoire a été vaincu par l'attaquant");
			System.out.println("owner planetAtt avant = " + planetAtt.getPlanetOwner());
			System.out.println("owner planetDef avant = " + planetDef.getPlanetOwner());
			planetDef.setPlanetOwner(planetAtt.getPlanetOwner());
			System.out.println("owner planetAtt après = " + planetAtt.getPlanetOwner());
			System.out.println("owner planetDef après = " + planetDef.getPlanetOwner());
		}
	}



	public int[] compareDice(ArrayList<Integer> attDice, ArrayList<Integer> defDice) {
		int numberDice;
		if(attDice.size() < defDice.size()) {
			numberDice = attDice.size();
		}else if(attDice.size() > defDice.size()) {
			numberDice = defDice.size();
		}else {
			numberDice = defDice.size();
		}
		int[] resFight = {0, 0};
		for(int i=0;i < numberDice;i++) {
			if(attDice.get(i) > defDice.get(i)) {
				resFight[1]++;
			}else {
				resFight[0]++;
			}
		}
		// Le défenseur va forcément tout perdre avec ça
		resFight[1]=2;
		//
		return resFight;
	}
}
