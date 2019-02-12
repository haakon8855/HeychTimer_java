package app;

import java.util.concurrent.ThreadLocalRandom;

public class Scramble {
	
	private String[][] scrambles = new String[][]{
		{"R","R'","R2"},
		{"U","U'","U2"},
		{"F","F'","F2"},
		{"L","L'","L2"},
		{"D","D'","D2"},
		{"B","B'","B2"}
	};
	private String theScramble = "";
	private int length = 25;
	
	public void newScramble() {
		theScramble = "";
		int last1 = ThreadLocalRandom.current().nextInt(0, scrambles.length);
		int last2 = ThreadLocalRandom.current().nextInt(0, scrambles.length);
		
		for (int i = 0; i < length; i++) {
			// Chooses a random face
			int moveX = ThreadLocalRandom.current().nextInt(0, scrambles.length);
			
			// Checks if face is redundant
			/* Is redundant if:
			 	* Face is same as last one
			 	* Face is same as second last one and last one is opposite of this
			*/
			while (moveX == last1 
				   || (moveX == last2
				   && (moveX-3 == last1
				   || moveX+3 == last1))) {
				moveX = ThreadLocalRandom.current().nextInt(0, scrambles.length);
			}
			
			// Picks random move from that face (e.g. R, R' or 2R etc.)
			int moveY = ThreadLocalRandom.current().nextInt(0, scrambles[0].length);
			theScramble += scrambles[moveX][moveY] + " ";
			last2 = last1;
			last1 = moveX;
		}
	}
	
	@Override
	public String toString() {
		return theScramble;
	}
	
	public static void main(String[] args) {
		Scramble a = new Scramble();
		a.newScramble();
		System.out.println(a.theScramble);
	}

}
