package app.sergeikonash.classifier_service.service.api;

import app.sergeikonash.classifier_service.dto.PageDto;

public interface IService<T1, T2, T3> {

    T1 create(T1 t);

    PageDto<T3> getAll(Integer page, Integer size);
}
