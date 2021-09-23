import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AimTrainerGame {
    private final Scene scene;
    private final BorderPane root;

    public AimTrainerGame(Scene scene, int SIZE, TilePane mainMenu) {
        this.scene = scene;
        root = new BorderPane();

        VBox vBox = new VBox(10);

        Label aimTrainerTitle = new Label("Aim Trainer");
        aimTrainerTitle.setFont(new Font(40));
        aimTrainerTitle.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("Click the targets as quickly as you can. " +
                "Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });

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

        vBox.getChildren().addAll(aimTrainerTitle, gameDesc,
                targetPane, backButton);
        vBox.setAlignment(Pos.CENTER);

        root.setCenter(vBox);
    }
    public void show() { scene.setRoot(root); }
}