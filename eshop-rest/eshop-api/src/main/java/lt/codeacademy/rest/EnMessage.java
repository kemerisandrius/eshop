package lt.codeacademy.rest;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("en")
@Component
public class EnMessage implements MessageBean {
    @Override
    public String getMessage() {
        return "Hello, World!";
    }
}
