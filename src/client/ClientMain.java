package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author jack1
 */
public class ClientMain {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.getRegistry();
            //looking for the remote reference
            commonInterface chat = (commonInterface) registry.lookup("chat"); 
            //pass this reference to Login class. Connection built based on the same interface.
            new Login(chat);  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
