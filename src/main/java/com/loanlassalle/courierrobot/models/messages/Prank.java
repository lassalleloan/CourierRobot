package com.loanlassalle.courierrobot.models.messages;

import com.loanlassalle.courierrobot.models.email.ContentMap;
import com.loanlassalle.courierrobot.models.email.Email;
import com.loanlassalle.courierrobot.models.email.GroupMap;

import java.util.logging.Logger;

/**
 * Provides representation of email prank
 *
 * @author Lassalle Loan
 * @author Tano Iannetta
 * @version 1.0
 * @since 04.04.2017
 */
public class Prank {

    /**
     * Used to keep track of informations and errors of executing
     */
    private final static Logger LOG = Logger.getLogger(Prank.class.getName());

    private Email email;

    public Prank(GroupMap groupMap, ContentMap contentMap) {
        this.email = new Email(groupMap.get("sender"),
                groupMap.get("recipient"),
                groupMap.get("carbonCopy"),
                groupMap.get("blindCarbonCopy"),
                contentMap.get("subject"),
                contentMap.get("content"));
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Prank{" +
                "email=" + email +
                '}';
    }
}
