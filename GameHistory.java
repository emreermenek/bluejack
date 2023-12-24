import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


public class GameHistory {
    public static final int MAX_HISTORY_SIZE = 10;

    public static void writeGameHistory(String playerName, int playerScore, int computerScore) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_history.txt", true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            String date = dateFormat.format(new Date());

            String historyEntry = playerName + ":" + playerScore + " - Computer:" + computerScore + ", " + date + "\n";

            Queue<String> gameHistoryQueue = readGameHistory();
            
            gameHistoryQueue.add(historyEntry);

            while (gameHistoryQueue.size() > MAX_HISTORY_SIZE) {
                gameHistoryQueue.poll();
            }

            try (FileWriter fileWriter = new FileWriter("game_history.txt")) {
                for (String entry : gameHistoryQueue) {
                    fileWriter.write(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Queue<String> readGameHistory() {
        Queue<String> gameHistoryQueue = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("game_history.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                gameHistoryQueue.add(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gameHistoryQueue;
    }

}
