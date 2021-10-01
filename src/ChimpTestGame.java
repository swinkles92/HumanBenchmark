import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;


public class ChimpTestGame {
    private final Scene scene;
    private final BorderPane startScreen;
    private final BorderPane gameScreen;
    private TilePane gameBoard;
    private LinkedList<Rectangle> rectList = new LinkedList<>();
    private LinkedList<Label> labelsList = new LinkedList<>();
    private LinkedList<Integer> gameSequence = new LinkedList<>();
    private int score = 0;
    private Label scoreLabel = new Label();

    /**
     * Input: Scene variable to transition to,
     * SIZE variable for window/shape creation,
     * Pane variable for main menu to go back to
     */
    public ChimpTestGame(Scene scene, int SIZE, BorderPane mainMenu) {
        this.scene = scene;
        startScreen = new BorderPane();
        gameScreen = new BorderPane();
        gameBoard = generateBoard(SIZE);

        //Start screen labels, game description, and buttons
        VBox startVbox = new VBox(10);
        Label title = new Label("Chimp Test");
        title.setFont(new Font(40));
        title.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("Click the boxes according to their numbers. " +
                "Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);
        Button backButton = new Button("Back to Main Menu");
        Button startButton = new Button("Start");
        startVbox.getChildren().addAll(title, gameDesc, startButton, backButton);
        startVbox.setAlignment(Pos.CENTER);
        startScreen.setCenter(startVbox);

        // Necessary labels and buttons for gameplay
        VBox gameVBox = new VBox(25);
        scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(new Font(40));
        scoreLabel.setAlignment(Pos.CENTER);
        Button retryButton = new Button("Retry");
        gameVBox.setAlignment(Pos.CENTER);
        gameVBox.getChildren().addAll(scoreLabel, gameBoard,
                retryButton, backButton);
        gameScreen.setCenter(gameVBox);

        startButton.setOnAction(event -> {
            scene.setRoot(gameScreen);
            gameLoop();
        });
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });
        retryButton.setOnAction(event -> {
            score = 0;
            scoreLabel.setText("Score: " + score);
            gameLoop();
        });
    }
    // Creates the 18 rectangles necessary for gameplay
    // and adds them to rectList linked list.
    // Also provides logic for gameplay to see if
    // clicked rectangle is part of the generated sequence
    public TilePane generateBoard(int SIZE) {
        TilePane gameBoard = new TilePane();
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.setVgap(2.5);
        gameBoard.setHgap(2.5);
        gameBoard.setMaxWidth(SIZE / 2);
        for(int i = 0; i < 18; i++) {
            StackPane stackPane = new StackPane();
            int counter = i;
            Rectangle rect = new Rectangle(SIZE / 8.15, SIZE / 8.15);
            rect.setFill(Color.DIMGREY);
            rect.setOnMouseClicked(event -> {
                if(!gameSequence.isEmpty()) {
                    if(counter == gameSequence.peekFirst()) {
                        gameSequence.pop();
                        if(gameSequence.isEmpty()) {
                            score++;
                            scoreLabel.setText("Score: " + score);
                            gameLoop();
                        }
                    }
                    else scoreLabel.setText("Game over!");
                }
            });
            Label label = new Label();
            label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
            label.setTextFill(Color.CORNFLOWERBLUE);
            label.setMouseTransparent(true);
            labelsList.add(label);
            rectList.add(rect);
            stackPane.getChildren().addAll(rect, label);
            gameBoard.getChildren().add(stackPane);
        }
        return gameBoard;
    }
    /*
    Clears the game sequence after each full game cycle,
    generates a new cycle, and assigns the timeline
    animation to selected rectangles
     */
    public void gameLoop() {
        gameSequence.clear();
        generateSequence();
        for(int i = 0; i < gameSequence.size(); i++) {
            int curr = gameSequence.get(i);
            labelsList.get(curr).setText(Integer.toString(i + 1));
            Timeline t = playAnimation(curr);
            t.play();
        }
    }
    /*
    Creates the animation necessary for gameplay
     */
    public Timeline playAnimation(int curr) {
        Timeline t = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(labelsList.get(curr).opacityProperty(), 100)),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(labelsList.get(curr).opacityProperty(), 0))
        );
        return t;
    }
    // Generates new sequence of random numbers necessary for gameplay
    public void generateSequence() {
        int rand;
        for(int i = 0; i < score + 1; i++) {
            rand = ThreadLocalRandom.current().nextInt(0,18);
            if(gameSequence.contains(rand)) {
                while(gameSequence.contains(rand)) {
                    rand = ThreadLocalRandom.current().nextInt(0,18);
                }
                gameSequence.push(rand);
            }
            else gameSequence.push(rand);
        }
    }
    // Transfers focus from main menu to game
    public void show() { scene.setRoot(startScreen); }
    // Used for CSV writing
    public int getScore() {
        return score;
    }
}