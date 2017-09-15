package com.loanlassalle.courierrobot.controllers.smtp;

/**
 * Provide a SMTP protocol
 *
 * @author Lassalle Loan
 * @author Tano Iannetta
 * @version 1.0
 * @since 04.04.2017
 */
public class Protocol {

    /**
     * Used to separate emails addresses
     */
    public final static String EMAIL_ADDRESSES_SEPARATOR = ", ";

    /**
     * Used to know end of receive from SMTP server
     */
    public final static String END_OF_RECEIVE = "-";

    /**
     * Used to communicate with SMTP Server
     */
    public final static String EHLO = "EHLO CourierRobot";
    public final static String MAIL_FROM = "MAIL FROM: ";
    public final static String RCPT_TO = "RCPT TO: ";
    public final static String DATA = "DATA";
    public final static String FROM = "From: ";
    public final static String TO = "To: ";
    public final static String CC = "Cc: ";
    public final static String BCC = "Bcc: ";
    public final static String CONTENT_TYPE = "Content-Type: text/plain";
    public final static String CHARSET = "charset=UTF-8";
    public final static String SUBJECT_START = "Subject: =?utf-8?B?";
    public final static String SUBJECT_END = "?=";
    public final static String END_OF_DATA = "ENDOFDATA ";
    public final static String QUIT = "QUIT ";
    public final static String BYE = "BYE ";
}
