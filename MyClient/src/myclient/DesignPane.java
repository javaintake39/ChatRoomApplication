package myclient;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public  class DesignPane extends AnchorPane {

    protected final Pane pane;
    protected final TextArea textArea;
    protected final TextField textField;
    protected final Button button;

    public DesignPane() {

        pane = new Pane();
        textArea = new TextArea();
        textField = new TextField();
        button = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        pane.setPrefHeight(400.0);
        pane.setPrefWidth(600.0);

        textArea.setPrefHeight(357.0);
        textArea.setPrefWidth(600.0);

        textField.setLayoutX(6.0);
        textField.setLayoutY(357.0);
        textField.setPrefHeight(43.0);
        textField.setPrefWidth(451.0);

        button.setLayoutX(470.0);
        button.setLayoutY(357.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(43.0);
        button.setPrefWidth(116.0);
        button.setText("Send");

        pane.getChildren().add(textArea);
        pane.getChildren().add(textField);
        pane.getChildren().add(button);
        getChildren().add(pane);

    }
}
