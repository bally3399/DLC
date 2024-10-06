package africa.semicolon.com.dlc.data.repository;

import africa.semicolon.com.dlc.data.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
}
