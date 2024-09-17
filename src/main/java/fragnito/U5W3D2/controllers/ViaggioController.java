package fragnito.U5W3D2.controllers;

import fragnito.U5W3D2.entities.Viaggio;
import fragnito.U5W3D2.exceptions.Validation;
import fragnito.U5W3D2.payloads.NewViaggioDTO;
import fragnito.U5W3D2.payloads.RespDTO;
import fragnito.U5W3D2.payloads.StatoViaggioDTO;
import fragnito.U5W3D2.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private Validation validation;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postViaggio(@RequestBody @Validated NewViaggioDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Viaggio newViaggio = this.viaggioService.saveViaggio(body);
        return new RespDTO(newViaggio.getId());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Viaggio> getAllViaggi() {
        return this.viaggioService.getAllViaggi();
    }

    @GetMapping("/{viaggioId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Viaggio getViaggioById(@PathVariable int viaggioId) {
        return this.viaggioService.getViaggioById(viaggioId);
    }

    @PutMapping("/{viaggioId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public RespDTO putViaggio(@PathVariable int viaggioId, @RequestBody @Validated NewViaggioDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Viaggio updatedViaggio = this.viaggioService.putViaggio(viaggioId, body);
        return new RespDTO(updatedViaggio.getId());
    }

    @DeleteMapping("/{viaggioId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable int viaggioId) {
        this.viaggioService.deleteViaggio(viaggioId);
    }

    @PatchMapping("/{viaggioId}/statoViaggio")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public RespDTO changeStatoViaggio(@PathVariable int viaggioId, @RequestBody @Validated StatoViaggioDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Viaggio viaggio = this.viaggioService.changeStatoViaggio(viaggioId, body);
        return new RespDTO(viaggio.getId());
    }
}
