package fragnito.U5W3D2.controllers;

import fragnito.U5W3D2.entities.Dipendente;
import fragnito.U5W3D2.exceptions.Validation;
import fragnito.U5W3D2.payloads.AuthDTO;
import fragnito.U5W3D2.payloads.NewDipendenteDTO;
import fragnito.U5W3D2.payloads.RespDTO;
import fragnito.U5W3D2.services.AuthService;
import fragnito.U5W3D2.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private Validation validation;

    @PostMapping("/login")
    public String login(@RequestBody AuthDTO body) {
        return this.authService.generateToken(body);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Dipendente dipendente = this.dipendenteService.saveDipendente(body);
        return new RespDTO(dipendente.getId());
    }
}
