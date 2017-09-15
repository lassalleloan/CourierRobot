package com.loanlassalle.courierrobot;

import com.loanlassalle.courierrobot.controllers.manager.ConfigurationManager;
import com.loanlassalle.courierrobot.controllers.smtp.Client;
import com.loanlassalle.courierrobot.models.email.ContentMap;
import com.loanlassalle.courierrobot.models.email.GroupMap;
import com.loanlassalle.courierrobot.models.messages.Prank;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Sends a list of emails with random group of emails addresses and random contents of email
 *
 * @author Lassalle Loan
 * @author Tano Iannetta
 * @version 1.0
 * @since 04.04.2017
 */
public class CourierRobot {

    public static void main(String[] args) {
        try {
            // Used to pick up an email's content randomly
            Random random = new Random();

            // Gets back configuration properties
            ConfigurationManager configurationManager = new ConfigurationManager();

            // Gets back server's configuration
            String serverAddress = configurationManager.getSmtpServerAddress();
            int serverPort = configurationManager.getSmtpServerPort();
            String witnessEmailAddress = configurationManager.getWitnessEmailAddress();

            // Gets back email address groups and email's contents
            List<GroupMap> groupMapList = configurationManager.getGroupMapList();
            List<ContentMap> contentMapList = configurationManager.getContentMapList();
            final int contentMapListSize = contentMapList.size();

            // Connects a client to SMTP server and initializes send email
            Client client = new Client();
            client.initializeSendEmail(serverAddress, serverPort);

            for (GroupMap groupMap : groupMapList) {
                // Creates and sends an email prank
                groupMap.putBlindCarbonCopy(witnessEmailAddress);
                Prank prank = new Prank(groupMap, contentMapList.get(random.nextInt(contentMapListSize)));
                client.sendEmail(prank.getEmail());
            }

            // Terminate SMTP communication
            client.terminateSend();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
