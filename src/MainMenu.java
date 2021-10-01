import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.io.FileWriter;
import java.io.IOException;

public class MainMenu {
    private final Scene scene;
    private final BorderPane mainScreen;
    private final TilePane root;

    //Instantiate game objects for later use
    private ReactionTimeGame reactionTimeGame;
    private SequenceMemoryGame sequenceMemoryGame;
    private AimTrainerGame aimTrainerGame;
    private NumberMemoryGame numberMemoryGame;
    private VerbalMemoryGame verbalMemoryGame;
    private ChimpTestGame chimpTestGame;
    private VisualMemoryGame visualMemoryGame;
    private TypingGame typingGame;
    private MathSolverGame mathSolverGame;
    private TextField nameField;

    public MainMenu(Scene scene, int SIZE) {
        this.scene = scene;
        root = new TilePane();
        mainScreen = new BorderPane();

        //All necessary labels, buttons, and shapes for Reaction Time Game
        Rectangle reactionTimeRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        reactionTimeRect.setFill(Color.LIGHTBLUE);
        StackPane reactionTimePane = new StackPane();
        Button reactionTime = new Button("Reaction Time");
        reactionTime.setWrapText(true);
        reactionTime.setTextAlignment(TextAlignment.CENTER);
        reactionTime.setPrefSize(SIZE / 7, SIZE / 7);
        reactionTime.setOnAction(event -> {
            reactionTimeGame = new ReactionTimeGame(scene, SIZE, mainScreen);
            reactionTimeGame.show();
        });
        Text reactionTimeDesc = new Text("Test your visual and " +
                "physical reflexes.");
        reactionTimeDesc.setWrappingWidth(SIZE / 5);
        VBox reactionTimeVbox = new VBox(10);
        reactionTimeVbox.getChildren().addAll(reactionTime, reactionTimeDesc);
        reactionTimeVbox.setAlignment(Pos.CENTER);
        reactionTimePane.getChildren().addAll(reactionTimeRect, reactionTimeVbox);
        root.getChildren().add(reactionTimePane);

        //All necessary labels, buttons, and shapes for Sequence Memory Game
        Rectangle sequenceMemoryRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        sequenceMemoryRect.setFill(Color.LIGHTBLUE);
        StackPane sequenceMemoryPane = new StackPane();
        Button sequenceMemory = new Button("Sequence Memory");
        sequenceMemory.setWrapText(true);
        sequenceMemory.setTextAlignment(TextAlignment.CENTER);
        sequenceMemory.setPrefSize(SIZE / 7, SIZE / 7);
        sequenceMemory.setOnAction(event -> {
            sequenceMemoryGame = new SequenceMemoryGame(scene, SIZE, mainScreen);
            sequenceMemoryGame.show();
        });
        Text sequenceMemoryDesc = new Text("Remember an increasing " +
                "pattern of button presses.");
        sequenceMemoryDesc.setWrappingWidth(SIZE / 5);
        VBox sequenceMemoryVbox = new VBox(10);
        sequenceMemoryVbox.getChildren().addAll(sequenceMemory, sequenceMemoryDesc);
        sequenceMemoryVbox.setAlignment(Pos.CENTER);
        sequenceMemoryPane.getChildren().addAll(sequenceMemoryRect, sequenceMemoryVbox);
        root.getChildren().add(sequenceMemoryPane);

        //All necessary labels, buttons, and shapes for Aim Trainer Game
        Rectangle aimTrainerRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        aimTrainerRect.setFill(Color.LIGHTBLUE);
        StackPane aimTrainerPane = new StackPane();
        Button aimTrainer = new Button("Aim Trainer");
        aimTrainer.setWrapText(true);
        aimTrainer.setTextAlignment(TextAlignment.CENTER);
        aimTrainer.setPrefSize(SIZE / 7, SIZE / 7);
        aimTrainer.setOnAction(event -> {
            aimTrainerGame = new AimTrainerGame(scene, SIZE, mainScreen);
            aimTrainerGame.show();
        });
        Text aimTrainerDesc = new Text("How quickly can you hit all the targets?");
        aimTrainerDesc.setWrappingWidth(SIZE / 5);
        VBox aimTrainerVbox = new VBox(10);
        aimTrainerVbox.getChildren().addAll(aimTrainer, aimTrainerDesc);
        aimTrainerVbox.setAlignment(Pos.CENTER);
        aimTrainerPane.getChildren().addAll(aimTrainerRect, aimTrainerVbox);
        root.getChildren().add(aimTrainerPane);

        //All necessary labels, buttons, and shapes for Number Memory Game
        Rectangle numberMemoryRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        numberMemoryRect.setFill(Color.LIGHTBLUE);
        StackPane numberMemoryPane = new StackPane();
        Button numberMemory = new Button("Number Memory");
        numberMemory.setWrapText(true);
        numberMemory.setTextAlignment(TextAlignment.CENTER);
        numberMemory.setPrefSize(SIZE / 7, SIZE / 7);
        numberMemory.setOnAction(event -> {
            numberMemoryGame = new NumberMemoryGame(scene, mainScreen);
            numberMemoryGame.show();
        });
        Text numberMemoryDesc = new Text("Remember the longest number" +
                " you can.");
        numberMemoryDesc.setWrappingWidth(SIZE / 5);
        VBox numberMemoryVbox = new VBox(10);
        numberMemoryVbox.getChildren().addAll(numberMemory, numberMemoryDesc);
        numberMemoryVbox.setAlignment(Pos.CENTER);
        numberMemoryPane.getChildren().addAll(numberMemoryRect, numberMemoryVbox);
        root.getChildren().add(numberMemoryPane);

        //All necessary labels, buttons, and shapes for Verbal Memory Game
        Rectangle verbalMemoryRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        verbalMemoryRect.setFill(Color.LIGHTBLUE);
        StackPane verbalMemoryPane = new StackPane();
        Button verbalMemory = new Button("Verbal Memory");
        verbalMemory.setWrapText(true);
        verbalMemory.setTextAlignment(TextAlignment.CENTER);
        verbalMemory.setPrefSize(SIZE / 7, SIZE / 7);
        verbalMemory.setOnAction(event -> {
            verbalMemoryGame = new VerbalMemoryGame(scene, mainScreen);
            verbalMemoryGame.show();
        });
        Text verbalMemoryDesc = new Text("Keep as many words in memory " +
                "as possible.");
        verbalMemoryDesc.setWrappingWidth(SIZE / 5);
        VBox verbalMemoryVbox = new VBox(10);
        verbalMemoryVbox.getChildren().addAll(verbalMemory, verbalMemoryDesc);
        verbalMemoryVbox.setAlignment(Pos.CENTER);
        verbalMemoryPane.getChildren().addAll(verbalMemoryRect, verbalMemoryVbox);
        root.getChildren().add(verbalMemoryPane);

        //All necessary labels, buttons, and shapes for Chimp Test Game
        Rectangle chimpTestRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        chimpTestRect.setFill(Color.LIGHTBLUE);
        StackPane chimpTestPane = new StackPane();
        Button chimpTest = new Button("Chimp Test");
        chimpTest.setWrapText(true);
        chimpTest.setTextAlignment(TextAlignment.CENTER);
        chimpTest.setPrefSize(SIZE / 7, SIZE / 7);
        chimpTest.setOnAction(event -> {
            chimpTestGame = new ChimpTestGame(scene, SIZE, mainScreen);
            chimpTestGame.show();
        });
        Text chimpTestDesc = new Text("Are you smarter than a chimpanzee?");
        chimpTestDesc.setWrappingWidth(SIZE / 5);
        VBox chimpTestVbox = new VBox(10);
        chimpTestVbox.getChildren().addAll(chimpTest, chimpTestDesc);
        chimpTestVbox.setAlignment(Pos.CENTER);
        chimpTestPane.getChildren().addAll(chimpTestRect, chimpTestVbox);
        root.getChildren().add(chimpTestPane);

        //All necessary labels, buttons, and shapes for Visual Memory Game
        Rectangle visualMemoryRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        visualMemoryRect.setFill(Color.LIGHTBLUE);
        StackPane visualMemoryPane = new StackPane();
        Button visualMemory = new Button("Visual Memory");
        visualMemory.setWrapText(true);
        visualMemory.setTextAlignment(TextAlignment.CENTER);
        visualMemory.setPrefSize(SIZE / 7, SIZE / 7);
        visualMemory.setOnAction(event -> {
            visualMemoryGame = new VisualMemoryGame(scene, SIZE, mainScreen);
            visualMemoryGame.show();
        });
        Text visualMemoryDesc = new Text("Remember an increasingly large" +
                " board of squares.");
        visualMemoryDesc.setWrappingWidth(SIZE / 5);
        VBox visualMemoryVbox = new VBox(10);
        visualMemoryVbox.getChildren().addAll(visualMemory, visualMemoryDesc);
        visualMemoryVbox.setAlignment(Pos.CENTER);
        visualMemoryPane.getChildren().addAll(visualMemoryRect, visualMemoryVbox);
        root.getChildren().add(visualMemoryPane);

        //All necessary labels, buttons, and shapes for Typing Game
        Rectangle typingRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        typingRect.setFill(Color.LIGHTBLUE);
        StackPane typingPane = new StackPane();
        Button typing = new Button("Typing");
        typing.setWrapText(true);
        typing.setTextAlignment(TextAlignment.CENTER);
        typing.setPrefSize(SIZE / 7, SIZE / 7);
        typing.setOnAction(event -> {
            typingGame = new TypingGame(scene, SIZE, mainScreen);
            typingGame.show();
        });
        Text typingDesc = new Text("How many words per minute can you type?");
        typingDesc.setWrappingWidth(SIZE / 5);
        VBox typingVbox = new VBox(10);
        typingVbox.getChildren().addAll(typing, typingDesc);
        typingVbox.setAlignment(Pos.CENTER);
        typingPane.getChildren().addAll(typingRect, typingVbox);
        root.getChildren().add(typingPane);

        //All necessary labels, buttons, and shapes for Math Solver Game
        Rectangle mathSolverRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        mathSolverRect.setFill(Color.LIGHTBLUE);
        StackPane mathSolverPane = new StackPane();
        Button mathSolver = new Button("Math Solver");
        mathSolver.setWrapText(true);
        mathSolver.setTextAlignment(TextAlignment.CENTER);
        mathSolver.setPrefSize(SIZE / 7, SIZE / 7);
        mathSolver.setOnAction(event -> {
            mathSolverGame = new MathSolverGame(scene, SIZE, mainScreen);
            mathSolverGame.show();
        });
        Text mathSolverDesc = new Text("With increasing numbers how many " +
                "equations can you solve?");
        mathSolverDesc.setWrappingWidth(SIZE / 5);
        VBox mathSolverVbox = new VBox(10);
        mathSolverVbox.getChildren().addAll(mathSolver, mathSolverDesc);
        mathSolverVbox.setAlignment(Pos.CENTER);
        mathSolverPane.getChildren().addAll(mathSolverRect, mathSolverVbox);
        root.getChildren().add(mathSolverPane);

        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

        //Labels and fields for CSV writing functionality
        HBox topHBox = new HBox(10);
        Label nameLabel = new Label("Name: ");
        nameField = new TextField();
        nameField.setMaxWidth(200);
        Button saveScoreBtn = new Button("Save Scores");
        saveScoreBtn.setOnAction(event -> {
            try {
                CSVWriter csvw = new CSVWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        topHBox.getChildren().addAll(nameLabel, nameField, saveScoreBtn);
        topHBox.setAlignment(Pos.CENTER);
        mainScreen.setTop(topHBox);
        mainScreen.setCenter(root);

    }
    public void show() { scene.setRoot(mainScreen); }
    public class CSVWriter {
        public CSVWriter() throws IOException {
            FileWriter csvWriter = new FileWriter("scores.csv");
            csvWriter.append("Name, ");
            csvWriter.append("Reaction Time (ms), ");
            csvWriter.append("Sequence Memory, ");
            csvWriter.append("Aim Trainer (ms), ");
            csvWriter.append("Number Memory, ");
            csvWriter.append("Verbal Memory, ");
            csvWriter.append("Chimp Test, ");
            csvWriter.append("Visual Memory, ");
            csvWriter.append("Typing (ms), ");
            csvWriter.append("Math Solver");
            csvWriter.append("\n");

            if (!nameField.getText().isEmpty()) {
                csvWriter.append(nameField.getText());
                csvWriter.append(", ");
            }
            else {
                csvWriter.append("No Name");
                csvWriter.append(", ");
            }
            if (reactionTimeGame != null) {
                csvWriter.append(Long.toString(reactionTimeGame.getScore()));
                csvWriter.append(", ");
            }
            else {
                csvWriter.append(Integer.toString(0));
                csvWriter.append(", ");
            }
            if (sequenceMemoryGame != null) {
                csvWriter.append(Integer.toString(sequenceMemoryGame.getScore()));
                csvWriter.append(", ");
            }
            else {
                csvWriter.append(Integer.toString(0));
                csvWriter.append(", ");
            }
            if (aimTrainerGame != null) {
                csvWriter.append(Long.toString(aimTrainerGame.getScore()));
                csvWriter.append(", ");
            }
            else {
                csvWriter.append(Integer.toString(0));
                csvWriter.append(", ");
            }
            if (numberMemoryGame != null) {
                csvWriter.append(Integer.toString(numberMemoryGame.getScore()));
                csvWriter.append(", ");
            }
            else {
                csvWriter.append(Integer.toString(0));
                csvWriter.append(", ");
            }
            if (verbalMemoryGame != null) {
                csvWriter.append(Integer.toString(verbalMemoryGame.getScore()));
                csvWriter.append(", ");
            }
            else {
                csvWriter.append(Integer.toString(0));
                csvWriter.append(", ");
            }
            if (chimpTestGame != null) {
                csvWriter.append(Integer.toString(chimpTestGame.getScore()));
                csvWriter.append(", ");
            }
            else {
                csvWriter.append(Integer.toString(0));
                csvWriter.append(", ");
            }
            if (visualMemoryGame != null) {
                csvWriter.append(Integer.toString(visualMemoryGame.getScore()));
                csvWriter.append(", ");
            }
            else {
                csvWriter.append(Integer.toString(0));
                csvWriter.append(", ");
            }
            if (typingGame != null) {
                csvWriter.append(Long.toString(typingGame.getScore()));
                csvWriter.append(", ");
            }
            else {
                csvWriter.append(Integer.toString(0));
                csvWriter.append(", ");
            }
            if (mathSolverGame != null) {
                csvWriter.append(Long.toString(mathSolverGame.getScore()));
            }
            else {
                csvWriter.append(Integer.toString(0));
            }
            csvWriter.flush();
            csvWriter.close();
        }
    }
}
