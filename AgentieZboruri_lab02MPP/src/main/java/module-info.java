module com.example.agentiezboruri_lab02mpp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.logging.log4j;

    opens com.example.agentiezboruri_lab02mpp to javafx.fxml;
    exports com.example.agentiezboruri_lab02mpp;
    exports com.example.agentiezboruri_lab02mpp.controller;
    opens com.example.agentiezboruri_lab02mpp.controller to javafx.fxml;
    exports com.example.agentiezboruri_lab02mpp.domain;
}