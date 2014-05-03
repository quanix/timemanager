package com.domac.app.support.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : lihaoquan
 */
@ManagedResource(objectName = ApplicationStatistics.MBEAN_NAME, description = "Application Statistics Management Bean")
public class ApplicationStatistics {

    public static final String MBEAN_NAME = "application:name=ApplicationStatistics";

    private final AtomicInteger listUserTimes = new AtomicInteger();
    private final AtomicInteger updateUserTimes = new AtomicInteger();

    public void incrListUserTimes() {
        listUserTimes.incrementAndGet();
    }

    public void incrUpdateUserTimes() {
        updateUserTimes.incrementAndGet();
    }

    @ManagedAttribute(description = "Times of users be listed")
    public int getListUserTimes() {
        return listUserTimes.get();
    }

    @ManagedAttribute(description = "Times of users be updated")
    public int getUpdateUserTimes() {
        return updateUserTimes.get();
    }

    @ManagedOperation(description = "Reset all statistics")
    public void resetStatistics() {
        listUserTimes.set(0);
        updateUserTimes.set(0);
    }
}
