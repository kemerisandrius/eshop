package lt.codeacademy.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MessageBean {

    private final String message;

    public MessageBean(@Value("${greeting.message}") String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
