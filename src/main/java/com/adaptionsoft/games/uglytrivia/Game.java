package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Arrays;
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
	private String inputFromUser;
	private String strQuestion;
	
    LinkedList<String> questionList;
    LinkedList<String> answerList;

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
		
		sportsQuestions.addAll(Arrays.asList(
			    "Which sport has 11 playes on the field?",
			    "Which sport uses a net, a racket, and a shuttlecock?",
			    "What sport is described as “the beautiful game?”",
			    "How long is the total distance of a marathon?",
			    "Which sport has 11 playes on the field?",
			    "Which sport uses a net, a racket, and a shuttlecock?",
			    "What sport is described as “the beautiful game?”",
			    "How long is the total distance of a marathon?"
			));

			sportsAnswers.addAll(Arrays.asList(
			    "Cricket",
			    "Badminton",
			    "Football",
			    String.valueOf(5),
			    "Cricket",
			    "Badminton",
			    "Football",
			    String.valueOf(5)
			));

//Science
		scienceQuestions.add("Which animal lays eggs?");
		scienceAnswers.add("Duck");

		scienceQuestions.add("A male cow is called?");
		scienceAnswers.add(1, "Ox");

		scienceQuestions.add("All animals need food, air, and ____ to survive.");
		scienceAnswers.add("Water");

		scienceQuestions.add("Which one is a fur-bearing animal?");
		scienceAnswers.add("Cat");

		scienceQuestions.add("Which animal lays eggs?");
		scienceAnswers.add("Duck");

		scienceQuestions.add("A male cow is called?");
		scienceAnswers.add("Ox");

		scienceQuestions.add("All animals need food, air, and ____ to survive.");
		scienceAnswers.add("Water");

		scienceQuestions.add("Which one is a fur-bearing animal?");
		scienceAnswers.add("Cat");

// Rock
		rockQuestions.add("Which is the largest country in the world?");
		rockAnswers.add("Russia");

		rockQuestions.add("Which country has the largest population in the world?");
		rockAnswers.add("China");

		rockQuestions.add("In which country would you find the Leaning Tower of Pisa?");
		rockAnswers.add("Italy");

		rockQuestions.add("Which planet is nearest to the Earth??");
		rockAnswers.add("Venus");

		rockQuestions.add("Which is the largest country in the world?");
		rockAnswers.add("Russia");

		rockQuestions.add("Which country has the largest population in the world?");
		rockAnswers.add("China");

		rockQuestions.add("In which country would you find the Leaning Tower of Pisa?");
		rockAnswers.add("Italy");

		rockQuestions.add("Which planet is nearest to the Earth??");
		rockAnswers.add("Venus");

//Pop
		popQuestions.add("What is the smallest planet in our solar system?");
		popAnswers.add("Mercury");

		popQuestions.add("What sport did David Beckham play?");
		popAnswers.add("Football");

		popQuestions.add("What has Mohammed Ali’s original name?");
		popAnswers.add("Boxing");

		popQuestions.add("What does the Latin word 'tempus' mean in English?");
		popAnswers.add("Time");
		
		popQuestions.add("What is the smallest planet in our solar system?");
		popAnswers.add("Mercury");

		popQuestions.add("What sport did David Beckham play?");
		popAnswers.add("Football");

		popQuestions.add("What has Mohammed Ali’s original name?");
		popAnswers.add("Boxing");

		popQuestions.add("What does the Latin word 'tempus' mean in English?");
		popAnswers.add("Time");

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

	/*
	 * private void askQuestion() { if (currentCategory() == "Pop") { index =
	 * popQuestions.indexOf(popQuestions.size()) + 1; strQuestion =
	 * popQuestions.removeFirst(); System.out.println(strQuestion); inputFromUser =
	 * sc.next(); //Store answer
	 * 
	 * validateAnswer(currentCategory(), index, inputFromUser);
	 * 
	 * } if (currentCategory() == "Science") { index =
	 * scienceAnswers.indexOf(scienceQuestions.size()) + 1; String strQuestion =
	 * scienceQuestions.removeFirst(); System.out.println(strQuestion);
	 * inputFromUser = sc.next(); validateAnswer(currentCategory(), index,
	 * inputFromUser); } if (currentCategory() == "Sports") { index =
	 * sportsQuestions.indexOf(sportsQuestions.size()) + 1; String strQuestion =
	 * sportsQuestions.removeFirst(); System.out.println(strQuestion); inputFromUser
	 * = sc.next(); validateAnswer(currentCategory(), index, inputFromUser); } if
	 * (currentCategory() == "Rock") { index =
	 * rockAnswers.indexOf(rockQuestions.size()) + 1; String strQuestion =
	 * rockQuestions.removeFirst(); System.out.println(strQuestion); inputFromUser =
	 * sc.next(); validateAnswer(currentCategory(), index, inputFromUser); } }
	 * 
	 * // Validate the input answer and call for existing methods. private boolean
	 * validateAnswer(String strCategory, int index, String inputFromUser) {
	 * 
	 * if (strCategory.equalsIgnoreCase("Pop")) if
	 * (popAnswers.contains(inputFromUser)) { return true; }
	 * 
	 * if (strCategory.equalsIgnoreCase("Rock")) if
	 * (rockAnswers.contains(inputFromUser)) { return true; }
	 * 
	 * if (strCategory.equalsIgnoreCase("Science")) if
	 * (scienceAnswers.contains(inputFromUser))//.get(index).equals(inputFromUser))
	 * { { return true; }
	 * 
	 * if (strCategory.equalsIgnoreCase("Sports")) if
	 * (sportsAnswers.contains(inputFromUser)){ return true; } return false; }
	 */
	public String currentCategory() {
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
	
	
	private void askQuestion() {
	    String currentCategory = currentCategory();
	    String currentQuestion = "";

	    if (currentCategory.equalsIgnoreCase("Pop")) {
	        questionList = popQuestions;
	        answerList = popAnswers;
	    } else if (currentCategory.equalsIgnoreCase("Science")) {
	        questionList = scienceQuestions;
	        answerList = scienceAnswers;
	    } else if (currentCategory.equalsIgnoreCase("Sports")) {
	        questionList = sportsQuestions;
	        answerList = sportsAnswers;
	    } else {
	        questionList = rockQuestions;
	        answerList = rockAnswers;
	    }

	    if (!questionList.isEmpty()) {
	        currentQuestion = questionList.removeFirst();
	        System.out.println(currentQuestion);
	        setInputFromUser(sc.next());
	        validateAnswer(currentCategory, currentQuestion, getInputFromUser());
	    }
	}

	public boolean validateAnswer(String strCategory, String question, String inputFromUser) {
	  
	    if (strCategory.equalsIgnoreCase("Pop")) {
	        answerList = popAnswers;
	    } else if (strCategory.equalsIgnoreCase("Science")) {
	        answerList = scienceAnswers;
	    } else if (strCategory.equalsIgnoreCase("Sports")) {
	        answerList = sportsAnswers;
	    } else {
	        answerList = rockAnswers;
	    }

	    if (answerList.contains(inputFromUser)) {
	        return true;
	    }

	    return false;
	}

	public String getInputFromUser() {
		return inputFromUser;
	}

	public void setInputFromUser(String inputFromUser) {
		this.inputFromUser = inputFromUser;
	}

	public String getStrQuestion() {
		return strQuestion;
	}

	public void setStrQuestion(String strQuestion) {
		this.strQuestion = strQuestion;
	}

}
