package fragnito.U5W3D2.services;

import fragnito.U5W3D2.entities.Dipendente;
import fragnito.U5W3D2.entities.Prenotazione;
import fragnito.U5W3D2.entities.Viaggio;
import fragnito.U5W3D2.exceptions.BadRequestException;
import fragnito.U5W3D2.exceptions.NotFoundException;
import fragnito.U5W3D2.payloads.MinePrenotazioneDTO;
import fragnito.U5W3D2.payloads.PrenotazioneDTO;
import fragnito.U5W3D2.payloads.PrenotazioneOwnershipDTO;
import fragnito.U5W3D2.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ViaggioService viaggioService;

    public Prenotazione savePrenotazione(PrenotazioneDTO body) {
        Viaggio viaggio = this.viaggioService.getViaggioById(body.viaggioId());
        Dipendente dipendente = this.dipendenteService.getDipendenteById(body.dipendenteId());
        if (!this.prenotazioneRepository.filterPrenotazioniByUserAndData(dipendente.getId(), viaggio.getData()).isEmpty())
            throw new BadRequestException("Il dipendente " +
                    "è già impegnato nella data richiesta");
        Prenotazione prenotazione = new Prenotazione(dipendente, viaggio, body.note());
        this.prenotazioneRepository.save(prenotazione);
        return prenotazione;
    }

    public Prenotazione saveMinePrenotazione(Dipendente dipendente, MinePrenotazioneDTO body) {
        Viaggio viaggio = this.viaggioService.getViaggioById(body.viaggioId());
        if (!this.prenotazioneRepository.filterPrenotazioniByUserAndData(dipendente.getId(), viaggio.getData()).isEmpty())
            throw new BadRequestException("Il dipendente " +
                    "è già impegnato nella data richiesta");
        Prenotazione prenotazione = new Prenotazione(dipendente, viaggio, body.note());
        this.prenotazioneRepository.save(prenotazione);
        return prenotazione;

    }

    public Page<Prenotazione> getAllPrenotazioni(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioneRepository.findAll(pageable);
    }

    public Page<Prenotazione> getMinePrenotazioni(Dipendente dipendente, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioneRepository.findByDipendente(dipendente, pageable);
    }

    public Prenotazione getPrenotazioneById(int id) {
        return this.prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Prenotazione updatePrenotazione(int id, PrenotazioneDTO body) {
        Prenotazione found = this.getPrenotazioneById(id);
        if (found.getDipendente().getId() != body.dipendenteId()) throw new BadRequestException("Endpoint sbagliato per cambiare assegnazione di prenotazione");
        Viaggio viaggio = this.viaggioService.getViaggioById(body.viaggioId());
        found.setNote(body.note());
        found.setViaggio(viaggio);
        found.setDataRichiesta(LocalDate.now());
        this.prenotazioneRepository.save(found);
        return found;
    }

    public Prenotazione updateMinePrenotazione(int id, MinePrenotazioneDTO body) {
        Prenotazione found = this.getPrenotazioneById(id);
        Viaggio viaggio = this.viaggioService.getViaggioById(body.viaggioId());
        found.setNote(body.note());
        found.setViaggio(viaggio);
        found.setDataRichiesta(LocalDate.now());
        this.prenotazioneRepository.save(found);
        return found;
    }

    public void deletePrenotazione(int id) {
        Prenotazione found = this.getPrenotazioneById(id);
        this.prenotazioneRepository.delete(found);
    }

    public Prenotazione changePrenotazioneOwnership(int prenotazioneId, PrenotazioneOwnershipDTO body) {
        Prenotazione found = this.getPrenotazioneById(prenotazioneId);
        Viaggio viaggio = this.viaggioService.getViaggioById(found.getViaggio().getId());
        Dipendente dipendente = this.dipendenteService.getDipendenteById(body.dipendenteId());
        if (!this.prenotazioneRepository.filterPrenotazioniByUserAndData(dipendente.getId(), viaggio.getData()).isEmpty())
            throw new BadRequestException("Il dipendente " +
                    "è già impegnato nella data richiesta");
        found.setDipendente(dipendente);
        found.setDataRichiesta(LocalDate.now());
        this.prenotazioneRepository.save(found);
        return found;
    }
}
