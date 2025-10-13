# CasinoManager Simulation
CASINO MANAGER - CS 151
Group Members:
- Alex Wille
- Emily
- Gibson
- Piero

Distribution of the work and classes:
Classes:
Playable (Interface) → Piero
Methods
void play()
void rules()
double earned()

Game (Abstract) implements Playable → Emily
Methods
getName()
getRequiredBet()
displayGameName() → “You are now playing …”
toString() → returns the name of the game and the required bet to play the game

Blackjack (extends Game) → Piero
Methods
deal()
hit()
double()
split()
Overridden methods
play() → player’s bet and checks if they win
rules() → shows player the rules of the chosen game
earned() → how much the player earns based on how much they bet

Craps (extends Game) → Gib
Methods
rollDice()
bet()
Overridden methods
play() → player’s bet and checks if they win
rules() → shows player the rules of the chosen game
earned() → how much the player earns based on how much they bet


Roulette (extends Game) → Alex
spin()
chooseNumber()
Overridden methods
play() → player’s bet and checks if they win
rules() → shows player the rules of the chosen game
earned() → how much the player earns based on how much they bet

Player → Emily
getName()
getPlayerID()
displayBalance()
deposit()
withdraw()

Main → Alex
Create player objects
Create Scanner for player to choose game
Exits the game

Table → Gib
Holds the game, list of players, method to switch back to UI to choose another game


Usage
When prompted, enter your name and starting balance.
Choose a game from the main menu:
1 – Roulette
2 – Blackjack
3 – Craps
0 – Exit
Each game will display its rules and prompt for betting amounts and actions.
After each round, you can choose whether to play again or return to the main menu.
Type EXIT at any time to quit the program safely.
