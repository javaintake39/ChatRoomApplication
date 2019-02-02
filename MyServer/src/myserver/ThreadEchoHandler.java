package myserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Abdelrhman
 */
public class ThreadEchoHandler extends Thread {

    private Socket incomingSocket;
    private List<ThreadEchoHandler> clientsList;
    private DataInputStream inStream;
    private DataOutputStream outStream;
    int clientNumber;
    

    public ThreadEchoHandler(Socket incomingSocket, List<ThreadEchoHandler> clientsList,int clientNumber) {
        this.incomingSocket = incomingSocket;
        this.clientsList = clientsList;
        this.clientNumber=clientNumber;
        try {
            inStream = new DataInputStream(incomingSocket.getInputStream());
            outStream = new DataOutputStream(incomingSocket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            String message = "";
            try {
                message = inStream.readUTF();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for (ThreadEchoHandler client : clientsList) {
                try {
                    client.outStream.writeUTF("Client ("+clientNumber+") Sent : "+message);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
