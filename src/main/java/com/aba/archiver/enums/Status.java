package com.aba.archiver.enums;

import java.util.stream.Stream;

/**
 * Enum of job statuses
 */
public enum Status
{
    /** Passed      */ PASSED      ("Pass"),
    /** Failed      */ FAILED      ("Fail");

    private final String name;

    /**
     * Constructor
     *
     * @param name the name of the status enum
     */
    private Status(String name)
    {
        this.name = name;
    }

    /**
     * Returns the enum matching the specified key
     *
     * @param key The string value of the enum
     * @return The enum
     */
    public static Status getEnum(String key)
    {
        return Stream.of(values())
                     .filter(e -> e.getName().equalsIgnoreCase(key))
                     .findFirst()
                     .orElseThrow(() -> new EnumConstantNotPresentException(Status.class, "Invalid enum: " + key));
    }

    /**
     * Returns the enum name
     *
     * @return the enum name
     */
    public String getName()
    {
        return this.name;
    }
}
