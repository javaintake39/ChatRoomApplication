package myclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 *
 * @author Abdelrhman
 */
public class MyClient extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Socket clientSocket = new Socket("localhost",2222);   // Define Socket
            
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            
            DesignPane root = new DesignPane();
            root.button.setOnAction((event) -> {
                try {
                        dataOutputStream.writeUTF(root.textField.getText());
                        root.textField.clear();
                    } 
                catch (IOException ex) {
                       ex.printStackTrace();
                    }
            });
            root.textField.setOnKeyPressed((event) -> {
                if(event.getCode().equals(KeyCode.ENTER))
                {
                    try {
                        dataOutputStream.writeUTF(root.textField.getText());
                        root.textField.clear();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                
            });
            
            Thread readMessageThread = new Thread(() -> {
                while (true) {
                    try {
                        root.textArea.setText(root.textArea.getText() + dataInputStream.readUTF() + "\n");
                        
                    } catch (IOException ex) 
                    {
                        ex.printStackTrace();
                    }
                }
            });
            
            readMessageThread.start();
            
            Scene scene = new Scene(root, 600, 400);
            
            primaryStage.setTitle("Chat Application");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
