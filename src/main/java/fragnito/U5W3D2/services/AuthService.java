package fragnito.U5W3D2.services;

import fragnito.U5W3D2.entities.Dipendente;
import fragnito.U5W3D2.exceptions.UnauthorizedException;
import fragnito.U5W3D2.payloads.AuthDTO;
import fragnito.U5W3D2.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String generateToken(AuthDTO body) {
        Dipendente found = this.dipendenteService.findByEmail(body.email());
        if (passwordEncoder.matches(body.password(), found.getPassword())) return jwtTools.createToken(found);
        else throw new UnauthorizedException("Credenziali errate");
    }
}
