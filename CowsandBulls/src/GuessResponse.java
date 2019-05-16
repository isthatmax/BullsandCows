public class GuessResponse {
    public String guess;
    public int bulls;
    public int cows;

    public GuessResponse(String guess, int bulls, int cows) {
        this.guess = guess;
        this.bulls = bulls;
        this.cows = cows;
    }
}
