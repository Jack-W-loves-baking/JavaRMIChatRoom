package client;

import java.util.List;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;


/**
 *
 * @author jack1
 */
public class ClientImplGui extends javax.swing.JFrame {

    /**
     * Creates new form ClientImplGui
     */
    private String username; //current user's name
    private commonInterface chat; //Reference
    List<MessageInfo> mainMessages = new ArrayList<>();//List of information which we will put in JTextPane
    List<MessageInfo> privateMessages = new ArrayList<>();
    List<String> users = new ArrayList<>(); //List of current users

    public ClientImplGui(commonInterface chat, String comingusername) throws RemoteException {

        initFrame();
        initComponents();
        this.chat = chat;
        this.username = comingusername;
        chat.login(username);
        runClient();
    }
  
    
    private void runClient() {

        //Title of Jframe.
        this.setTitle("Hi, " + username);
     
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Keep refreshing    
                while (true) {
                    try {
                        refreshWholePage();
                        //refresh the whole page for every 1 second.
                        Thread.sleep(1000);
                    } catch (InterruptedException | ParseException ex) {
                        Logger.getLogger(ClientImplGui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();
        
    }    

    public void refreshWholePage() throws ParseException {
        try {

            //refresh the mainTextPane.
            refreshChatList();
            //refresh the current userlist in the chat room
            refreshUserList();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientImplGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshChatList() throws RemoteException, ParseException {

        //Everytime, delete all previous texts.
        mainTextArea.setText("");

        //Get all the messages.
        mainMessages = chat.getCurrentMessages();
//        privateMessages = chat.getPrivateMessages();
//        
//        mainMessages.addAll(privateMessages);
        
        //Initialize the string to including all the information then pass to JTextpane.setText("...");
        //Here string needs to include HTML tags, otherwise, emoji cannot be revealed.
        String toMainPaneInput = "<html><body>";
        for (int i = 0; i < mainMessages.size(); i++) {
                
               
                String emoji = mainMessages.get(i).getEmoji();
                Date date = mainMessages.get(i).getDate();

                //get the time in hh/mm/ss format
                SimpleDateFormat pf = new SimpleDateFormat("HH:mm:ss");

                //Have to take different situations into consideration,
                //if emoji is null, no need to add it;
                //if message is null, no need to add it, otherwise message will be shown as "null";
                //To check if getEmoji was null first would be faster for emoji to pop up on your screen. 
                if (mainMessages.get(i).getEmoji() != null) {
                    toMainPaneInput
                            += "<p style='color:blue;padding:0px;margin:0px'>"
                            + "On "
                            + pf.format(date)
                            + ", "
                            + mainMessages.get(i).getUserName()
                            + ": </p>"
                            + mainMessages.get(i).getEmoji()
                            + "<br/>";

                } else {
                    
                    toMainPaneInput
                            += "<p style='color:blue;padding:0px;margin:0px'>"
                            + "On "
                            + pf.format(date)
                            + ", "
                            + mainMessages.get(i).getUserName()
                            + ": </p>"
                            + "<p style='padding:0px; margin:0px'>"
                            + mainMessages.get(i).getMessage()
                            + "</p><br/>";
                    
            }

        }
        toMainPaneInput += "</body></html>";
        mainTextArea.setText(toMainPaneInput);

    }

    private void refreshUserList() throws RemoteException {

        //clear the list and add current users into the model then to JList.
        DefaultListModel model = new DefaultListModel();
        model.clear();
        chatList.setModel(model);
        users = chat.getAllUsers();

        //add for the current user apart from yourself to the chatList.
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).equals(username)) {
                model.addElement(users.get(i));
            }
        }
        chatList.setModel(model);
    }

    //Initialize the Frame, make it display in the middle of the screen and cannot be resized.
    public void initFrame() {
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle(username + "'s chat room");
    }

    public void insertEmoji(String emojiName) {

        MessageInfo emojiMessage = new MessageInfo();
        emojiMessage.setUserName(username);
        emojiMessage.setDate(new Date());
        //can setyp to anything else apart from on and off
        emojiMessage.setStatus("Emoji-ing");
        String emoji = "<img style='padding:0px;margin:0px' src='"
                + this.getClass().getResource("/source/" + emojiName + ".png")
                + "'"
                + "/><br/>";
        emojiMessage.setEmoji(emoji);
        try {
            chat.sendMessage(emojiMessage);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientImplGui.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatPanel = new javax.swing.JPanel();
        sendMessageButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        emojiLabel = new javax.swing.JLabel();
        currentUserListLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        happyLabel = new javax.swing.JLabel();
        angryLabel = new javax.swing.JLabel();
        sadLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mainTextArea = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        userInputTextArea = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatList = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chatPanel.setBackground(new java.awt.Color(255, 255, 255));
        chatPanel.setPreferredSize(new java.awt.Dimension(600, 700));
        chatPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sendMessageButton.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        sendMessageButton.setText("Send");
        sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageButtonActionPerformed(evt);
            }
        });
        chatPanel.add(sendMessageButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 650, 130, 50));

        logoutButton.setFont(new java.awt.Font("Tempus Sans ITC", 1, 13)); // NOI18N
        logoutButton.setText("Log out");
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButtonMouseExited(evt);
            }
        });
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        chatPanel.add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 90, -1));

        emojiLabel.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        emojiLabel.setText("Emoji (click to insert)");
        chatPanel.add(emojiLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 160, -1));

        currentUserListLabel.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        currentUserListLabel.setText("Current user list:");
        chatPanel.add(currentUserListLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(240, 240, 240));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        happyLabel.setFont(new java.awt.Font("Tempus Sans ITC", 1, 13)); // NOI18N
        happyLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/source/happy.png"))); // NOI18N
        happyLabel.setText("-Happy");
        happyLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                happyLabelMouseClicked(evt);
            }
        });
        jPanel1.add(happyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 90));

        angryLabel.setFont(new java.awt.Font("Tempus Sans ITC", 1, 13)); // NOI18N
        angryLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/source/angry.png"))); // NOI18N
        angryLabel.setText("-Angry");
        angryLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                angryLabelMouseClicked(evt);
            }
        });
        jPanel1.add(angryLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, -1));

        sadLabel.setFont(new java.awt.Font("Tempus Sans ITC", 1, 13)); // NOI18N
        sadLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/source/sad.png"))); // NOI18N
        sadLabel.setText("-Sad");
        sadLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sadLabelMouseClicked(evt);
            }
        });
        jPanel1.add(sadLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 160, -1));

        chatPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 160, 320));

        mainTextArea.setEditable(false);
        mainTextArea.setContentType("text/html"); // NOI18N
        mainTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(mainTextArea);

        chatPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 440, 600));

        jScrollPane3.setAutoscrolls(true);
        jScrollPane3.setViewportView(userInputTextArea);

        chatPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 650, 310, 50));

        jScrollPane4.setViewportView(chatList);

        chatPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, 320));

        jButton1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 13)); // NOI18N
        jButton1.setText("Retrieve Histories");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        chatPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 150, -1));

        getContentPane().add(chatPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageButtonActionPerformed
        String input = userInputTextArea.getText();
        userInputTextArea.setText("");
        MessageInfo message = new MessageInfo();
        message.setUserName(username);
        
        //can setup to anything apart from on and off.
        message.setStatus("Texting");
        message.setMessage(input);
        message.setDate(new Date());
        try {
            chat.sendMessage(message);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientImplGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendMessageButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        try {
            chat.logout(username);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientImplGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void logoutButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseEntered

    }//GEN-LAST:event_logoutButtonMouseEntered

    private void logoutButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseExited

    }//GEN-LAST:event_logoutButtonMouseExited

    private void happyLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_happyLabelMouseClicked

        insertEmoji("happy");
    }//GEN-LAST:event_happyLabelMouseClicked

    private void angryLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_angryLabelMouseClicked
        insertEmoji("angry");
    }//GEN-LAST:event_angryLabelMouseClicked

    private void sadLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sadLabelMouseClicked
        insertEmoji("sad");
    }//GEN-LAST:event_sadLabelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try {
            new RetrieveHistory(chat);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientImplGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel angryLabel;
    private javax.swing.JList<String> chatList;
    private javax.swing.JPanel chatPanel;
    private javax.swing.JLabel currentUserListLabel;
    private javax.swing.JLabel emojiLabel;
    private javax.swing.JLabel happyLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextPane mainTextArea;
    private javax.swing.JLabel sadLabel;
    private javax.swing.JButton sendMessageButton;
    private javax.swing.JTextPane userInputTextArea;
    // End of variables declaration//GEN-END:variables

}
