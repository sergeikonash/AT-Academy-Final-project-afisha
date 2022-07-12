package app.sergeikonash.events_service.dao.api;

import app.sergeikonash.events_service.dao.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface IConcertDao extends JpaRepository<Concert, UUID> {

    Page<Concert> findAll(Pageable pageable);
}
