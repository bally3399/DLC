package africa.semicolon.com.dlc.data.repository;

import africa.semicolon.com.dlc.data.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
