import javax.security.auth.kerberos.KerberosTicket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;

public class Game {

    public void start() {
        SecretCode computerCode = new SecretCode();
        boolean manualGuessing = true;
        boolean guessMethod = true;

         FileHandler fileHandler = new FileHandler();
        
        System.out.println("Enter your guess: ");

        int playerGuessLimit = 7;
        boolean gameOver = false;

        ArrayList<GuessResponse> guesses = new ArrayList<>();
        String Lengtherror[] = new String[4];
        Lengtherror[0] = "";
        Lengtherror[1] = "You know the number is 4 digits right? That still counts as a guess though.";
        Lengtherror[2] = "Its actually harder if you don't guess 4 digits";
        Lengtherror[3] = "pssst its 4 digits....";

        String lastGuess = null;
        String gameResult = null;

        for (int counter = 0; counter < playerGuessLimit; counter++) {
            String playerGuess = null;
            
            playerGuess = Keyboard.readInput();

            if(playerGuess.length() != 4){
                System.out.println(Lengtherror[playerGuess.length() % 4]);
            }

            if (computerCode.codeMatches(playerGuess)) {
                System.out.println("You win!");
                lastGuess = playerGuess;
                gameResult = "Player wins";
                gameOver = true;
                break;
            }
            else {
                int[] bullCows = computerCode.bullsAndCows(playerGuess);
                guesses.add(new GuessResponse(playerGuess, bullCows[0], bullCows[1]));
                System.out.println(("Your guess was " + playerGuess));
                System.out.println("Bulls: " + bullCows[0] + " Cows: " + bullCows[1]);
                System.out.println();
            }

        }

        if (!gameOver) {
            gameResult = "Draw";
            System.out.println("You're all out of guesses. The code was " + computerCode.toString());
        }

        boolean saveResult = true;

        


        if (saveResult && (gameResult == "Player wins")) {
            System.out.println("Whats your name:");
            boolean saved = false;

            while (saved == false) {
                if (saveResult) {
                    String fileName = Keyboard.readInput();
                    System.out.println("Enter your email to go into the draw for a paid of Sennheiser Headphones:");
                    String email = Keyboard.readInput();
                    try {
                        fileHandler.saveFile(fileName, email, computerCode.toString(), guesses, gameResult, lastGuess);
                        saved = true;
                    }
                    catch (IOException e) {
                        System.out.println("Error saving, please enter new file name:");
                    }
                }
            }
        }
        else{
            System.out.println("Bad luck, hit enter to try again?");
            Keyboard.readInput();
        }
    }

    public static void main(String[] args) {
        
        while(true){
            Game game = new Game();
            game.start();
            for(int i = 0; i < 50; i++) System.out.println();
        }
    }
}