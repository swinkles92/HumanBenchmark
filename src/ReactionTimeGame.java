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

    public ReactionTimeGame(Scene scene, int SIZE, TilePane mainMenu) {
        this.scene = scene;
        root = new BorderPane();

        VBox vBox = new VBox(10);
        Label reactionTimeTitle = new Label("Reaction Time");
        reactionTimeTitle.setFont(new Font(40));
        reactionTimeTitle.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("When the red box turns green, click it " +
                "as quickly as you can. Clicking the box will begin the game.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);

        Label reactionSpeed = new Label();
        Rectangle rect = new Rectangle(SIZE / 2, SIZE / 2);
        rect.setFill(Color.GREEN);
        rect.setOnMouseClicked(event -> {gameLoop(rect, reactionSpeed);});

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });
        vBox.getChildren().addAll(reactionTimeTitle, gameDesc, rect, backButton);
        vBox.setAlignment(Pos.CENTER);
        root.setCenter(vBox);
    }
    public void gameLoop(Rectangle rect, Label reactionSpeed) {
        rect.setFill(Color.RED);
        int rand = ThreadLocalRandom.current().nextInt(1, 4);
        Timeline t = new Timeline(
            new KeyFrame(Duration.seconds(0), new KeyValue(rect.fillProperty(), Color.RED)),
            new KeyFrame(Duration.seconds(rand), new KeyValue(rect.fillProperty(), Color.GREEN))
        );
        t.play();
    }
    public void show() { scene.setRoot(root); }
}
