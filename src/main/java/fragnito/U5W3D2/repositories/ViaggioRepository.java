package fragnito.U5W3D2.repositories;

import fragnito.U5W3D2.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Integer> {
}
