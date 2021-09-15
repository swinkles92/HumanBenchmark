import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HumanBenchmarkMain extends Application {
    private final int SIZE = 750;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Human Benchmark");

        Pane root = new Pane();
        Scene scene = new Scene(root, SIZE, SIZE);
        primaryStage.setScene(scene);
        primaryStage.show();

        MainMenu mainMenu = new MainMenu(scene, SIZE);
        mainMenu.show();
    }
}
