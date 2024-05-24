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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminScreen {

  private App app;
  private Scene scene;

  private FoodController foodDAO = new FoodController();
  private OrderController orderDAO = new OrderController();
  private ObservableList<Food> foodList = FXCollections.observableArrayList();
  private ObservableList<Order> orderList = FXCollections.observableArrayList();
  private TableView<Food> foodTable;
  private TableView<Order> orderTable;

  public AdminScreen(App app) {
    this.app = app;
    loadFoods();
    loadOrders();
    initialize();
  }

  private void initialize() {
    Label foodNameLabel = new Label("Food Name:");
    TextField foodNameField = new TextField();

    Label foodPriceLabel = new Label("Food Price:");
    TextField foodPriceField = new TextField();

    Button addFoodButton = new Button("Add Food");
    addFoodButton.setOnAction(e -> {
      String name = foodNameField.getText();
      double price = Double.parseDouble(foodPriceField.getText());
      Food food = new Food(name, price);
      foodDAO.addFood(food);
      loadFoods();
    });

    Button updateFoodButton = new Button("Update Food");
    updateFoodButton.setOnAction(e -> {
      Food selectedFood = foodTable.getSelectionModel().getSelectedItem();
      if (selectedFood != null) {
        selectedFood.setName(foodNameField.getText());
        selectedFood.setPrice(Double.parseDouble(foodPriceField.getText()));
        foodDAO.updateFood(selectedFood);
        loadFoods();
      }
    });

    Button deleteFoodButton = new Button("Delete Food");
    deleteFoodButton.setOnAction(e -> {
      Food selectedFood = foodTable.getSelectionModel().getSelectedItem();
      if (selectedFood != null) {
        foodDAO.deleteFood(selectedFood.getId());
        loadFoods();
      }
    });

    Button backButton = new Button("Back");
    backButton.setOnAction(e -> app.showMainScreen());

    // TableView for Foods
    foodTable = new TableView<>();
    TableColumn<Food, Integer> idColumn = new TableColumn<>("ID");
    idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

    TableColumn<Food, String> nameColumn = new TableColumn<>("Name");
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

    TableColumn<Food, Double> priceColumn = new TableColumn<>("Price");
    priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

    foodTable.getColumns().addAll(idColumn, nameColumn, priceColumn);
    foodTable.setItems(foodList);

    // TableView for Orders
    orderTable = new TableView<>();
    TableColumn<Order, Integer> orderIdColumn = new TableColumn<>("Order ID");
    orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

    TableColumn<Order, String> customerNameColumn = new TableColumn<>("Customer Name");
    customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());

    TableColumn<Order, String> foodNameColumn = new TableColumn<>("Food");
    foodNameColumn.setCellValueFactory(cellData -> cellData.getValue().foodNameProperty());

    TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Quantity");
    quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

    orderTable.getColumns().addAll(orderIdColumn, customerNameColumn, foodNameColumn, quantityColumn);
    orderTable.setItems(orderList);

    HBox formLayout = new HBox(10);
    formLayout.getChildren().addAll(foodNameLabel, foodNameField, foodPriceLabel, foodPriceField, addFoodButton,
        updateFoodButton, deleteFoodButton);

    VBox layout = new VBox(10);
    layout.getChildren().addAll(formLayout, foodTable, orderTable, backButton);

    scene = new Scene(layout, 800, 600);
  }

  private void loadFoods() {
    foodList.setAll(foodDAO.getAllFoods());
  }

  private void loadOrders() {
    orderList.setAll(orderDAO.getAllOrders());
  }

  public Scene getScene() {
    return scene;
  }
}
