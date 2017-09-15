package com.loanlassalle.courierrobot.models.email;

/**
 * Provides basic representation of custom Map interface
 *
 * @author Lassalle Loan
 * @author Tano Iannetta
 * @version 1.0
 * @since 04.04.2017
 */
public interface IMap {

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key key of the value
     * @return value to which the specified key is mapped
     */
    String get(String key);

    /**
     * Removes the mapping for a key from this map if it is present
     *
     * @param key key of the value
     */
    void remove(String key);

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     */
    void clear();

    /**
     * Represents IMap as a string
     */
    String toString();
}
