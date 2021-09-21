import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NumberMemoryGame {
    private final Scene scene;
    private final BorderPane root;

    public NumberMemoryGame(Scene scene, int SIZE, TilePane mainMenu) {
        this.scene = scene;
        root = new BorderPane();

        VBox vBox = new VBox(10);

        Label numberMemoryTitle = new Label("Number Memory");
        numberMemoryTitle.setFont(new Font(40));
        numberMemoryTitle.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("You will be shown an increasingly long number. " +
                "Type it correctly to proceed. Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);

        Button backButton = new Button("Back to Main Menu");
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });

        vBox.getChildren().addAll(numberMemoryTitle, gameDesc, backButton);
        vBox.setAlignment(Pos.CENTER);

        root.setCenter(vBox);
    }
    public void show() { scene.setRoot(root); }
}