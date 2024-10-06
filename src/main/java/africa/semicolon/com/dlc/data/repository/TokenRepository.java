package africa.semicolon.com.dlc.data.repository;

import africa.semicolon.com.dlc.data.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional <Token> findByOwnerEmail(String lowerCase);
}
