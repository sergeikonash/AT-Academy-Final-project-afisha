package app.sergeikonash.classifier_service.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IService<T1, T2> {

    void create(T1 t);

    Page<T2> getAll(Pageable page);

    T2 get(UUID uuid);

}
