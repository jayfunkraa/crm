package com.kartoffelkopf.crm.util;

import com.kartoffelkopf.crm.controller.EditCustomerController;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class WindowFactory {

  public WindowFactory() {
  }

  public Stage getAddWindow(String fxmlPath, double width, double height, String title) {
    Stage stage = new Stage();
    Parent root = null;
    try {
      root = FXMLLoader.load(getClass().getResource(fxmlPath));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Scene scene = new Scene(root, width, height);

    stage.setTitle(title);
    stage.setScene(scene);
    return stage;
  }

  public Stage getEditWindow(String fxmlPath, double width, double height, String title, Object object) {
    Stage stage = new Stage();
    Parent root = null;
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
    try {
      root = fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    fxmlLoader.<EditCustomerController>getController().setObject(object);

    Scene scene = new Scene(root, width, height);

    stage.setTitle(title);
    stage.setScene(scene);
    return stage;
  }

  public boolean getDeleteWindow(Object object, String name) {

    boolean confirm = false;

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Deletion");
    alert.setHeaderText("Deleting " + name);
    alert.setContentText("Are you sure?");

    Optional<ButtonType> result = alert.showAndWait();
    confirm = (result.get() == ButtonType.OK);

    return confirm;
  }
}
