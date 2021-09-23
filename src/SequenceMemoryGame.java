import javafx.animation.AnimationTimer;
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
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class SequenceMemoryGame {
    private final Scene scene;
    private final BorderPane startScreen;
    private final TilePane gameBoard;
    private ArrayList gameSequence = new ArrayList();

    public SequenceMemoryGame(Scene scene, int SIZE, TilePane mainMenu) {
        this.scene = scene;
        startScreen = new BorderPane();
        gameBoard = new TilePane();
        Score score = new Score();
        score.setScore(0);
        VBox startVBox = new VBox(10);
        VBox gameVBox = new VBox(25);

        Label sequenceMemoryTitle = new Label("Sequence Memory");
        sequenceMemoryTitle.setFont(new Font(40));
        sequenceMemoryTitle.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("Memorize the sequence of buttons that " +
                "light up, then press them in order. Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);
        Label scoreLabel = new Label("Score: " + score.getScore());
        scoreLabel.setFont(new Font(40));
        Button backButton = new Button("Back to Main Menu");

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate;
            @Override
            public void handle(long now) {
                if(now - lastUpdate > 1_000_000_000) {
                    if(!gameSequence.isEmpty()) {
                        scene.setRoot(gameVBox);
                        gameVBox.getChildren().clear();
                        gameVBox.getChildren().addAll(
                                scoreLabel,
                                generateBoard(gameBoard, score, SIZE, (int)gameSequence.get(0)),
                                backButton);
                    }
                    else {
                        scene.setRoot(gameVBox);
                        gameVBox.getChildren().clear();
                        gameVBox.getChildren().addAll(
                                scoreLabel,
                                generateBoard(gameBoard, score, SIZE, -1),
                                backButton);
                    }
                    lastUpdate = now;
                }
            }
        };
        backButton.setOnAction(event -> {
            timer.stop();
            scene.setRoot(mainMenu);
        });
        Button startBtn = new Button("Start");
        startBtn.setOnAction(event -> {
            score.setScore(5);
            generateSequence(score, gameSequence);
            timer.start();
        });

        startVBox.getChildren().addAll(sequenceMemoryTitle, gameDesc,
                startBtn, backButton);
        startVBox.setAlignment(Pos.CENTER);
        startScreen.setCenter(startVBox);

        gameVBox.setAlignment(Pos.CENTER);
        gameVBox.getChildren().addAll(scoreLabel, gameBoard, backButton);

    }
    public TilePane generateBoard(TilePane gameBoard, Score score, int SIZE, int currentSquare) {
        gameBoard = new TilePane();
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.setVgap(2.5);
        gameBoard.setHgap(2.5);
        gameBoard.setMaxWidth(SIZE / 2);
        if(currentSquare >= 0) {
            for(int i = 0; i < 9; i++) {
                Rectangle rect = new Rectangle(SIZE / 6.5, SIZE / 6.5);
                if(gameSequence.contains(i + 1)) {
                    rect.setFill(Color.DARKRED);
                }
                else rect.setFill(Color.DIMGREY);
                rect.setOnMouseClicked(event -> {
                    System.out.println(1);
                        });
                gameBoard.getChildren().add(rect);
            }
            gameSequence.remove(0);
        }
        else {
            for(int i = 0; i < 9; i++) {
                Rectangle rect = new Rectangle(SIZE / 6.5, SIZE / 6.5);
                rect.setFill(Color.DIMGREY);
                rect.setOnMouseClicked(event -> {
                    System.out.println(2);
                });
                gameBoard.getChildren().add(rect);
            }
        }
        return gameBoard;
    }
    public void generateSequence(Score score, ArrayList gameSequence) {
        if(score.getScore() <= 9) score.setScore(score.getScore() + 1);
        int numSequence = score.score;
        for(int i = 0; i < numSequence; i++) {
            gameSequence.add(ThreadLocalRandom.current().nextInt(1, 10));
        }
    }
    private class Score {
        private int score;
        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }
    }
    public void show() { scene.setRoot(startScreen); }
}
