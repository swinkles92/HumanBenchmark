import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

public class NumberMemoryGame {
    private final Scene scene;
    private final BorderPane startScreen;
    private final BorderPane gameScreen;
    private int currPow10 = 0;
    private int currNum;

    /**
     Input: Scene variable to transition to,
     Pane variable for main menu to go back to
     */
    public NumberMemoryGame(Scene scene, BorderPane mainMenu) {
        this.scene = scene;
        startScreen = new BorderPane();
        gameScreen = new BorderPane();

        VBox startVBox = new VBox(10);

        Label numberMemoryTitle = new Label("Number Memory");
        numberMemoryTitle.setFont(new Font(40));
        numberMemoryTitle.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("You will be shown an increasingly long number. " +
                "Type it correctly to proceed. Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });
        Button startButton = new Button("Start");

        startVBox.getChildren().addAll(numberMemoryTitle, gameDesc,
                startButton, backButton);
        startVBox.setAlignment(Pos.CENTER);
        startScreen.setCenter(startVBox);

        VBox gameVBox = new VBox(10);
        currNum = generateNum();
        Label numberLabel = new Label(Integer.toString(currNum));
        numberLabel.setFont(new Font(50));
        TextField textField = new TextField();
        textField.setMaxWidth(200);
        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(event -> {
            currNum = gameLoop(numberLabel, currNum,
                    Integer.parseInt(textField.getText()));
            textField.clear();
        });
        Button retryButton = new Button("Retry");
        gameVBox.getChildren().addAll( numberLabel, textField, submitBtn,
                retryButton, backButton);
        gameVBox.setAlignment(Pos.CENTER);
        gameScreen.setCenter(gameVBox);
        startButton.setOnAction(event -> {
            scene.setRoot(gameScreen);
            currNum = gameLoop(numberLabel, 0, 0);
        });
        retryButton.setOnAction(event -> {
            currPow10 = 0;
            currNum = gameLoop(numberLabel, 0, 0);
        });
    }
    // Generates new number based on current power of 10
    public int generateNum() {
        return ThreadLocalRandom.current().nextInt(
                1 * (int)Math.pow(10,currPow10),
                10 * (int)Math.pow(10,currPow10));
    }
    // Compares user input with current, correct number
    // If user is correct, new number is generated and
    // animation timeline is used to flash number
    public int gameLoop(Label numberLabel, int currNum, int userInput) {
        if(currNum == userInput) {
            currNum = generateNum();
            numberLabel.setText(Integer.toString(currNum));
            Timeline t = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(numberLabel.opacityProperty(), 100)),
                    new KeyFrame(Duration.seconds(2), new KeyValue(numberLabel.opacityProperty(), 0))
            );
            t.play();
            currPow10++;
        }
        else numberLabel.setText("Game Over!");
        return currNum;
    }
    // Transfers focus from main menu to game
    public void show() { scene.setRoot(startScreen); }
    // Used for CSV writing
    public int getScore() {
        return currPow10;
    }
}