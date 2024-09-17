package fragnito.U5W3D2.repositories;

import fragnito.U5W3D2.entities.Dipendente;
import fragnito.U5W3D2.entities.Prenotazione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
    @Query("SELECT p FROM Prenotazione p WHERE p.dipendente.id = :id AND p.viaggio.data = :data")
    List<Prenotazione> filterPrenotazioniByUserAndData(int id, LocalDate data);

    Page<Prenotazione> findByDipendente(Dipendente dipendente, Pageable pageable);
}
