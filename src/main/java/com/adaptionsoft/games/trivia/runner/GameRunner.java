
package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		Random rand = new Random();

		do {

			aGame.roll(rand.nextInt(5) + 1);

			 boolean isCorrectAnswer = aGame.validateAnswer(aGame.currentCategory(), aGame.getStrQuestion(), aGame.getInputFromUser());

			    if (isCorrectAnswer) {
			        notAWinner = aGame.wasCorrectlyAnswered();
			    } else {
			        notAWinner = aGame.wrongAnswer();
			    }
		} while (notAWinner);

	}
}
