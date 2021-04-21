package com.legal8.customer_registration.Exception;

import java.time.ZonedDateTime;
import java.util.Date;

public class ErrorDetails {

    private ZonedDateTime timestamp;
    private String message;
    private String details;

    public ErrorDetails(ZonedDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
}
