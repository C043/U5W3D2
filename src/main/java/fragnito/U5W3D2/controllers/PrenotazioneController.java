package fragnito.U5W3D2.controllers;

import fragnito.U5W3D2.entities.Prenotazione;
import fragnito.U5W3D2.exceptions.Validation;
import fragnito.U5W3D2.payloads.PrenotazioneDTO;
import fragnito.U5W3D2.payloads.PrenotazioneOwnershipDTO;
import fragnito.U5W3D2.payloads.RespDTO;
import fragnito.U5W3D2.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postPrenotazione(@RequestBody @Validated PrenotazioneDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Prenotazione prenotazione = this.prenotazioneService.savePrenotazione(body);
        return new RespDTO(prenotazione.getId());
    }

    @GetMapping
    public Page<Prenotazione> getAllPrenotazioni(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                                 @RequestParam(defaultValue = "id") String sortBy) {
        return this.prenotazioneService.getAllPrenotazioni(page, size, sortBy);
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione getPrenotazioneById(@PathVariable int prenotazioneId) {
        return this.prenotazioneService.getPrenotazioneById(prenotazioneId);
    }

    @PutMapping("/{prenotazioneId}")
    public RespDTO putPrenotazione(@PathVariable int prenotazioneId, @RequestBody @Validated PrenotazioneDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Prenotazione prenotazione = this.prenotazioneService.updatePrenotazione(prenotazioneId, body);
        return new RespDTO(prenotazione.getId());
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable int prenotazioneId) {
        this.prenotazioneService.deletePrenotazione(prenotazioneId);
    }

    @PatchMapping("/{prenotazioneId}/dipendente")
    public RespDTO changePrenotazioneOwnerchip(@PathVariable int prenotazioneId, @RequestBody @Validated PrenotazioneOwnershipDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Prenotazione prenotazione = this.prenotazioneService.changePrenotazioneOwnership(prenotazioneId, body);
        return new RespDTO(prenotazione.getId());
    }
}
