# DroneGame                                                                                       
 _ _ _     _                      _          _____ _             _      ____      _     
| | | |___| |___ ___ _____ ___   | |_ ___   |  _  | |___ ___ ___| |_   |    \ ___|_|___ 
| | | | -_| |  _| . |     | -_|  |  _| . |  |   __| | .'|   | -_|  _|  |  |  |  _| | . |
|_____|___|_|___|___|_|_|_|___|  |_| |___|  |__|  |_|__,|_|_|___|_|    |____/|_| |_|  _|
                                                                                   |_|                        
Hopefully you enjoy the game as much as I enjoyed making it!
All the art was done by my girlfriend Mina Stevens and of course all the code was done by me.

Compile and Run the ExecuteGame.java.

The controls of the game are simple, press [s] to start. 
From there onwards use the arrow keys to control the ship, your objective is to land on the landing platform perfectly straight.
Landing sideways or slightly slanted will cause a crash and respawn.

Hopefully all the images should load as intended as the file path is made using the user's directory and file seperator.
Also, although there are no confirmed bugs, ocassionally if you click [s] to quick from the welcome screen it could cause it to not load. If so relaunch the game.


Here are all the requirements I fullfilled:

• There is animation - the coins, ship, drops, treasure chest, landing platform, fuel level, explosion and drip guy are all animated. 

• It is interactive - the ship is controlled through a keyboard.

• There is a scoring mechanism and it is shown to the player - the score is on the top left and it increases as you collect coins, or decreases if you crash the ship. 

• There is a definitive ending mechanism - If you crash 3 times you lose, if you land on the platform you win!

• There is a physical mechanism - This game has constant pull of gravity, velocity based on angle, and air drag.

• There is collision detection - You can collide against the walls, falling drips, and the drip guy. Also you can collide with the coins, or treasure to collect them.

• It is creative - Although a subjective requirement, I would venture to say that I tried my hardest to make it creative.

• You implement a flourish - I implemented more than one flourish, firsly it looks really good, also has non-stationary collidables, and a score that tallies at the end.


Description of the classes:
	
There are four total classes, the most extensive of which is FlyingDrone.java

ExecuteGame.java:
Pretty simple class it sets the window size and adds the game to the window. I made resizable false, so that all the art can be displayed with its full resolution.

FlyingDrone.java:
This class contains the code of the entire game basically. There is a timer that ticks and calls repaint(). In paintComponent there are all the images drawn that make up the screen. 
The way the collisions are checked, is due toeverything in the game having a hitbox of a rectangle. Once the ship intersects a hitbox something happens, such as a respawn
or a score increase. Furthermore, the class has some variables such as, m, c, which are used to make the animations. The controls are done using key listeners and the flame will be on while
the up key is pressed and turn off when it is released. After passing a level or dying I made a new screen appear to allow for a key control to dictate the next action. Once reaching the end
of the game there are no more actions and the game is over. An array is used to keep track of the scores of each level, and in the last screen they are all tallied up. 

GoldCoins.java:
The main idea behind having this class is so each individual coin can have a "touch" boolean variable. This is so each individual coin knows to disappear when touched. If each
individual coin didnt have this unique variable they would all turn off once one is touched.

DripBalls.java:
The drip ball class may have been unecessary but I was attempting to implement something such as the coin class. So the most important function of the class is the respawn method, which allows
the ball to go back to the drip guy's mouth once it hits the terrain. 







