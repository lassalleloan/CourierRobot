package com.loanlassalle.courierrobot.models.email;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides representation of group of emails addresses
 *
 * @author Lassalle Loan
 * @author Tano Iannetta
 * @version 1.0
 * @since 04.04.2017
 */
public class GroupMap implements IMap {

    /**
     * Used to store sender email address and recipient email address
     */
    private Map<String, String> groupMap;

    public GroupMap() {
        this.groupMap = new HashMap<>();
    }

    public GroupMap(String sender, String recipient, String carbonCopy, String blindCarbonCopy) {
        this();
        putSender(sender);
        putRecipient(recipient);
        putCarbonCopy(carbonCopy);
        putBlindCarbonCopy(blindCarbonCopy);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key key of the value
     * @return value to which the specified key is mapped
     */
    public String get(String key) {
        return groupMap.get(key);
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}) associates it with the given value and returns
     * {@code null}, else returns the current value.
     *
     * @param sender sender email address
     */
    public void putSender(String sender) {
        groupMap.putIfAbsent("sender", sender);
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}) associates it with the given value and returns
     * {@code null}, else returns the current value.
     *
     * @param recipient recipient email address
     */
    public void putRecipient(String recipient) {
        groupMap.putIfAbsent("recipient", recipient);
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}) associates it with the given value and returns
     * {@code null}, else returns the current value.
     *
     * @param carbonCopy carbon copy email address
     */
    public void putCarbonCopy(String carbonCopy) {
        groupMap.putIfAbsent("carbonCopy", carbonCopy);
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}) associates it with the given value and returns
     * {@code null}, else returns the current value.
     *
     * @param blindCarbonCopy blind carbon copy email address
     */
    public void putBlindCarbonCopy(String blindCarbonCopy) {
        groupMap.putIfAbsent("blindCarbonCopy", blindCarbonCopy);
    }

    /**
     * Removes the mapping for a key from this map if it is present
     *
     * @param key key of the value
     */
    public void remove(String key) {
        groupMap.remove(key);
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     */
    public void clear() {
        groupMap.clear();
    }

    /**
     * Represents GroupMap as a string
     */
    @Override
    public String toString() {
        return "GroupMap{" +
                "groupMap=" + groupMap +
                '}';
    }
}
