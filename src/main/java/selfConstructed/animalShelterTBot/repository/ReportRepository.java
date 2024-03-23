package selfConstructed.animalShelterTBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.animalShelterTBot.model.Report;

public interface ReportRepository extends JpaRepository<Report, String>{
}