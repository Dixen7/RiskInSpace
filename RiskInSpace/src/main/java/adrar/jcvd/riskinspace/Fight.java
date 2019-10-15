package adrar.jcvd.riskinspace;

import java.util.ArrayList;
import java.util.Collections;

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
				System.out.println("Attack Wins");
				resFight[1]+=1;
			}else {
				System.out.println("Def Wins");
				resFight[0]+=1;
			}
		}
		return resFight;
	}

	public void fight(int attDice, int defDice) {
		int[] resFight = compareDice(rollDice(attDice), rollDice(defDice));
		System.out.println("troupes perdues : " + resFight[0]+ " de l'attaquant | " + resFight[1] + " du défenseur");
	}


}
