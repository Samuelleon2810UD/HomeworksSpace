module com.binary_tree {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.binary_tree to javafx.fxml;
    exports com.binary_tree;
}
