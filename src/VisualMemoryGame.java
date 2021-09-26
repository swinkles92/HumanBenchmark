import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class VisualMemoryGame {
    private final Scene scene;
    private final BorderPane startScreen;
    private final BorderPane gameScreen;
    private TilePane gameBoard;
    private LinkedList<Rectangle> rectList = new LinkedList<>();
    private LinkedList<Integer> gameSequence = new LinkedList<>();
    private int score = 0;
    private Label scoreLabel = new Label();

    public VisualMemoryGame(Scene scene, int SIZE, TilePane mainMenu) {
        this.scene = scene;
        startScreen = new BorderPane();
        gameScreen = new BorderPane();
        gameBoard = generateBoard(SIZE);
        VBox startVBox = new VBox(10);
        VBox gameVBox = new VBox(25);

        Label title = new Label("Visual Memory");
        title.setFont(new Font(40));
        title.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("Click the boxes according to their numbers. " +
                "Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);
        Button backButton = new Button("Back to Main Menu");
        Button startBtn = new Button("Start");
        startVBox.getChildren().addAll(title, gameDesc, startBtn, backButton);
        startVBox.setAlignment(Pos.CENTER);
        startScreen.setCenter(startVBox);

        scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(new Font(40));
        scoreLabel.setAlignment(Pos.CENTER);
        gameVBox.setAlignment(Pos.CENTER);
        gameVBox.getChildren().addAll(scoreLabel, gameBoard, backButton);
        gameScreen.setCenter(gameVBox);


        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });
        startBtn.setOnAction(event -> {
            scene.setRoot(gameScreen);
            gameLoop();
        });

    }
    public TilePane generateBoard(int SIZE) {
        TilePane gameBoard = new TilePane();
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.setVgap(2.5);
        gameBoard.setHgap(2.5);
        gameBoard.setMaxWidth(SIZE / 2);
        for(int i = 0; i < 20; i++) {
            int counter = i;
            Rectangle rect = new Rectangle(SIZE / 10, SIZE / 10);
            rect.setFill(Color.DIMGREY);
            rect.setOnMouseClicked(event -> {
                if(!gameSequence.isEmpty()) {
                    if(gameSequence.contains(counter)) {
                        gameSequence.removeFirstOccurrence(counter);
                        Timeline t = new Timeline(
                                new KeyFrame(Duration.seconds(0),
                                        new KeyValue(rect.fillProperty(), Color.GREEN)),
                                new KeyFrame(Duration.seconds(.5 - 0.001),
                                        new KeyValue(rect.fillProperty(), Color.GREEN)),
                                new KeyFrame(Duration.seconds(.5),
                                        new KeyValue(rect.fillProperty(), Color.DIMGREY))
                        );
                        t.play();
                        if(gameSequence.isEmpty()) {
                            score++;
                            scoreLabel.setText("Score: " + score);
                            gameLoop();
                        }
                    }
                    else scoreLabel.setText("Game over!");
                }
            });
            rectList.add(rect);
            gameBoard.getChildren().add(rect);
        }
        return gameBoard;
    }
    public void gameLoop() {
        gameSequence.clear();
        generateSequence();
        for(int i = 0; i < gameSequence.size(); i++) {
            int curr = gameSequence.get(i);
            Timeline t = playAnimation(curr);
            t.play();
        }
    }
    public Timeline playAnimation(int curr) {
        Timeline t = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(rectList.get(curr).fillProperty(), Color.GOLD)),
                new KeyFrame(Duration.seconds(1.5 - 0.001),
                        new KeyValue(rectList.get(curr).fillProperty(), Color.GOLD)),
                new KeyFrame(Duration.seconds(1.5),
                        new KeyValue(rectList.get(curr).fillProperty(), Color.DIMGREY))
        );
        return t;
    }
    public void generateSequence() {
        int rand;
        for(int i = 0; i < score + 1; i++) {
            rand = ThreadLocalRandom.current().nextInt(0,20);
            if(gameSequence.contains(rand)) {
                while(gameSequence.contains(rand)) {
                    rand = ThreadLocalRandom.current().nextInt(0,20);
                }
                gameSequence.push(rand);
            }
            else gameSequence.push(rand);
        }
    }
    public void show() { scene.setRoot(startScreen); }
}