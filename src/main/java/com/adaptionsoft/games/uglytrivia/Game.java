package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
	ArrayList<String> players = new ArrayList<String>();
	int[] places = new int[6];
	int[] purses = new int[6];
	boolean[] inPenaltyBox = new boolean[6];

	LinkedList<String> popQuestions = new LinkedList<String>();
	LinkedList<String> scienceQuestions = new LinkedList<String>();
	LinkedList<String> sportsQuestions = new LinkedList<String>();
	LinkedList<String> rockQuestions = new LinkedList<String>();

	LinkedList<String> popAnswers = new LinkedList<String>();
	LinkedList<String> scienceAnswers = new LinkedList<String>();
	LinkedList<String> sportsAnswers = new LinkedList<String>();
	LinkedList<String> rockAnswers = new LinkedList<String>();

	int currentPlayer = 0;
	boolean isGettingOutOfPenaltyBox;
	int index = -1;

	Scanner sc = new Scanner(System.in);

	public Game() {

		// Add questions
		addQuestionAndAnswers();

		/*
		 * for (int i = 0; i < 2; i++) { popQuestions.addLast("Pop Question " + i);
		 * scienceQuestions.addLast(("Science Question " + i));
		 * sportsQuestions.addLast(("Sports Question " + i));
		 * rockQuestions.addLast(createRockQuestion(i)); }
		 */
	}

	private void addQuestionAndAnswers() {

		// Sports
		sportsQuestions.add(0, "Which sport has 11 playes on the field?");
		sportsAnswers.add(0, "Cricket");

		sportsQuestions.add(1, " Which sport uses a net, a racket, and a shuttlecock?");
		sportsAnswers.add(1, "Badminton");

		sportsQuestions.add(2, "What sport is described as “the beautiful game?”");
		sportsAnswers.add(2, "Football");

		sportsQuestions.add(3, "How long is the total distance of a marathon?");
		sportsAnswers.add(3, String.valueOf(5));

		// Science
		scienceQuestions.add(0, "Which animal lays eggs?");
		scienceAnswers.add(0, "Duck");

		scienceQuestions.add(1, "A male cow is called?");
		scienceAnswers.add(1, "Ox");

		scienceQuestions.add(2, "All animals need food, air, and ____ to survive.");
		scienceAnswers.add(2, "Water");

		scienceQuestions.add(3, "Which one is a fur-bearing animal?");
		scienceAnswers.add(3, "Cat");

		// Rock
		rockQuestions.add(0, "Which is the largest country in the world?");
		rockAnswers.add(0, "Russia");

		rockQuestions.add(1, "Which country has the largest population in the world?");
		rockAnswers.add(1, "China");

		rockQuestions.add(2, "In which country would you find the Leaning Tower of Pisa?");
		rockAnswers.add(2, "Italy");

		rockQuestions.add(3, "Which planet is nearest to the Earth??");
		rockAnswers.add(3, "Venus");

		// pop
		popQuestions.add(0, "What is the smallest planet in our solar system?");
		popAnswers.add(0, "Mercury");

		popQuestions.add(1, "What sport did David Beckham play?");
		popAnswers.add(1, "Football");

		popQuestions.add(2, "What has Mohammed Ali’s original name?");
		popAnswers.add(2, "Boxing");

		popQuestions.add(3, "What does the Latin word 'tempus' mean in English?");
		popAnswers.add(3, "Time");

	}

	public String createRockQuestion(int index) {
		return "Rock Question " + index;
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {

		players.add(playerName);
		places[howManyPlayers()] = 0;
		purses[howManyPlayers()] = 0;
		inPenaltyBox[howManyPlayers()] = false;

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11)
					places[currentPlayer] = places[currentPlayer] - 12;

				System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}

		} else {

			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11)
				places[currentPlayer] = places[currentPlayer] - 12;

			System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}

	}

	private void askQuestion() {
		if (currentCategory() == "Pop") {
			index = popQuestions.indexOf(popQuestions.size()) + 1;
			String strQuestion = popQuestions.removeFirst();
			System.out.println(strQuestion);
			String inputFromUser = sc.next();
			validateAnswer(currentCategory(), index, inputFromUser);
		}
		if (currentCategory() == "Science") {
			index = scienceQuestions.indexOf(scienceQuestions.size()) + 1;
			String strQuestion = scienceQuestions.removeFirst();
			System.out.println(strQuestion);
			String inputFromUser = sc.next();
			validateAnswer(currentCategory(), index, inputFromUser);
		}
		if (currentCategory() == "Sports") {
			index = sportsQuestions.indexOf(sportsQuestions.size()) + 1;
			String strQuestion = sportsQuestions.removeFirst();
			System.out.println(strQuestion);
			String inputFromUser = sc.next();
			validateAnswer(currentCategory(), index, inputFromUser);
		}
		if (currentCategory() == "Rock") {
			index = rockQuestions.indexOf(rockQuestions.size()) + 1;
			String strQuestion = rockQuestions.removeFirst();
			System.out.println(strQuestion);
			String inputFromUser = sc.next();
			validateAnswer(currentCategory(), index, inputFromUser);
		}
	}

	// Valiedate the input answer and call for existing methods.
	private void validateAnswer(String strCategory, int index, String inputFromUser) {

		if (strCategory == "Pop")
			if (popAnswers.get(index).equals(inputFromUser)) {
				wasCorrectlyAnswered();
			} else {
				wrongAnswer();
			}

		if (strCategory == "Rock")
			if (rockAnswers.get(index).equals(inputFromUser)) {
				wasCorrectlyAnswered();
			} else {
				wrongAnswer();
			}

		if (strCategory == "Science")
			if (scienceAnswers.get(index).equals(inputFromUser)) {
				wasCorrectlyAnswered();
			} else {
				wrongAnswer();
			}

		if (strCategory == "Sports")
			if (sportsAnswers.get(index).equals(inputFromUser)) {
				wasCorrectlyAnswered();
			} else {
				wrongAnswer();
			}
	}

	private String currentCategory() {
		if (places[currentPlayer] == 0)
			return "Pop";
		if (places[currentPlayer] == 4)
			return "Pop";
		if (places[currentPlayer] == 8)
			return "Pop";
		if (places[currentPlayer] == 1)
			return "Science";
		if (places[currentPlayer] == 5)
			return "Science";
		if (places[currentPlayer] == 9)
			return "Science";
		if (places[currentPlayer] == 2)
			return "Sports";
		if (places[currentPlayer] == 6)
			return "Sports";
		if (places[currentPlayer] == 10)
			return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size())
					currentPlayer = 0;

				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size())
					currentPlayer = 0;
				return true;
			}

		} else {

			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size())
				currentPlayer = 0;

			return winner;
		}
	}

	public boolean wrongAnswer() {
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;

		currentPlayer++;
		if (currentPlayer == players.size())
			currentPlayer = 0;
		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}