package fragnito.U5W3D2.controllers;

import fragnito.U5W3D2.entities.Dipendente;
import fragnito.U5W3D2.entities.Prenotazione;
import fragnito.U5W3D2.exceptions.Validation;
import fragnito.U5W3D2.payloads.MinePrenotazioneDTO;
import fragnito.U5W3D2.payloads.PrenotazioneDTO;
import fragnito.U5W3D2.payloads.PrenotazioneOwnershipDTO;
import fragnito.U5W3D2.payloads.RespDTO;
import fragnito.U5W3D2.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private Validation validation;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postPrenotazione(@RequestBody @Validated PrenotazioneDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Prenotazione prenotazione = this.prenotazioneService.savePrenotazione(body);
        return new RespDTO(prenotazione.getId());
    }

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postMinePrenotazione(@AuthenticationPrincipal Dipendente currentUser, @RequestBody @Validated MinePrenotazioneDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Prenotazione prenotazione = this.prenotazioneService.saveMinePrenotazione(currentUser, body);
        return new RespDTO(prenotazione.getId());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<Prenotazione> getAllPrenotazioni(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                                 @RequestParam(defaultValue = "id") String sortBy) {
        return this.prenotazioneService.getAllPrenotazioni(page, size, sortBy);
    }

    @GetMapping("/me")
    public Page<Prenotazione> getAllMinePrenotazioni(@AuthenticationPrincipal Dipendente currentUser, @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy) {
        return this.prenotazioneService.getMinePrenotazioni(currentUser, page, size, sortBy);
    }

    @GetMapping("/{prenotazioneId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Prenotazione getPrenotazioneById(@PathVariable int prenotazioneId) {
        return this.prenotazioneService.getPrenotazioneById(prenotazioneId);
    }

    @PutMapping("/{prenotazioneId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public RespDTO putPrenotazione(@PathVariable int prenotazioneId, @RequestBody @Validated PrenotazioneDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Prenotazione prenotazione = this.prenotazioneService.updatePrenotazione(prenotazioneId, body);
        return new RespDTO(prenotazione.getId());
    }

    @PutMapping("/me/{prenotazioneId}")
    public RespDTO putPrenotazione(@AuthenticationPrincipal Dipendente currentUser, @PathVariable int prenotazioneId, @RequestBody @Validated MinePrenotazioneDTO body,
                                   BindingResult validation) {
        this.validation.validate(validation);
        Prenotazione prenotazione = this.prenotazioneService.updateMinePrenotazione(prenotazioneId, body);
        return new RespDTO(prenotazione.getId());
    }

    @DeleteMapping("/{prenotazioneId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable int prenotazioneId) {
        this.prenotazioneService.deletePrenotazione(prenotazioneId);
    }

    @DeleteMapping("/me/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMinePrenotazione(@AuthenticationPrincipal Dipendente currentUser, @PathVariable int prenotazioneId) {
        this.prenotazioneService.deletePrenotazione(prenotazioneId);
    }

    @PatchMapping("/{prenotazioneId}/dipendente")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public RespDTO changePrenotazioneOwnership(@PathVariable int prenotazioneId, @RequestBody @Validated PrenotazioneOwnershipDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Prenotazione prenotazione = this.prenotazioneService.changePrenotazioneOwnership(prenotazioneId, body);
        return new RespDTO(prenotazione.getId());
    }
}
