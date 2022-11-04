import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Chess extends Application{

    public void start(Stage stage) throws Exception{
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("Chess.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
        System.out.println();
    }
}



