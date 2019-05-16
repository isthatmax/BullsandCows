import java.util.ArrayList;
import java.util.Arrays;

public class SecretCode {

    private ArrayList<Integer> code;
    private int codeSize = 4;

    public SecretCode(String userCode) { setCodeFromString(userCode); }

    public void setCodeFromString(String playerCode) {
       ArrayList<String> codeString = new ArrayList<>(Arrays.asList(playerCode.split("")));

       ArrayList<Integer> codeInt = new ArrayList<>();
       for(int i = 0; i < codeString.size(); i++) {
           codeInt.add(Integer.parseInt(codeString.get(i)));
       }

       if (codeInt.size() != codeSize) {
            throw new IllegalArgumentException("Code not correct size");
       }

        for (int x = 0; x < codeInt.size(); x++) {
            for (int y = 0; y < codeInt.size(); y++) {
                if (x != y && codeInt.get(x).equals(codeInt.get(y))) {
                    throw new IllegalArgumentException("Numbers were not different");
                }
            }
        }

       code = codeInt;
    }

    
    public SecretCode() {
        generateCode();
    }

    public void generateCode() {
        int randomNoLength = codeSize;
        ArrayList<Integer> numberArray = new ArrayList<>();
        int counter = 0;
        while (counter < randomNoLength) {
            int numberToAdd = (int)(Math.random() * 10);
            boolean numberAlreadyExists = false;
            for (int x = 0; x < numberArray.size(); x++) {
                if (numberToAdd == numberArray.get(x)) {
                    numberAlreadyExists = true;
                    break;
                }
            }
            if (!numberAlreadyExists) {
                numberArray.add(numberToAdd);
                counter++;
            }
        }
        code = numberArray;
    }

    public boolean codeMatches(String guessString) {
        if (guessString.equals(toString())) {
            return true;
        }
        return false;
    }

    public int[] bullsAndCows(String guessString) {
        int bullCount = 0;
        int cowCount = 0;

        for (int x = 0; x < guessString.length(); x++) {
            int guessNumber = Character.getNumericValue(guessString.charAt(x));
            for (int y = 0; y < code.size(); y++) {
                if (guessNumber == code.get(y)) {
                    if (x == y) {
                        bullCount++;
                    }
                    else {
                        cowCount++;
                    }
                    break;
                }
            }
        }

        return new int[] {bullCount, cowCount};
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer number : code)
        {
            sb.append(number);
        }
        return sb.toString();
    }


}

