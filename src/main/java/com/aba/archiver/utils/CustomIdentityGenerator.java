package com.aba.archiver.utils;

import jakarta.persistence.Id;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * An CustomIdentityGenerator that handles IDENTITY/"autoincrement" columns. Determines if the property value
 * is generated when a row is written to the database, or in Java code that executes before the row is written.
 */
public class CustomIdentityGenerator extends IdentityGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomIdentityGenerator.class);

    /**
     * Determines if the property value is generated when a row is written to the database,
     * or in Java code that executes before the row is written.
     *
     * @param obj The instance of the entity owning the attribute for which we are generating a value.
     * @param session The session from which the request originates.
     *
     * @return true if the value is generated by the database as a side effect of the execution of an insert or update statement,
     * or false if it is generated in Java code before the statement is executed via JDBC.
     * @throws HibernateException
     */
    @Override
    public boolean generatedOnExecution(Object obj, SharedSessionContractImplementor session)
            throws HibernateException {
        Integer integerId = null;
        Long longId = null;

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            Id id = f.getAnnotation(Id.class);
            if (id != null) {
                f.setAccessible(true);
                try {
                    if (f.get(obj) != null) {
                        if (f.get(obj) instanceof Integer) {
                            integerId = (Integer) f.get(obj);
                        } else if (f.get(obj) instanceof Long) {
                            longId = (Long) f.get(obj);
                        } else {
                            LOGGER.error("Id of object is not instance of Integer or Long while using CustomSequenceGenerator for generating Ids");
                            throw new RuntimeException("Id of object is not instance of Integer or Long while using CustomSequenceGenerator for generating Ids");
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if (integerId != null && integerId > 0) {
            return false;
        } else if (longId != null && longId > 0) {
            return false;
        } else {
            return super.generatedOnExecution(obj, session);
        }
    }
}