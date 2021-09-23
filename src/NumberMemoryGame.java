import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.concurrent.ThreadLocalRandom;

public class NumberMemoryGame {
    private final Scene scene;
    private final BorderPane startScreen;
    private final BorderPane gameScreen;
    private int currPow10 = 0;
    private int currNum;

    public NumberMemoryGame(Scene scene, int SIZE, TilePane mainMenu) {
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
        startButton.setOnAction(event -> {
            scene.setRoot(gameScreen);
        });

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
                    Integer.parseInt(textField.getText().toString()));
        });
        gameVBox.getChildren().addAll(numberLabel, textField,
                submitBtn, backButton);
        gameVBox.setAlignment(Pos.CENTER);
        gameScreen.setCenter(gameVBox);
    }
    public int generateNum() {
        return ThreadLocalRandom.current().nextInt(
                1 * (int)Math.pow(10,currPow10),
                10 * (int)Math.pow(10,currPow10));
    }
    public int gameLoop(Label numberLabel, int currNum, int userInput) {
        if(currNum == userInput) {
            currPow10++;
            currNum = generateNum();
            numberLabel.setText(Integer.toString(currNum));
        }
        else numberLabel.setText("Game Over!");
        return currNum;
    }
    public void show() { scene.setRoot(startScreen); }
}