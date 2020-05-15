package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author jack1
 */
public class ServerMain {
    
      //remoteObject is the stub here.
      public static void main(String[] args) {

        try {
            ServerImpl remoteObject = new ServerImpl();
            Registry registry = LocateRegistry.createRegistry(1099); 
            registry.rebind("chat", remoteObject);
            System.out.println("Server is running");
            try {
                String[] bindings = Naming.list("localhost"); // no URL
                for (String name : bindings) {
                    System.out.println(name);
                }
            } catch (MalformedURLException e) {
                System.err.println("Unable to see names: " + e);
            }
        } catch (RemoteException e) {
            System.err.println("Unable to bind to registry: " + e);
        }
    }
}
