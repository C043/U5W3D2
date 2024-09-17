package fragnito.U5W3D2.exceptions;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Component
public class Validation {
    public void validate(BindingResult validation) {
        String messages = validation.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(". "));
        if (validation.hasErrors()) throw new BadRequestException("Ci sono stati degli errori: " + messages);
    }
}
