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

		//Load Question and Answers
		loadQuestionsAndAnswers();
	}

	private void loadQuestionsAndAnswers() {
		getSportsQA();
		getSceinceQA();
		getRockQA();
		getPopQA();
					

	}
     

	/**
	 * Description : Gives pop question and answers
	 */
	private void getPopQA() {
		popQuestions.addAll(Arrays.asList(
			    "What is the smallest planet in our solar system?",
			    "What sport did David Beckham play?",
			    "What has Mohammed Ali’s original name?",
			    "What does the Latin word 'tempus' mean in English?",
			    "What is the smallest planet in our solar system?",
			    "What sport did David Beckham play?",
			    "What has Mohammed Ali’s original name?",
			    "What does the Latin word 'tempus' mean in English?"
			));

			popAnswers.addAll(Arrays.asList(
			    "Mercury",
			    "Football",
			    "Boxing",
			    "Time",
			    "Mercury",
			    "Football",
			    "Boxing",
			    "Time"
			));
		
	}

	/**
	 * Description : Gives rock question and answers
	 */
	private void getRockQA() {
		rockQuestions.addAll(Arrays.asList(
			    "Which is the largest country in the world?",
			    "Which country has the largest population in the world?",
			    "In which country would you find the Leaning Tower of Pisa?",
			    "Which planet is nearest to the Earth??",
			    "Which is the largest country in the world?",
			    "Which country has the largest population in the world?",
			    "In which country would you find the Leaning Tower of Pisa?",
			    "Which planet is nearest to the Earth??"
			));

			rockAnswers.addAll(Arrays.asList(
			    "Russia",
			    "China",
			    "Italy",
			    "Venus",
			    "Russia",
			    "China",
			    "Italy",
			    "Venus"
			));

		
	}

	/**
	 * Description : Gives science question and answers
	 */
	private void getSceinceQA() {
		scienceQuestions.addAll(Arrays.asList(
			    "Which animal lays eggs?",
			    "A male cow is called?",
			    "All animals need food, air, and ____ to survive.",
			    "Which one is a fur-bearing animal?",
			    "Which animal lays eggs?",
			    "A male cow is called?",
			    "All animals need food, air, and ____ to survive.",
			    "Which one is a fur-bearing animal?"
			));

			scienceAnswers.addAll(Arrays.asList(
			    "Duck",
			    "Ox",
			    "Water",
			    "Cat",
			    "Duck",
			    "Ox",
			    "Water",
			    "Cat"
			));

		
	}

	/**
	 * Description : Gives sports question and answers
	 */
	private void getSportsQA() {
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

		
	}

	public String createRockQuestion(int index) {
		return "Rock Question " + index;
	}

	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	/**
	 * Description: Adds the player in the Game.
	 * @param playerName
	 * @return returns true
	 */
	public boolean add(String playerName) {

		players.add(playerName);
		places[howManyPlayers()] = 0;
		purses[howManyPlayers()] = 0;
		inPenaltyBox[howManyPlayers()] = false;

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + players.size());
		return true;
	}

	/**
	 * @return returns the total number of player
	 */
	public int howManyPlayers() {
		return players.size();
	}

	/**
	 * Description: This method roll(int roll) gets the number generated by the Random.rand.nextInt() and 
	 *              throws the question based on place and category.
	 *              It internally checks the penalty of the user also if dice is even player will not come
	 *              out of Penalty box. Only if in penalty as well dice is odd player will be moved out.
	 * @param roll - 
	 */
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

	//
	/**
	 * Description: It prints the correct answer message and checks if player is eligible for winning if yes 
	 *              then declares winner and exit.
	 *              If not winner yet, move to next player.
	 * @return true
	 */
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

	
	/**
	 * Description: It prints incorrect message and put player in penalty box.
	 *              and moves to next player.
	 * @return
	 */
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
	
	
	/**
	 * Description: Post the question to player. 
	 */
	private void askQuestion() {
	    String currentCategory = currentCategory();
	    String currentQuestion = "";

	    try {
			if (currentCategory.equalsIgnoreCase(GameEnums.Pop.toString())) {
			    questionList = popQuestions;
			    answerList = popAnswers;
			} else if (currentCategory.equalsIgnoreCase(GameEnums.Science.toString())) {
			    questionList = scienceQuestions;
			    answerList = scienceAnswers;
			} else if (currentCategory.equalsIgnoreCase(GameEnums.Sports.toString())) {
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
			else 
			{
				System.out.println("Question list is empty for " + currentCategory + "category.");
			}
		} catch (Exception e) {
			System.out.println("Error occured : " +e.getMessage());
		}
	}

	
	/**
	 * @param strCategory - takes the category
	 * @param question - takes the question
	 * @param inputFromUser - takes the user input
	 * @return true if answer is correct else false.
	 */
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
