package app;

import java.util.concurrent.ThreadLocalRandom;

public class Scramble {
	
	private String[][] scrambles = new String[][]{
		// These are the only moves used to scramble a standard 3x3x3 cube
		{"R","R'","R2"},
		{"U","U'","U2"},
		{"F","F'","F2"},
		{"L","L'","L2"},
		{"D","D'","D2"},
		{"B","B'","B2"}
	};
	private String theScramble = "";
	// length of scramble in moves
	private int length = 25;
	
	public void newScramble() {
		theScramble = "";
		// picks two random faces as the last two faces that were moved 
		int last1 = ThreadLocalRandom.current().nextInt(0, scrambles.length);
		int last2 = ThreadLocalRandom.current().nextInt(0, scrambles.length);
		
		for (int i = 0; i < length; i++) {
			// Chooses a random face
			int moveX = ThreadLocalRandom.current().nextInt(0, scrambles.length);
			
			// Checks if face is redundant, i.e. if the move picked 
			// does not contribute to further scrambling of the cube
			/* The fave is redundant if:
			 	* Face is same as last move's face
			 	* Face is same as second last move's face, AND last 
				  move's face was the opposite of this face
			*/
			while (moveX == last1 
				   || (moveX == last2
				   && (moveX-3 == last1
				   || moveX+3 == last1))) {
				// Picks again
				moveX = ThreadLocalRandom.current().nextInt(0, scrambles.length);
			}
			
			// Picks random move from that face (e.g. R, R' or 2R)
			int moveY = ThreadLocalRandom.current().nextInt(0, scrambles[0].length);
			// Adds that move to the scramble sequence
			theScramble += scrambles[moveX][moveY] + "  ";
			// Shifts the last1 and last2 vars
			last2 = last1;
			last1 = moveX;
		}
	}
	
	@Override
	public String toString() {
		return theScramble;
	}

}
