package com.loanlassalle.courierrobot.controllers.manager;

import com.loanlassalle.courierrobot.CourierRobot;
import com.loanlassalle.courierrobot.controllers.smtp.Protocol;
import com.loanlassalle.courierrobot.models.email.ContentMap;
import com.loanlassalle.courierrobot.models.email.GroupMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides configuration properties, groups of emails addresses and contents of emails
 *
 * @author Lassalle Loan
 * @author Tano Iannetta
 * @version 1.0
 * @since 04.04.2017
 */
public class ConfigurationManager {

    /**
     * Used to keep track of informations and errors of executing
     */
    private final static Logger LOG = Logger.getLogger(ConfigurationManager.class.getName());

    /**
     * Used to show an error if there is not enough emails addresses to create a group
     */
    private final static String notEnoughEmailAddress = "There is not enough emails addresses to create a group";

    /**
     * Used to pick up an email address randomly
     */
    private Random random;

    /**
     * Used to connect to SMTP server
     */
    private String smtpServerAddress;
    private int smtpServerPort;

    /**
     * Used to creation of groups of emails addresses
     */
    private int numberOfGroup;
    private String witnessEmailAddress;

    /**
     * Used to list groups of emails addresses and contents of emails
     */
    private List<GroupMap> groupMapList;
    private List<ContentMap> contentMapList;

    public ConfigurationManager() throws IOException {
        this("resources/config.properties", "resources/emailsAddresses.utf8",
                "resources/emailsContents.utf8");
    }

    public ConfigurationManager(String configFile, String emailsAddressesFile, String emailsContentFile)
            throws IOException {
        random = new Random();

        loadProperties(configFile);
        loadGroupMapList(emailsAddressesFile);
        loadContentMapList(emailsContentFile);
    }

    /**
     * Gets back SMTP server address
     *
     * @return SMTP server address
     */
    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    /**
     * Sets SMTP server address
     *
     * @param smtpServerAddress SMTP server address
     */
    public void setSmtpServerAddress(String smtpServerAddress) {
        this.smtpServerAddress = smtpServerAddress;
    }

    /**
     * Gets back SMTP server port
     *
     * @return SMTP server port
     */
    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    /**
     * Sets SMTP server port
     *
     * @param smtpServerPort SMTP server port
     */
    public void setSmtpServerPort(int smtpServerPort) {
        this.smtpServerPort = smtpServerPort;
    }

    /**
     * Gets back number of group of emails addresses
     *
     * @return number of group of emails addresses
     */
    public int getNumberOfGroup() {
        return numberOfGroup;
    }

    /**
     * Sets number of group of emails addresses
     *
     * @param numberOfGroup number of group of emails addresses
     */
    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    /**
     * Gets back witness email address
     *
     * @return witness email address
     */
    public String getWitnessEmailAddress() {
        return witnessEmailAddress;
    }

    /**
     * Sets witness email address
     *
     * @param witnessEmailAddress witness email address
     */
    public void setWitnessEmailAddress(String witnessEmailAddress) {
        this.witnessEmailAddress = witnessEmailAddress;
    }

    /**
     * Gets back list of group of emails addresses
     *
     * @return list of group of emails addresses
     */
    public List<GroupMap> getGroupMapList() {
        return groupMapList;
    }

    /**
     * Sets list of group of emails addresses
     *
     * @param groupMapList list of group of emails addresses
     */
    public void setGroupMapList(List<GroupMap> groupMapList) {
        this.groupMapList = groupMapList;
    }

    /**
     * Gets back list of contents of emails addresses
     *
     * @return list of contents of emails addresses
     */
    public List<ContentMap> getContentMapList() {
        return contentMapList;
    }

    /**
     * Sets list of contents of emails addresses
     *
     * @param contentMapList list of contents of emails addresses
     */
    public void setContentMapList(List<ContentMap> contentMapList) {
        this.contentMapList = contentMapList;
    }

    /**
     * Loads properties of SMTP server connect and groups of emails addresses
     *
     * @param filename name of properties file
     * @throws IOException if properties file or targeted property does not exist
     */
    private void loadProperties(String filename) throws IOException {
        try {
            // Gets back properties file from root class
            Properties properties = new Properties();
            properties.load(CourierRobot.class.getResourceAsStream(filename));

            // Loads properties to connect SMTP client
            smtpServerAddress = properties.getProperty("smtpServerAddress");
            smtpServerPort = Integer.valueOf(properties.getProperty("smtpServerPort"));

            // Loads properties to define groups of emails addresses
            numberOfGroup = Integer.valueOf(properties.getProperty("numberOfGroup"));
            witnessEmailAddress = properties.getProperty("witnessEmailAddress");
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * Loads groups of emails addresses
     *
     * @param filename emails addresses file
     * @throws IOException if emails addresses file does not exist or if there is not enough emails addresses
     *                     to create a group
     */
    private void loadGroupMapList(String filename) throws IOException {
        groupMapList = new ArrayList<>();

        // Stream to read the file compared with location of root program
        InputStream inputStream = CourierRobot.class.getResourceAsStream(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        // Gets back all emails addresses
        String line;
        List<String> emailAddressList = new ArrayList<>();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                emailAddressList.add(line);
            }
            bufferedReader.close();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }

        int nbEmailAddress = emailAddressList.size();
        int nbEmailAddressByGroup = nbEmailAddress / numberOfGroup;

        StringBuilder stringBuilder = new StringBuilder();

        // Verify number of emails addresses
        if (nbEmailAddressByGroup < 3) {
            LOG.log(Level.SEVERE, notEnoughEmailAddress);
            throw new IOException();
        } else {
            // Creates random groups of emails addresses
            for (int i = 0; i < numberOfGroup; ++i) {
                GroupMap groupMap = new GroupMap();
                groupMap.putSender(emailAddressList.get(random.nextInt(nbEmailAddress)));

                stringBuilder.setLength(0);
                for (int j = 0; j < nbEmailAddressByGroup - 1; ++j) {
                    stringBuilder.append(emailAddressList.get(random.nextInt(nbEmailAddress)))
                            .append(Protocol.EMAIL_ADDRESSES_SEPARATOR);
                }
                stringBuilder.append(emailAddressList.get(random.nextInt(nbEmailAddress)));

                groupMap.putRecipient(stringBuilder.toString());
                groupMapList.add(groupMap);
            }
        }
    }

    /**
     * Loads contents of emails
     *
     * @param filename emails contents file
     * @throws IOException if emails contents file does not exist
     */
    private void loadContentMapList(String filename) throws IOException {
        contentMapList = new ArrayList<>();

        // Stream to read the file compared with location of root program
        InputStream inputStream = CourierRobot.class.getResourceAsStream(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line;
        final String stopLine;
        final String subject = "Subject: ";

        StringBuilder content = new StringBuilder();
        ContentMap contentMap = new ContentMap();
        boolean isNullLine;

        try {
            // Gets back contents separator
            stopLine = bufferedReader.readLine();

            // Gets back subjects and contents
            do {
                line = bufferedReader.readLine();
                isNullLine = line == null;

                if (isNullLine || line.equals(stopLine)) {
                    contentMap.putContent(content.toString());
                    contentMapList.add(contentMap);

                    contentMap = new ContentMap();
                    content = new StringBuilder();
                } else if (line.contains(subject)) {
                    contentMap.putSubject(line.substring(subject.length()));
                } else {
                    content.append(line).append(System.lineSeparator());
                }
            } while (!isNullLine);

            bufferedReader.close();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw ex;
        }
    }
}
