                                                                                  
# Welcome to Drip World

Hopefully you enjoy the game as much as I enjoyed making it!
All the art was done by my girlfriend Mina Stevens and of course all the code was done by me.

## Build Instructions:
Compile ALL classes and Run the ExecuteGame.java.

## Controls:
•Press [s] to start. 
 Use the arrow keys to control the spaceship.

## Objective
The objective is to land on the landing platform perfectly straight.
Landing sideways or slightly slanted will cause a crash and respawn.


## Contains the following
• There is animation - the coins, ship, drops, treasure chest, landing platform, fuel level, explosion and drip guy are all animated. 

• It is interactive - the ship is controlled through a keyboard.

• There is a scoring mechanism and it is shown to the player - the score is on the top left and it increases as you collect coins, or decreases if you crash the ship. 

• There is a definitive ending mechanism - If you crash 3 times you lose, if you land on the platform you win!

• There is a physical mechanism - This game has constant pull of gravity, velocity based on angle, and air drag.

• There is collision detection - You can collide against the walls, falling drips, and the drip guy. Also you can collide with the coins, or treasure to collect them.

• It is creative - Although a subjective requirement, I would venture to say that I tried my hardest to make it creative.

• You implement a flourish - I implemented more than one flourish, firsly it looks really good, also has non-stationary collidables, and a score that tallies at the end.


## Description of the classes:

### ExecuteGame.java:
Sets the window size and adds the game to the window. Resizable is set to false, so that all the art can be displayed with its full resolution.

### FlyingDrone.java:
This class contains the code of the entire game. There is a timer that ticks and calls repaint(). In paintComponent there are all the images drawn that make up the screen. 
Collisions are checked via a rectangular hitbox. A collision may trigger a respawn or a score increase. 
Furthermore, the class has some variables such as, m, c, which are used to make the animations.  

### GoldCoins.java:
Used to keep track of whether a coin has been picked up.

### DripBalls.java:
Keeps track of the drips position.
If you look closely you will notice the drips leave a puddle behind!





