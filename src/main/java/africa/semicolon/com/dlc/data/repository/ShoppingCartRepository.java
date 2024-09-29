package africa.semicolon.com.dlc.data.repository;

import africa.semicolon.com.dlc.data.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
