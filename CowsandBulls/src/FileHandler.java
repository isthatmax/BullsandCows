import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public ArrayList<String> readFile (String fileName) throws IOException {
        BufferedReader myReader = null;
        ArrayList<String> autoGuesses = new ArrayList<>();

        try { // in a try block to catch in case of exception so program doesn't break (in case file is named wrong, corrupted, not in filepath etc.)
            String strCurrentLine;
            myReader = new BufferedReader(new FileReader(fileName));

            while ((strCurrentLine = myReader.readLine()) != null) { // while the current buffered reader readline is not empty....
                autoGuesses.add(strCurrentLine);
            }

        }
        catch (IOException error) { // catch happens if input/output exception happens during the try (breaks)
            throw new IOException();
        }
        finally {
            try {
                if (myReader != null)
                    myReader.close(); // close reader down
            }
            catch (IOException error) {
                throw new IOException();
            }
        }

        return autoGuesses;
    }

    public void saveFile (String fileName, String playerCode, String computerCode, ArrayList<GuessResponse> guesses, String gameResult, String lastGuess) throws IOException {

        BufferedWriter myBWriter = null;
        try {
            fileName = "Results\\\\" + fileName;
            File file = new File(fileName);

            while(file.exists()){
                int i = 1;
                fileName = fileName + i;
                file = new File(fileName);
                i++;
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter myFWriter = new FileWriter(file);
            myBWriter = new BufferedWriter(myFWriter);
            myBWriter.write("Bulls & Cows game result.");
            myBWriter.newLine();
            myBWriter.write("Players Email: " + playerCode);
            myBWriter.newLine();
            myBWriter.write("Computer code: " + computerCode);
            myBWriter.newLine();

            for (int i = 0; i < guesses.size(); i++) {
                if (i % 2 == 0) {
                    myBWriter.write("Turn " + (int)(i / 2 + 1) + ":");
                    myBWriter.newLine();
                }
                GuessResponse guess = guesses.get(i);

                if (i % 2 == 0) {
                    myBWriter.write("You");
                }
                else {
                    myBWriter.write("Computer");
                }
                    myBWriter.write(" guessed " + guess.guess + ", scoring " + guess.bulls + " Bulls and " + guess.cows + " Cows.");
                    myBWriter.newLine();
            }

            if (gameResult.equals("Player wins")) {
                myBWriter.write("Turn " + (int)(guesses.size() / 2 + 1) + ":");
                myBWriter.newLine();
            }

            myBWriter.write(gameResult);
            if (!gameResult.equals("Draw")) {
                myBWriter.write(" with a guess of " + lastGuess + ".");
            }

        }
        catch (IOException e) {
            throw new IOException();
        }
        finally
        {
            try{
                if(myBWriter != null) {
                    myBWriter.close();
                }
            }
            catch(Exception e){
                throw new IOException();
            }
        }
    }
}
