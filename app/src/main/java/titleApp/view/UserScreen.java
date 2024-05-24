package titleApp.view;

import titleApp.App;
import titleApp.controller.FoodController;
import titleApp.controller.OrderController;
import titleApp.model.Food;
import titleApp.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UserScreen {

  private App app;
  private Scene scene;

  private FoodController foodDAO = new FoodController();
  private OrderController orderDAO = new OrderController();
  private ObservableList<Food> foodList = FXCollections.observableArrayList();

  public UserScreen(App app) {
    this.app = app;
    initialize();
    loadFoods();
  }

  private void initialize() {
    Label customerNameLabel = new Label("Customer Name:");
    TextField customerNameField = new TextField();

    Label foodLabel = new Label("Food:");
    ComboBox<Food> foodComboBox = new ComboBox<>(foodList);
    foodComboBox.setCellFactory(param -> new ListCell<Food>() {
      @Override
      protected void updateItem(Food item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          setText(item.getName() + " - Rp. " + item.getPrice());
        }
      }
    });
    foodComboBox.setButtonCell(new ListCell<Food>() {
      @Override
      protected void updateItem(Food item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          setText(item.getName() + " - Rp. " + item.getPrice());
        }
      }
    });

    Label quantityLabel = new Label("Quantity:");
    TextField quantityField = new TextField();

    Button orderButton = new Button("Place Order");
    orderButton.setOnAction(e -> {
      String customerName = customerNameField.getText();
      Food selectedFood = foodComboBox.getSelectionModel().getSelectedItem();
      int quantity = Integer.parseInt(quantityField.getText());

      if (selectedFood != null) {
        Order order = new Order(selectedFood.getId(), customerName, quantity);
        orderDAO.addOrder(order);

        customerNameField.clear();
        foodComboBox.getSelectionModel().clearSelection();
        quantityField.clear();
      }
    });

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> app.showMainScreen());

    GridPane gridPane = new GridPane();
    gridPane.setVgap(10);
    gridPane.setHgap(10);

    gridPane.add(customerNameLabel, 0, 0);
    gridPane.add(customerNameField, 1, 0);
    gridPane.add(foodLabel, 0, 1);
    gridPane.add(foodComboBox, 1, 1);
    gridPane.add(quantityLabel, 0, 2);
    gridPane.add(quantityField, 1, 2);
    gridPane.add(orderButton, 1, 3);

    VBox layout = new VBox(10);
    layout.getChildren().addAll(gridPane, backButton);

    scene = new Scene(layout, 400, 300);
  }

  private void loadFoods() {
    foodList.setAll(foodDAO.getAllFoods());
  }

  public Scene getScene() {
    return scene;
  }
}
