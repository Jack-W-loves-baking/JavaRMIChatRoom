package client;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jack1
 */
public class ApplyForNewMember extends javax.swing.JPanel {

    /**
     * GUI for creating new member in the database.
     */
    public Login mj;
    private int passwordClick = 0;
    private final String quote = "'";
    private boolean uniqueName = false;
    private database.DBOperations db = new database.DBOperations();

    public ApplyForNewMember(Login frame) {

        this.setVisible(true);
        this.mj = frame;
        initComponents();

    }
    
    //switch panel
    public void goBackToLoginPage() {
        this.mj.getContentPane().removeAll();
        this.mj.add(this.mj.getPanel());
        this.mj.revalidate();
        this.mj.repaint();
    }

    public boolean checkUniqueName() {

        //Check if the username is unique       
        db.createConnection();
        db.createTable();
        String sql = "SELECT * FROM UserData WHERE Username = " + quote + nameText.getText() + quote;
        try {
            if (!db.selectQuery(sql).next()) {
                uniqueName = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplyForNewMember.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqueName;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        registerButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        checkValidationButton = new javax.swing.JButton();
        showPasswordButton = new javax.swing.JButton();
        passwordText = new javax.swing.JPasswordField();
        emailLabel = new javax.swing.JLabel();
        emailText = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1000, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Apply for new membership");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 310, -1));

        registerButton.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        registerButton.setText("Submit");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, -1, 40));

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, -1));

        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });
        add(nameText, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 160, 30));

        checkValidationButton.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        checkValidationButton.setText("Check Validation");
        checkValidationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkValidationButtonActionPerformed(evt);
            }
        });
        add(checkValidationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 180, 30));

        showPasswordButton.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        showPasswordButton.setText("Show password");
        showPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordButtonActionPerformed(evt);
            }
        });
        add(showPasswordButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 180, 30));

        passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextActionPerformed(evt);
            }
        });
        add(passwordText, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 160, 30));

        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("Email:");
        add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, -1, -1));
        add(emailText, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 160, 30));

        backButton.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        backButton.setText("<Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/source/memberbackground.png"))); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 500));
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed

        //get encryped password
        passwordText.setEchoChar('*');
        String password = new String(passwordText.getPassword());

        //check if user has completed everything.
        //if have not
        if ((nameText.getText().equals("")) || (password.equals("")) || (emailText.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Please fill in all your details");
        } //if complete everything
        else {

            //If username is not unique
            if (!checkUniqueName()) {
                JOptionPane.showMessageDialog(null, "Sorry " + nameText.getText() + " has been used!", "Warning!", JOptionPane.ERROR_MESSAGE);
            } else {
                //Insert into database                      
                db.insertIntoDatabase(nameText.getText(), password, emailText.getText());

                JOptionPane.showMessageDialog(null, "You have registered");

                //back to login
                goBackToLoginPage();
            }
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void showPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordButtonActionPerformed
        
        //Toal number of click if in is odd number, password will be unencryped/revealed.
        //if even number, password will be encryped.
        passwordClick++;
        passwordText.setEchoChar((char) 0);

        if (passwordClick % 2 == 0) {
            passwordText.setEchoChar('*');
        }

    }//GEN-LAST:event_showPasswordButtonActionPerformed

    private void checkValidationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkValidationButtonActionPerformed

        if (!checkUniqueName()) {
            JOptionPane.showMessageDialog(null, "Sorry " + nameText.getText() + " has been used!", "Warning!", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Congrats, " + nameText.getText() + " can be used!");

        }


    }//GEN-LAST:event_checkValidationButtonActionPerformed

    private void passwordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        goBackToLoginPage();
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton checkValidationButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nameText;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton showPasswordButton;
    // End of variables declaration//GEN-END:variables
}
