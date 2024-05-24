package titleApp;

import titleApp.view.MainScreen;
import titleApp.view.UserScreen;
import titleApp.view.AdminScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Order Application");

        showMainScreen();
    }

    public void showMainScreen() {
        MainScreen mainScreen = new MainScreen(this);
        primaryStage.setScene(mainScreen.getScene());
        primaryStage.show();
    }

    public void showUserOrderScreen() {
        UserScreen userScreen = new UserScreen(this);
        primaryStage.setScene(userScreen.getScene());
        primaryStage.show();
    }

    public void showAdminScreen() {
        AdminScreen adminScreen = new AdminScreen(this);
        primaryStage.setScene(adminScreen.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
