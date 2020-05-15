package client;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jack1
 */
public class MessageInfo implements Serializable {

    /**
     * @return get the status on or off.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param set the status to on or off
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the emoji
     */
    public String getEmoji() {
        return emoji;
    }

    /**
     * @param emoji the emoji to set
     */
    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    private String userName;
    private String message;
    private String emoji;
    private Date date;
    private String status;

}
