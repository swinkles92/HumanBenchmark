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
import java.util.concurrent.ThreadLocalRandom;


public class ReactionTimeGame {
    private final Scene scene;
    private BorderPane root;
    private long reactionTime;
    private long startTime;

    public ReactionTimeGame(Scene scene, int SIZE, BorderPane mainMenu) {
        this.scene = scene;
        root = new BorderPane();

        VBox vBox = new VBox(10);
        Label reactionTimeTitle = new Label("Reaction Time");
        reactionTimeTitle.setFont(new Font(40));
        reactionTimeTitle.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("When the red box turns green, click it " +
                "as quickly as you can. Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);

        Label reactionTimeLabel = new Label();
        Rectangle rect = new Rectangle(SIZE / 2, SIZE / 2);
        rect.setFill(Color.GREEN);

        Button startButton = new Button("Start / Retry");
        startButton.setOnAction(event -> {
            reactionTime = 0;
            reactionTimeLabel.setText("");
            startTime = gameLoop(rect);
            rect.setOnMouseClicked(event1 -> {
                if(rect.getFill() == Color.GREEN) {
                    if(reactionTime == 0) {
                        reactionTime = System.currentTimeMillis() - startTime;
                        reactionTimeLabel.setText("Your time: " + reactionTime + " ms");
                    }
                }
                else {
                    reactionTimeLabel.setText("Too early!");
                    reactionTime = 0;
                    startTime = gameLoop(rect);
                }
            });
        });

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });
        vBox.getChildren().addAll(reactionTimeTitle, gameDesc, reactionTimeLabel,
                rect, startButton, backButton);
        vBox.setAlignment(Pos.CENTER);
        root.setCenter(vBox);
    }
    public long gameLoop(Rectangle rect) {
        rect.setFill(Color.RED);
        int rand = ThreadLocalRandom.current().nextInt(1, 4);
        Timeline t = new Timeline(
            new KeyFrame(Duration.seconds(0), new KeyValue(rect.fillProperty(), Color.RED)),
            new KeyFrame(Duration.seconds(rand - 0.001), new KeyValue(rect.fillProperty(), Color.RED)),
            new KeyFrame(Duration.seconds(rand), new KeyValue(rect.fillProperty(), Color.GREEN))
        );
        t.play();
        long clickTime = System.currentTimeMillis();
        return clickTime;
    }
    public void show() { scene.setRoot(root); }
    public long getScore() {
        return reactionTime;
    }
}
