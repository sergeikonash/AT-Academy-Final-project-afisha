package app.sergeikonash.events_service.dao.api;

import app.sergeikonash.events_service.dao.entity.Concert;
import app.sergeikonash.events_service.dto.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface IConcertDao extends JpaRepository<Concert, UUID> {

//    List<Concert> findAll(Type type);
//    Page<Concert> findAll(Type type, Pageable pageable);
}
