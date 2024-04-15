package valerio.U6W3D1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valerio.U6W3D1.entity.Dipendente;

import java.util.Optional;

public interface DipendenteDAO extends JpaRepository<Dipendente, Integer> {
    boolean existsByEmail(String email);

    Optional<Dipendente> findByEmail(String email);
}
