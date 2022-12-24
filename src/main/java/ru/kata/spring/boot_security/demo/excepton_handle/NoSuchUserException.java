package ru.kata.spring.boot_security.demo.excepton_handle;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
