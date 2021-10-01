import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TypingGame {
    private final Scene scene;
    private final BorderPane startScreen;
    private final BorderPane gameScreen;
    private String paragraph = "The quick brown fox jumped over the lazy dog.";
    private Text paragraphBox;
    private int counter = 0;
    private long elapsedTime = 0;

    /**
     Input: Scene variable to transition to,
     SIZE variable for window/shape creation,
     Pane variable for main menu to go back to
     */
    public TypingGame(Scene scene, int SIZE, BorderPane mainMenu) {
        this.scene = scene;
        startScreen = new BorderPane();
        gameScreen = new BorderPane();
        paragraphBox = new Text();

        VBox startVBox = new VBox(10);
        Label title = new Label("Typing");
        title.setFont(new Font(40));
        title.setAlignment(Pos.CENTER);
        Text gameDesc = new Text("Click the boxes according to their numbers. " +
                "Click Start to begin.");
        gameDesc.setFont(new Font(20));
        gameDesc.setWrappingWidth(225);
        Button backButton = new Button("Back to Main Menu");
        Button startButton = new Button("Start");
        startVBox.getChildren().addAll(title, gameDesc, startButton, backButton);
        startVBox.setAlignment(Pos.CENTER);
        startScreen.setCenter(startVBox);

        Button retryButton = new Button("Retry");
        VBox gameVBox = new VBox(20);
        paragraphBox.setText(paragraph);
        paragraphBox.setFont(new Font(30));
        TextField userInputField = new TextField();
        userInputField.setMaxWidth(300);
        gameVBox.getChildren().addAll(paragraphBox, userInputField,
                retryButton, backButton);
        gameVBox.setAlignment(Pos.CENTER);
        gameScreen.setCenter(gameVBox);
        userInputField.setOnKeyReleased(event -> {
            if(!paragraph.isEmpty()) {
                gameLoop(userInputField.getText().charAt(0));
                userInputField.setText(" ");
            }
            else paragraphBox.setText("Your time: ");
        });
        startButton.setOnAction(event -> {
            scene.setRoot(gameScreen);
            elapsedTime = System.currentTimeMillis();
        });
        backButton.setOnAction(event -> {
            scene.setRoot(mainMenu);
        });
        retryButton.setOnAction(event -> {
            paragraph = "The quick brown fox jumped over the lazy dog.";
            paragraphBox.setText(paragraph);
            userInputField.clear();
        });
    }
    /*
    Compares user input with first char of paragraph string.
    If input is correct, removes first element of paragraph
    string and updates respective labels. If not, then text is
    turned red to show error.
     */
    public void gameLoop(char userInputChar) {
        char currChar = paragraph.charAt(0);
        if(currChar == userInputChar) {
            paragraph = paragraph.substring(1);
            if(paragraph.equals("")) {
                elapsedTime = System.currentTimeMillis() - elapsedTime;
                paragraphBox.setText("Your time was: " + elapsedTime + " ms");
            }
            else {
                paragraphBox.setFill(Color.BLACK);
                paragraphBox.setText(paragraph);
            }
            paragraphBox.setFill(Color.BLACK);
        }
        else {
            paragraphBox.setFill(Color.DARKRED);
            paragraphBox.setText(paragraph);
        }
    }
    // Transfers focus from main menu to game
    public void show() { scene.setRoot(startScreen); }
    // Used for CSV writing
    public long getScore() { return elapsedTime; }
}