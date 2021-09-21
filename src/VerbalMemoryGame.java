import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VerbalMemoryGame {
    private final Scene scene;
    private final BorderPane root;

    public VerbalMemoryGame(Scene scene, int SIZE, TilePane mainMenu) {
        this.scene = scene;
        root = new BorderPane();

        VBox vBox = new VBox(10);

        Label title = new Label("Verbal Memory");
        title.setFont(new Font(40));
        title.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("The test measures how many words" +
                " you can keep in short term memory at once.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });

        vBox.getChildren().addAll(title, gameDesc, backButton);
        vBox.setAlignment(Pos.CENTER);

        root.setCenter(vBox);
    }
    public void show() { scene.setRoot(root); }
}