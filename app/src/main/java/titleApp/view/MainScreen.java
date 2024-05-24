package titleApp.view;

import titleApp.App;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainScreen {

  private App app;
  private Scene scene;

  public MainScreen(App app) {
    this.app = app;
    initialize();
  }

  private void initialize() {
    Button userButton = new Button("User");
    userButton.setMinWidth(300);
    userButton.setMinHeight(100);

    Button adminButton = new Button("Admin");

    adminButton.setMinWidth(300);
    adminButton.setMinHeight(100);

    userButton.setOnAction(e -> app.showUserOrderScreen());
    adminButton.setOnAction(e -> app.showAdminScreen());

    VBox layout = new VBox(20);
    layout.getChildren().addAll(userButton, adminButton);

    scene = new Scene(layout, 300, 250);
  }

  public Scene getScene() {
    return scene;
  }
}
