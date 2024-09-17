package fragnito.U5W3D2.repositories;

import fragnito.U5W3D2.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Integer> {
    Dipendente findByEmail(String email);

    boolean existsByEmail(String email);
}
