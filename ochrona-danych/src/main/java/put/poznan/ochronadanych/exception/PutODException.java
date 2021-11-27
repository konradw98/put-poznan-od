package put.poznan.ochronadanych.exception;

import org.springframework.mail.MailException;

public class PutODException extends Throwable {
    public PutODException(String info) {
        super(info);
    }
}
