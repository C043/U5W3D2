package fragnito.U5W3D2.controllers;

import fragnito.U5W3D2.entities.Dipendente;
import fragnito.U5W3D2.exceptions.Validation;
import fragnito.U5W3D2.payloads.NewDipendenteDTO;
import fragnito.U5W3D2.payloads.RespDTO;
import fragnito.U5W3D2.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private Validation validation;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Dipendente> getAllDipendenti() {
        return this.dipendenteService.getAllDipendenti();
    }

    @GetMapping("/{dipendenteId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Dipendente getDipendenteById(@PathVariable int dipendenteId) {
        return this.dipendenteService.getDipendenteById(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RespDTO putDipendente(@PathVariable int dipendenteId, @RequestBody @Validated NewDipendenteDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Dipendente updatedDipendente = this.dipendenteService.updateDipendente(dipendenteId, body);
        return new RespDTO(updatedDipendente.getId());
    }

    @DeleteMapping("/{dipendenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable int dipendenteId) {
        this.dipendenteService.deleteDipendente(dipendenteId);
    }

    @PostMapping("/{dipendenteId}/avatar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void uploadAvatar(@PathVariable int dipendenteId, @RequestParam("avatar") MultipartFile img) throws IOException {
        this.dipendenteService.uploadImage(dipendenteId, img);
    }

    @PostMapping("/me/avatar")
    public void uploadMyAvatar(@AuthenticationPrincipal Dipendente currentUser, @RequestParam("avatar") MultipartFile img) throws IOException {
        this.dipendenteService.uploadImage(currentUser.getId(), img);
    }
}
