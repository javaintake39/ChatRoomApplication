
package myserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdelrhman
 */
public class MyServer {

   public static void main(String[] args) {
        try {
            
            ServerSocket server = new ServerSocket(2222);
            List<ThreadEchoHandler> clientsList = new ArrayList();
            int clientNumber=0;
            while(true)
            {
                Socket clientSocket = server.accept();
                ThreadEchoHandler clientThread = new ThreadEchoHandler(clientSocket, clientsList,clientNumber++);
                clientsList.add(clientThread);
                clientThread.start();
            }
            
        } catch (IOException ex) {
           ex.printStackTrace();
            
        }
        
    }
    
}
