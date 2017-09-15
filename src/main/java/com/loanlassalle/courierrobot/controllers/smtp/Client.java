package com.loanlassalle.courierrobot.controllers.smtp;

import com.loanlassalle.courierrobot.models.email.Email;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provide a SMTP client
 *
 * @author Lassalle Loan
 * @author Tano Iannetta
 * @version 1.0
 * @since 04.04.2017
 */
public class Client {

    /**
     * Used to keep track of informations and errors of executing
     */
    private final static Logger LOG = Logger.getLogger(Client.class.getName());

    /**
     * Used to indicates disconnection from SMTP server
     */
    private final static String disconnectMessage = "Cleaning up com.loanlassalle.courierrobot.resources...";

    /**
     * Used to complete the sending of data
     */
    private String endOfData;

    /**
     * Used to connect to SMTP server, receive and write data
     */
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    /**
     * Connects client to SMTP server
     *
     * @param address SMTP server address
     * @param port    SMTP server port
     * @throws IOException if socket, stream of read and stream of write can not be created
     */
    public void connect(String address, int port) throws IOException {
        try {
            socket = new Socket(address, port);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),
                    true);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * Disconnects client to SMTP server
     *
     * @throws IOException if socket, stream of read and stream of write can not be closed
     */
    public void disconnect() throws IOException {
        try {
            LOG.info(disconnectMessage);
            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * Indicates if SMTP client is connected
     *
     * @return true if SMTP client is connected, false otherwise
     */
    public boolean isConnected() {
        return socket != null && socket.isClosed();
    }

    /**
     * Sends line to SMTP server
     *
     * @param line string to send to SMTP  server
     */
    public void send(String line) {
        printWriter.println(line);
    }

    /**
     * Initializes send email
     *
     * @throws IOException if client can not receive data from SMTP server or send to SMTP server
     */
    public void initializeSendEmail(String address, int port) throws IOException {
        try {
            // Connects client to SMTP server
            connect(address, port);

            // Welcome messages
            LOG.info(receive());
            send(Protocol.EHLO);

            // Receives SMTP Server Information
            receive();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * Sends email through SMTP communication
     *
     * @throws IOException if client can not receive data from SMTP server or send to SMTP server
     */
    public void sendEmail(Email email) throws IOException {
        try {
            // Defines emails addresses for fictitious senders
            send(Protocol.MAIL_FROM + email.getSender());
            LOG.info(Protocol.MAIL_FROM + receive());

            // Defines emails addresses for real senders and recipients
            for (String recipient : email.getRecipient().split(Protocol.EMAIL_ADDRESSES_SEPARATOR)) {
                send(Protocol.RCPT_TO + recipient);
                LOG.info(Protocol.RCPT_TO + receive());
            }

            // Initializes content of email
            send(Protocol.DATA);
            receive();

            // Defines emails addresses for fictitious senders and recipients
            send(Protocol.FROM + email.getSender());
            send(Protocol.TO + email.getRecipient());

            if (email.getCarbonCopy() != null) {
                send(Protocol.CC + email.getCarbonCopy());
            }

            if (email.getBlindCarbonCopy() != null) {
                send(Protocol.BCC + email.getBlindCarbonCopy());
            }

            // Defines the subject and body of the email
            send(Protocol.CONTENT_TYPE + ";" + Protocol.CHARSET);
            String subjectTo64 = DatatypeConverter.printBase64Binary(email.getSubject().getBytes());
            send(Protocol.SUBJECT_START + subjectTo64 + Protocol.SUBJECT_END + System.lineSeparator());
            send(email.getBody());
            sendEndOfData();

            // End of data transmission
            LOG.info(Protocol.END_OF_DATA + receive());
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * Sends end of data
     */
    public void sendEndOfData() {
        send(endOfData);
    }

    /**
     * Terminates SMTP communication with server
     *
     * @throws IOException if client can not receive data from SMTP server or send to SMTP server
     */
    public void terminateSend() throws IOException {
        try {
            send(Protocol.QUIT);
            LOG.info(Protocol.BYE + receive());

            // Disconnects client to SMTP server
            disconnect();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * Receives message from SMTP server
     *
     * @return string received from SMTP server
     * @throws IOException if read of stream can not read
     */
    public String receive() throws IOException {
        String line;
        StringBuilder message = new StringBuilder();

        // Reads stream until end of received
        try {
            do {
                line = bufferedReader.readLine();
                message.append(line).append(System.lineSeparator());
            } while (line.substring(3, 4).equals(Protocol.END_OF_RECEIVE));

            getEndOfData(line);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }

        return message.toString();
    }

    /**
     * Gets back sequence of end of data
     *
     * @param line received line before sending data
     */
    private void getEndOfData(String line) {
        int withPosition = line.indexOf("with");

        if (withPosition != -1) {
            endOfData = line.substring(withPosition + 5)
                    .replace("<", "")
                    .replace(">", "")
                    .replace("CR", "\r")
                    .replace("LF", "\n");
        }
    }
}
