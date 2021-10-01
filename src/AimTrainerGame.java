import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.concurrent.ThreadLocalRandom;

public class AimTrainerGame {
    private final Scene scene;
    private final BorderPane startScreen;
    private final Pane gameBoard;
    private long timeElapsed;
    private int counter = 0;

    /**
    Input: Scene variable to transition to,
           SIZE variable for window/shape creation,
           Pane variable for main menu to go back to
     */
    public AimTrainerGame(Scene scene, int SIZE, BorderPane mainMenu) {
        this.scene = scene;
        startScreen = new BorderPane();
        gameBoard = new Pane();

        //Start screen labels, game description, and buttons
        VBox startVBox = new VBox(10);
        Label aimTrainerTitle = new Label("Aim Trainer");
        aimTrainerTitle.setFont(new Font(40));
        aimTrainerTitle.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("Click the targets as quickly as you can. " +
                "Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);
        Button backButton = new Button("Back to Main Menu");
        Button startButton = new Button("Start");
        startVBox.getChildren().addAll(aimTrainerTitle, gameDesc,
                startButton, backButton);
        startVBox.setAlignment(Pos.CENTER);
        startScreen.setCenter(startVBox);

        HBox hBox = new HBox(15);
        Button retryButton = new Button("Retry");
        hBox.getChildren().addAll(retryButton, backButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.relocate(SIZE / 2 - 100, 10);
        gameBoard.getChildren().add(hBox);

        startButton.setOnAction(event -> {
            scene.setRoot(gameBoard);
            timeElapsed = System.currentTimeMillis();
            gameLoop(SIZE);
        });
        // Returns focus back to main menu
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });
        retryButton.setOnAction(event -> {
            timeElapsed = System.currentTimeMillis();
            counter = 0;
            gameBoard.getChildren().clear();
            gameBoard.getChildren().add(hBox);
            gameLoop(SIZE);
        });
    }
    // Function calculates random x, y values and relocates Aim object
    // to the location
    public void gameLoop(int SIZE) {
        int x = ThreadLocalRandom.current().nextInt(50, SIZE - 150);
        int y = ThreadLocalRandom.current().nextInt(100, SIZE - 150);
        StackPane stackPane = generateTarget(SIZE);
        stackPane.relocate(x, y);
        gameBoard.getChildren().add(stackPane);
    }
    // Creates target object and adds action listeners
    // to increment counter until game is
    // complete (30 targets clicked)
    public StackPane generateTarget(int SIZE) {
        StackPane targetPane = new StackPane();

        Circle outer = new Circle(SIZE/10, SIZE/10, SIZE/10);
        outer.setFill(Color.RED);
        targetPane.getChildren().add(outer);
        Circle middle1 = new Circle(SIZE/12, SIZE/12, SIZE/12);
        middle1.setFill(Color.GHOSTWHITE);
        targetPane.getChildren().add(middle1);
        Circle middle2 = new Circle(SIZE/14, SIZE/14, SIZE/14);
        middle2.setFill(Color.RED);
        targetPane.getChildren().add(middle2);
        Circle middle3 = new Circle(SIZE/18, SIZE/18, SIZE/18);
        middle3.setFill(Color.GHOSTWHITE);
        targetPane.getChildren().add(middle3);
        Circle inner = new Circle(SIZE/20, SIZE/20, SIZE/20);
        inner.setFill(Color.RED);
        targetPane.getChildren().add(inner);

        targetPane.setOnMouseClicked(event -> {
            if(counter < 30) {
                counter++;
                targetPane.setOpacity(0);
                targetPane.setMouseTransparent(true);
                gameLoop(SIZE);
            }
            else {
                targetPane.setOpacity(0);
                targetPane.setMouseTransparent(true);
                timeElapsed = System.currentTimeMillis() - timeElapsed;
                Label timeLabel = new Label("Your time: " + timeElapsed + " s");
                timeLabel.relocate(SIZE / 2, SIZE / 2);
                gameBoard.getChildren().add(timeLabel);
            }
        });

        return targetPane;
    }
    // Transfers focus from main menu to game
    public void show() { scene.setRoot(startScreen); }
    // Used for CSV writing
    public long getScore() {
        return timeElapsed;
    }
}