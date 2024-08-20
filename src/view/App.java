package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("hello");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("product.fxml")));
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
