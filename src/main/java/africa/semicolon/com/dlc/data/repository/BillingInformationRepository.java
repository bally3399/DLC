package africa.semicolon.com.dlc.data.repository;

import africa.semicolon.com.dlc.data.model.BillingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingInformationRepository extends JpaRepository<BillingInformation, Long> {
}
