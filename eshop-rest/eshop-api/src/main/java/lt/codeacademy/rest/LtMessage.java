package lt.codeacademy.rest;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("lt")
@Component
public class LtMessage implements MessageBean {

    @Override
    public String getMessage() {
        return "Sveikas, Pasauli!";
    }
}
