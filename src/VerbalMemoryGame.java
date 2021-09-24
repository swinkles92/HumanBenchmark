import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class VerbalMemoryGame {
    private final Scene scene;
    private final BorderPane startScreen;
    private final BorderPane gameScreen;
    private int score = 0;
    private String currWord;
    private Label scoreLabel;
    private Label currWordLabel;
    private File wordFile = new File("./resources/dictionary.txt");
    private List wordsList = ParseFile.readFileToList(wordFile.toString());
    private ArrayList<String> seenWordsList = new ArrayList<>();


    public VerbalMemoryGame(Scene scene, TilePane mainMenu) {
        this.scene = scene;
        startScreen = new BorderPane();
        gameScreen = new BorderPane();

        VBox startVBox = new VBox(10);
        Label title = new Label("Verbal Memory");
        title.setFont(new Font(40));
        title.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("The test measures how many words" +
                " you can keep in short term memory at once. Click" +
                " start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);

        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            scene.setRoot(gameScreen);
            int rand = ThreadLocalRandom.current().nextInt(1, wordsList.size());
            currWord = (String)wordsList.get(rand);
            currWordLabel.setText(currWord);
        });
        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });

        startVBox.getChildren().addAll(title, gameDesc, startButton,
                backButton);
        startVBox.setAlignment(Pos.CENTER);
        startScreen.setCenter(startVBox);

        VBox gameVBox = new VBox(10);
        scoreLabel = new Label("Score: " + score);
        currWordLabel = new Label();
        HBox gameHBox = new HBox(20);
        Button newButton = new Button("New");
        newButton.setOnAction(event -> {
            gameLoop(false);
        });
        Button seenButton = new Button("Seen");
        seenButton.setOnAction(event -> {
            gameLoop(true);
        });
        gameHBox.getChildren().addAll(newButton, seenButton);
        gameHBox.setAlignment(Pos.CENTER);
        gameVBox.getChildren().addAll(scoreLabel, currWordLabel, gameHBox, backButton);
        gameVBox.setAlignment(Pos.CENTER);
        gameScreen.setCenter(gameVBox);

    }
    public void gameLoop(boolean wordSeen) {
        int rand;
        if(wordSeen) {
            if(seenWordsList.contains(currWord)) {
                score++;
                scoreLabel.setText("Score: " + score);
                rand = ThreadLocalRandom.current().nextInt(1, wordsList.size());
                currWord = (String)wordsList.get(rand);
                currWordLabel.setText(currWord);
            }
            else {
                currWordLabel.setText("Game Over");
            }
        }
        else {
            if(seenWordsList.contains(currWord)) {
                System.out.println("4");
                currWordLabel.setText("Game Over");
            }
            else {
                seenWordsList.add(currWord);
                score++;
                scoreLabel.setText("Score: " + score);
                rand = ThreadLocalRandom.current().nextInt(1, wordsList.size() - 1);
                currWord = (String)wordsList.get(rand);
                currWordLabel.setText(currWord);
            }
        }
    }
    public class ParseFile {
        public static List<String> readFileToList(String fileName) {
            List<String> textLines = Collections.emptyList();
            try {
                textLines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return textLines;
        }
    }
    public void show() { scene.setRoot(startScreen); }
}