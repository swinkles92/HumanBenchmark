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

public class SequenceMemoryGame {
    private final Scene scene;
    private final BorderPane startScreen;
    private final BorderPane gameScreen;
    private TilePane gameBoard;
    private LinkedList<Rectangle> rectList = new LinkedList<>();
    private LinkedList<Integer> gameSequence = new LinkedList<>();
    private int score = 0;
    private Label scoreLabel = new Label();

    /**
     Input: Scene variable to transition to,
     SIZE variable for window/shape creation,
     Pane variable for main menu to go back to
     */
    public SequenceMemoryGame(Scene scene, int SIZE, BorderPane mainMenu) {
        this.scene = scene;
        startScreen = new BorderPane();
        gameScreen = new BorderPane();
        gameBoard = generateBoard(SIZE);
        VBox startVBox = new VBox(10);
        VBox gameVBox = new VBox(25);

        Label sequenceMemoryTitle = new Label("Sequence Memory");
        sequenceMemoryTitle.setFont(new Font(40));
        sequenceMemoryTitle.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("Memorize the sequence of buttons that " +
                "light up, then press them in order. Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);
        Button backButton = new Button("Back to Main Menu");
        Button startBtn = new Button("Start");

        startVBox.getChildren().addAll(sequenceMemoryTitle, gameDesc,
                startBtn, backButton);
        startVBox.setAlignment(Pos.CENTER);
        startScreen.setCenter(startVBox);

        scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(new Font(40));
        scoreLabel.setAlignment(Pos.CENTER);
        Button retryButton = new Button("Retry");
        gameVBox.setAlignment(Pos.CENTER);
        gameVBox.getChildren().addAll(scoreLabel, gameBoard,
                retryButton, backButton);
        gameScreen.setCenter(gameVBox);

        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });
        startBtn.setOnAction(event -> {
            scene.setRoot(gameScreen);
            gameLoop();
        });
        retryButton.setOnAction(event -> {
            score = 0;
            scoreLabel.setText("Score: " + score);
            gameLoop();
        });
    }
    /*
    Generates game board of 9 rectangles. Adds
    action listeners to each with action listeners
    that check if said rectangle is part of the
    generated game sequence, flashing green if it is.
    Also continues game loop if all game sequence nodes
    have been found and clicked.
     */
    public TilePane generateBoard(int SIZE) {
        TilePane gameBoard = new TilePane();
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.setVgap(2.5);
        gameBoard.setHgap(2.5);
        gameBoard.setMaxWidth(SIZE / 2);
        for(int i = 0; i < 9; i++) {
            int counter = i;
            Rectangle rect = new Rectangle(SIZE / 6.5, SIZE / 6.5);
            rect.setFill(Color.DIMGREY);
            rect.setOnMouseClicked(event -> {
                if(!gameSequence.isEmpty()) {
                    if(counter == gameSequence.peekFirst()) {
                        gameSequence.pop();
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
            rect.setMouseTransparent(true);
            rectList.add(rect);
            gameBoard.getChildren().add(rect);
        }
        return gameBoard;
    }
    /*
    Clears any existing elements in game sequence list,
    generates a new sequence, and assigns the timeline animations
    to the necessary rectangles
     */
    public void gameLoop() {
        gameSequence.clear();
        generateSequence();
        for(int i = 0; i < gameSequence.size(); i++) {
            int curr = gameSequence.get(i);
            Timeline t = playAnimation(curr);
            t.setDelay(Duration.seconds(0).add(Duration.seconds(1.5 * i)));
            t.play();
        }
    }
    /*
    Flashes the rectangle that is part of the generated game sequence
     */
    public Timeline playAnimation(int curr) {
        Timeline t = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(rectList.get(curr).fillProperty(), Color.DARKRED)),
                new KeyFrame(Duration.seconds(1.5 - 0.001),
                        new KeyValue(rectList.get(curr).fillProperty(), Color.DARKRED)),
                new KeyFrame(Duration.seconds(1.5),
                        new KeyValue(rectList.get(curr).fillProperty(), Color.DIMGREY)),
                new KeyFrame(Duration.seconds(1.5),
                        new KeyValue(rectList.get(curr).mouseTransparentProperty(), false))
        );
        return t;
    }
    /*
    Generates a sequence of numbers used for rectangle indexing
     */
    public void generateSequence() {
        int rand;
        for(int i = 0; i < score + 1; i++) {
            rand = ThreadLocalRandom.current().nextInt(0,9);
            if(gameSequence.contains(rand)) {
                while(gameSequence.contains(rand)) {
                    rand = ThreadLocalRandom.current().nextInt(0,9);
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
