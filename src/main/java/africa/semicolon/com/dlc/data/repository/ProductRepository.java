package africa.semicolon.com.dlc.data.repository;

import africa.semicolon.com.dlc.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
