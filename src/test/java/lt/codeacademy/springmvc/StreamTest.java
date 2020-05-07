package lt.codeacademy.springmvc;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

public class StreamTest {

    @Test
    public void test() {
        List<String> telefonai = asList("Iphone X", "Samsung s10", "Nokia 3310");

        for (String telefonas : telefonai) {
            if (telefonas.toLowerCase().contains("Iphone")) {
                break;
            } //
        }

                         // terminal operation

        Optional<String> telefonasOptional = new TelefonaiDAO().ieskokTelefono();

        telefonasOptional.ifPresent(telefonas -> System.out.println(telefonas));

        String telefonas = telefonasOptional.orElseThrow(() -> new RuntimeException());
        System.out.println(telefonas);

//        if (telefonasOptional.isPresent()) {
//            System.out.println(telefonasOptional.get());
//        } else {
//
//        }
    }

    /**
     * Mano klase grazina telefona. Jei jo neranda - grazina null.
     */
    class TelefonaiDAO {
        public Optional<String> ieskokTelefono() {
            List<String> telefonai = asList("Iphone X", "Samsung s10", "Nokia 3310");

            Optional<String> optionalas = telefonai.stream()   // resource
                    .filter(telefonas -> telefonas.toLowerCase().contains("belekas"))                              // intermediate operations
                    .findFirst();

            return optionalas;
        }
    }
}
