import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MainMenu {
    private final Scene scene;
    private final TilePane root;
    private final HBox firstRow = new HBox(10);
    private final HBox sndRow = new HBox(10);
    private final HBox thirdRow = new HBox(10);
    private final VBox vBox = new VBox(10);

    public MainMenu(Scene scene, int SIZE) {
        this.scene = scene;
        root = new TilePane();

        Rectangle reactionTimeRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        reactionTimeRect.setFill(Color.LIGHTBLUE);
        StackPane reactionTimePane = new StackPane();
        Button reactionTime = new Button("Reaction Time");
        reactionTime.setWrapText(true);
        reactionTime.setTextAlignment(TextAlignment.CENTER);
        reactionTime.setPrefSize(SIZE / 7, SIZE / 7);
        Text reactionTimeDesc = new Text("Test your visual and " +
                "physical reflexes.");
        reactionTimeDesc.setWrappingWidth(SIZE / 5);
        VBox reactionTimeVbox = new VBox(10);
        reactionTimeVbox.getChildren().addAll(reactionTime, reactionTimeDesc);
        reactionTimeVbox.setAlignment(Pos.CENTER);
        reactionTimePane.getChildren().addAll(reactionTimeRect, reactionTimeVbox);
        root.getChildren().add(reactionTimePane);

        Rectangle sequenceMemoryRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        sequenceMemoryRect.setFill(Color.LIGHTBLUE);
        StackPane sequenceMemoryPane = new StackPane();
        Button sequenceMemory = new Button("Sequence Memory");
        sequenceMemory.setWrapText(true);
        sequenceMemory.setTextAlignment(TextAlignment.CENTER);
        sequenceMemory.setPrefSize(SIZE / 7, SIZE / 7);
        Text sequenceMemoryDesc = new Text("Remember an increasing " +
                "pattern of button presses.");
        sequenceMemoryDesc.setWrappingWidth(SIZE / 5);
        VBox sequenceMemoryVbox = new VBox(10);
        sequenceMemoryVbox.getChildren().addAll(sequenceMemory, sequenceMemoryDesc);
        sequenceMemoryVbox.setAlignment(Pos.CENTER);
        sequenceMemoryPane.getChildren().addAll(sequenceMemoryRect, sequenceMemoryVbox);
        root.getChildren().add(sequenceMemoryPane);

        Rectangle aimTrainerRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        aimTrainerRect.setFill(Color.LIGHTBLUE);
        StackPane aimTrainerPane = new StackPane();
        Button aimTrainer = new Button("Aim Trainer");
        aimTrainer.setWrapText(true);
        aimTrainer.setTextAlignment(TextAlignment.CENTER);
        aimTrainer.setPrefSize(SIZE / 7, SIZE / 7);
        Text aimTrainerDesc = new Text("How quickly can you hit all the targets?");
        aimTrainerDesc.setWrappingWidth(SIZE / 5);
        VBox aimTrainerVbox = new VBox(10);
        aimTrainerVbox.getChildren().addAll(aimTrainer, aimTrainerDesc);
        aimTrainerVbox.setAlignment(Pos.CENTER);
        aimTrainerPane.getChildren().addAll(aimTrainerRect, aimTrainerVbox);
        root.getChildren().add(aimTrainerPane);

        Rectangle numberMemoryRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        numberMemoryRect.setFill(Color.LIGHTBLUE);
        StackPane numberMemoryPane = new StackPane();
        Button numberMemory = new Button("Number Memory");
        numberMemory.setWrapText(true);
        numberMemory.setTextAlignment(TextAlignment.CENTER);
        numberMemory.setPrefSize(SIZE / 7, SIZE / 7);
        Text numberMemoryDesc = new Text("Remember the longest number" +
                " you can.");
        numberMemoryDesc.setWrappingWidth(SIZE / 5);
        VBox numberMemoryVbox = new VBox(10);
        numberMemoryVbox.getChildren().addAll(numberMemory, numberMemoryDesc);
        numberMemoryVbox.setAlignment(Pos.CENTER);
        numberMemoryPane.getChildren().addAll(numberMemoryRect, numberMemoryVbox);
        root.getChildren().add(numberMemoryPane);


        Rectangle verbalMemoryRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        verbalMemoryRect.setFill(Color.LIGHTBLUE);
        StackPane verbalMemoryPane = new StackPane();
        Button verbalMemory = new Button("Verbal Memory");
        verbalMemory.setWrapText(true);
        verbalMemory.setTextAlignment(TextAlignment.CENTER);
        verbalMemory.setPrefSize(SIZE / 7, SIZE / 7);
        Text verbalMemoryDesc = new Text("Keep as many words in memory " +
                "as possible.");
        verbalMemoryDesc.setWrappingWidth(SIZE / 5);
        VBox verbalMemoryVbox = new VBox(10);
        verbalMemoryVbox.getChildren().addAll(verbalMemory, verbalMemoryDesc);
        verbalMemoryVbox.setAlignment(Pos.CENTER);
        verbalMemoryPane.getChildren().addAll(verbalMemoryRect, verbalMemoryVbox);
        root.getChildren().add(verbalMemoryPane);

        Rectangle chimpTestRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        chimpTestRect.setFill(Color.LIGHTBLUE);
        StackPane chimpTestPane = new StackPane();
        Button chimpTest = new Button("Chimp Test");
        chimpTest.setWrapText(true);
        chimpTest.setTextAlignment(TextAlignment.CENTER);
        chimpTest.setPrefSize(SIZE / 7, SIZE / 7);
        Text chimpTestDesc = new Text("Are you smarter than a chimpanzee?");
        chimpTestDesc.setWrappingWidth(SIZE / 5);
        VBox chimpTestVbox = new VBox(10);
        chimpTestVbox.getChildren().addAll(chimpTest, chimpTestDesc);
        chimpTestVbox.setAlignment(Pos.CENTER);
        chimpTestPane.getChildren().addAll(chimpTestRect, chimpTestVbox);
        root.getChildren().add(chimpTestPane);

        Rectangle visualMemoryRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        visualMemoryRect.setFill(Color.LIGHTBLUE);
        StackPane visualMemoryPane = new StackPane();
        Button visualMemory = new Button("Visual Memory");
        visualMemory.setWrapText(true);
        visualMemory.setTextAlignment(TextAlignment.CENTER);
        visualMemory.setPrefSize(SIZE / 7, SIZE / 7);
        Text visualMemoryDesc = new Text("Remember an increasingly large" +
                " board of squares.");
        visualMemoryDesc.setWrappingWidth(SIZE / 5);
        VBox visualMemoryVbox = new VBox(10);
        visualMemoryVbox.getChildren().addAll(visualMemory, visualMemoryDesc);
        visualMemoryVbox.setAlignment(Pos.CENTER);
        visualMemoryPane.getChildren().addAll(visualMemoryRect, visualMemoryVbox);
        root.getChildren().add(visualMemoryPane);

        Rectangle typingRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        typingRect.setFill(Color.LIGHTBLUE);
        StackPane typingPane = new StackPane();
        Button typing = new Button("Typing");
        typing.setWrapText(true);
        typing.setTextAlignment(TextAlignment.CENTER);
        typing.setPrefSize(SIZE / 7, SIZE / 7);
        Text typingDesc = new Text("How many words per minute can you type?");
        typingDesc.setWrappingWidth(SIZE / 5);
        VBox typingVbox = new VBox(10);
        typingVbox.getChildren().addAll(typing, typingDesc);
        typingVbox.setAlignment(Pos.CENTER);
        typingPane.getChildren().addAll(typingRect, typingVbox);
        root.getChildren().add(typingPane);

        Rectangle wowTriviaRect = new Rectangle(SIZE / 3.5, SIZE / 3.5);
        wowTriviaRect.setFill(Color.LIGHTBLUE);
        StackPane wowTriviaPane = new StackPane();
        Button wowTrivia = new Button("World of Warcraft Trivia");
        wowTrivia.setWrapText(true);
        wowTrivia.setTextAlignment(TextAlignment.CENTER);
        wowTrivia.setPrefSize(SIZE / 7, SIZE / 7);
        Text wowTriviaDesc = new Text("Test your World of Warcraft knowledge!");
        wowTriviaDesc.setWrappingWidth(SIZE / 5);
        VBox wowTriviaVbox = new VBox(10);
        wowTriviaVbox.getChildren().addAll(wowTrivia, wowTriviaDesc);
        wowTriviaVbox.setAlignment(Pos.CENTER);
        wowTriviaPane.getChildren().addAll(wowTriviaRect, wowTriviaVbox);
        root.getChildren().add(wowTriviaPane);

        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

    }
    public void show() { scene.setRoot(root); }
}
