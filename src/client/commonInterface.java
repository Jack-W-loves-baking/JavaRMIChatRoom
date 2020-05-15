package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author jack1
 * This interface will be implemented from both client and server sides.
 */
public interface commonInterface extends Remote {

    //Client sends message to the server
    public void sendMessage(MessageInfo message) throws RemoteException;

    public List<MessageInfo> getCurrentMessages() throws RemoteException;
    
    public List<MessageInfo> getAllMessages() throws RemoteException;
    
    public List<MessageInfo> getPrivateMessages() throws RemoteException;
    
    public void sendPrivateMessage(String from, String to,commonInterface chat,MessageInfo message) throws RemoteException;

    public List<String> getAllUsers() throws RemoteException;

    public void login(String username) throws RemoteException;

    public void logout(String username) throws RemoteException;
    
    public void sendCurrentUserName(String username) throws RemoteException;
    
    
}
