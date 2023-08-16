package com.adaptionsoft.games.trivia;


import org.junit.jupiter.api.Test;

import com.adaptionsoft.games.uglytrivia.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class GameTest {

    private Game game;
    
    @BeforeEach
    public void setUp()
    {
     game = new Game();
     game.add("pPlayer_1");// for test check penalty test cases
     game.add("pPlayer_2");
    }
    
    @Test
    public void testAddPLayer()
    {
    	assertTrue(game.add("Player1"));
    	assertEquals(1,game.howManyPlayers());
    	

    	assertTrue(game.add("Player1"));
    	assertEquals(2,game.howManyPlayers());
    }

    @Test
   public void testPenaltyCheckWhenGettingOutOfPenaltyBox()
   {
    	//TODO: to roll(3) on odd number then set penalty true and roll(1) to odd number 
    	//       and check assert false InPenaltyBox should return false.
     	game.roll(3);
        //TODO: I need to get the current player penalty to true
     	assertFalse(false); //TODO: need to replace with actual penalty variable.
   }
    
    @Test
    public void testPenaltyCheckWhenStayingInPenaltyBox()
    {
    	//TODO: to roll(3) on odd number then set penalty true and roll(2) to even number 
    	//       and check assert false InPenaltyBox should return true.
     	game.roll(3);
     	
        // I need to get the current player penalty to true
     	
     	game.roll(2);
     	
     	assertTrue(true); //TODO: need to replace with actual penalty variable.
    }
    
    
    
    
  }
 
