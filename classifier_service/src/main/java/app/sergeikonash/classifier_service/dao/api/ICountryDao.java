package app.sergeikonash.classifier_service.dao.api;

import app.sergeikonash.classifier_service.dao.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICountryDao extends JpaRepository<Country, UUID> {
}
