import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.concurrent.ThreadLocalRandom;

public class MathSolverGame {
    private final Scene scene;
    private BorderPane root;
    private int x;
    private int y;
    private int numLimit = 1;
    private char operation;
    private boolean gameStartFlag = false;
    private int lives = 3;
    private int score = 0;

    public MathSolverGame(Scene scene, int SIZE, BorderPane mainMenu) {
        this.scene = scene;
        root = new BorderPane();

        VBox vBox = new VBox(10);
        Label mathSolverTitle = new Label("Math Solver");
        mathSolverTitle.setFont(new Font(40));
        mathSolverTitle.setAlignment(Pos.CENTER);

        Text gameDesc = new Text("Type the solution to the equation " +
                "in the box. Press Submit to submit or start the game.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);

        HBox eqHBox = new HBox(10);
        x = 1;
        Label xLabel = new Label(Integer.toString(x));
        xLabel.setFont(new Font(20));
        operation = '+';
        Label opLabel = new Label(Character.toString(operation));
        opLabel.setFont(new Font(20));
        y = 1;
        Label yLabel = new Label(Integer.toString(y));
        yLabel.setFont(new Font(20));
        Label eqLabel = new Label("=");
        eqLabel.setFont(new Font(20));
        TextField textField = new TextField();
        eqHBox.getChildren().addAll(xLabel, opLabel, yLabel, eqLabel, textField);
        eqHBox.setAlignment(Pos.CENTER);

        HBox gameInfoHbox = new HBox(10);
        Label livesLabel = new Label("Lives: " + lives);
        Label scoreLabel = new Label("Score: " + score);
        gameInfoHbox.getChildren().addAll(livesLabel, scoreLabel);
        gameInfoHbox.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(event -> {
            if(gameStartFlag) {
                calculate(livesLabel, scoreLabel, opLabel, Integer.parseInt(textField.getText()));
                gameLoop(xLabel, yLabel, opLabel);
            }
            else {
                gameStartFlag = true;
                calculate(livesLabel, scoreLabel, opLabel, Integer.parseInt(textField.getText()));
                gameLoop(xLabel, yLabel, opLabel);
            }
        });

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });

        Button retryButton = new Button("Retry");
        retryButton.setOnAction(event -> {
            lives = 3;
            score = 0;
            numLimit = 1;
            scoreLabel.setText("Score: " + score);
            livesLabel.setText("Lives: " + lives);
            gameLoop(xLabel, yLabel, opLabel);
        });
        vBox.getChildren().addAll(mathSolverTitle, gameDesc, eqHBox,
                gameInfoHbox, submitBtn, retryButton, backButton);
        vBox.setAlignment(Pos.CENTER);
        root.setCenter(vBox);
    }
    public void gameLoop(Label xLabel, Label yLabel, Label opLabel) {
        numLimit++;

        x = ThreadLocalRandom.current().nextInt(1, (int)Math.pow(2, numLimit));
        y = ThreadLocalRandom.current().nextInt(1, (int)Math.pow(2, numLimit));
        xLabel.setText(Integer.toString(x));
        yLabel.setText(Integer.toString(y));
        int opInt = ThreadLocalRandom.current().nextInt(4);
        switch (opInt) {
            case 0: opLabel.setText("+");
                    break;
            case 1: opLabel.setText("-");
                    break;
            case 2: opLabel.setText("/");
                    break;
            case 3: opLabel.setText("*");
                    break;
            case 4: opLabel.setText("%");
                    break;
            }

    }
    public void calculate(Label livesLabel, Label scoreLabel, Label opLabel, int answer) {
        switch (opLabel.getText()) {
            case "+":
                if(x + y == answer) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
                else {
                    lives--;
                    livesLabel.setText("Lives: " + lives);
                }
                break;
            case "-":
                if(x - y == answer) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
                else {
                    lives--;
                    livesLabel.setText("Lives: " + lives);
                }
                break;
            case "/":
                if(x / y == answer) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
                else {
                    lives--;
                    livesLabel.setText("Lives: " + lives);
                }
                break;
            case "*":
                if(x * y == answer) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
                else {
                    lives--;
                    livesLabel.setText("Lives: " + lives);
                }
                break;
            case "%":
                if(x % y == answer) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
                else {
                    lives--;
                    livesLabel.setText("Lives: " + lives);
                }
                break;
        }
    }
    public void show() { scene.setRoot(root); }
    public int getScore() {
        return score;
    }
}
