package server;

import client.MessageInfo;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import client.commonInterface;


/**
 *
 * @author jack1
 */
public class ServerImpl extends UnicastRemoteObject implements commonInterface, Serializable {
    
    List<MessageInfo> messages = new ArrayList<>();
    List<MessageInfo> totalMessages = new ArrayList<>();
    List<MessageInfo> privateMessage = new ArrayList<>();
    List<String> users = new ArrayList<>();
    private String username="";
    private String oppositeName="";
    private commonInterface chat;
  

    public ServerImpl() throws RemoteException {
        super();
    }

    @Override
    public void sendMessage(MessageInfo message) throws RemoteException {
        
        //add messages from all the users' inputs.
        messages.add(message);
       
        totalMessages.add(message);
        //
        if (messages.size()>5){
            messages.remove(0);
        }
    }

    @Override
    public List<MessageInfo> getCurrentMessages() throws RemoteException {
        return messages;
    }

    @Override
    public List<String> getAllUsers() throws RemoteException {

        return users;
    }
    
    /**
     * 
     * @param username
     * @throws RemoteException 
     */
    @Override
    public void login(String username) throws RemoteException {
            
            users.add(username);
            MessageInfo message = new MessageInfo();
            message.setUserName(username);
            message.setDate(new Date());
            message.setMessage("joined the chat room");
            message.setStatus("on");           
            messages.add(message);
        
    }

    @Override
    public void logout(String username) throws RemoteException {
        
        users.remove(username);
        MessageInfo message = new MessageInfo();
        message.setUserName(username);
        message.setDate(new Date());
        message.setMessage("left the chat room");
        message.setStatus("off");       
        messages.add(message);         
    }

    @Override
    public List<MessageInfo> getAllMessages() throws RemoteException {
       
        return totalMessages;
    }

 

    @Override
    public void sendPrivateMessage(String from, String to,commonInterface chat,MessageInfo message) throws RemoteException {
    
    }

    @Override
    public List<MessageInfo> getPrivateMessages() throws RemoteException {
        return privateMessage;
    }

    @Override
    public void sendCurrentUserName(String username) throws RemoteException {
        
        this.username = username;
    }

}
