package com.loanlassalle.courierrobot.models.email;

/**
 * Provides representation of email
 *
 * @author Lassalle Loan
 * @author Tano Iannetta
 * @version 1.0
 * @since 04.04.2017
 */
public class Email {

    /**
     * Characteristics of email
     */
    private String sender;
    private String recipient;
    private String carbonCopy;
    private String blindCarbonCopy;
    private String subject;
    private String body;

    public Email() {

    }

    public Email(String sender, String recipient, String carbonCopy, String blindCarbonCopy, String subject, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.carbonCopy = carbonCopy;
        this.blindCarbonCopy = blindCarbonCopy;
        this.subject = subject;
        this.body = body;
    }

    /**
     * Gets back sender of email
     *
     * @return sender of email
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets sender of email
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Gets back recipient of email
     *
     * @return recipient of email
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Sets sender of email
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * Gets back carbon copy of email
     *
     * @return carbon copy of email
     */
    public String getCarbonCopy() {
        return carbonCopy;
    }

    /**
     * Sets carbon copy of email
     */
    public void setCarbonCopy(String carbonCopy) {
        this.carbonCopy = carbonCopy;
    }

    /**
     * Gets back blind carbon copy of email
     *
     * @return blind carbon copy of email
     */
    public String getBlindCarbonCopy() {
        return blindCarbonCopy;
    }

    /**
     * Sets blind carbon copy of email
     */
    public void setBlindCarbonCopy(String blindCarbonCopy) {
        this.blindCarbonCopy = blindCarbonCopy;
    }

    /**
     * Gets back subject of email
     *
     * @return subject of email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets subject of email
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets back body of email
     *
     * @return body of email
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets body of email
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Represents email as a string
     */
    @Override
    public String toString() {
        return "Email{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
