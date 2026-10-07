module com.dinamic_list {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.dinamic_list to javafx.fxml;
    exports com.dinamic_list;
}
