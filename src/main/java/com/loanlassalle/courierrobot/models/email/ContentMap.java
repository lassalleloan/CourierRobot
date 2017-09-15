package com.loanlassalle.courierrobot.models.email;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides representation of emails contents
 *
 * @author Lassalle Loan
 * @author Tano Iannetta
 * @version 1.0
 * @since 04.04.2017
 */
public class ContentMap implements IMap {

    /**
     * Used to store subject and content of email
     */
    private Map<String, String> contentMap;

    public ContentMap() {
        this.contentMap = new HashMap<>();
    }

    public ContentMap(String subject, String content) {
        this();
        putSubject(subject);
        putSubject(content);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key key of the value
     * @return value to which the specified key is mapped
     */
    public String get(String key) {
        return contentMap.get(key);
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}) associates it with the given value and returns
     * {@code null}, else returns the current value.
     *
     * @param subject subject of email
     */
    public void putSubject(String subject) {
        contentMap.putIfAbsent("subject", subject);
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}) associates it with the given value and returns
     * {@code null}, else returns the current value.
     *
     * @param content content of email
     */
    public void putContent(String content) {
        contentMap.putIfAbsent("content", content);
    }

    /**
     * Removes the mapping for a key from this map if it is present
     *
     * @param key key of the value
     */
    public void remove(String key) {
        contentMap.remove(key);
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     */
    public void clear() {
        contentMap.clear();
    }

    /**
     * Represents ContentMap as a string
     */
    @Override
    public String toString() {
        return "ContentMap{" +
                "contentMap=" + contentMap +
                '}';
    }
}
