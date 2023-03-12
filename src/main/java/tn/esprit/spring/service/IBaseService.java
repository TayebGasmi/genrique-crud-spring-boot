package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T, ID> {
    void delete(ID id);

    Optional<T> findById(ID id);

    List<T> getAll();


    List<T> findAllByOwner(ID id);
}
