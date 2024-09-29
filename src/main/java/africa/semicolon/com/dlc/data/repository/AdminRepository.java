package africa.semicolon.com.dlc.data.repository;

import africa.semicolon.com.dlc.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
