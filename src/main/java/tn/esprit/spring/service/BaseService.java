package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entity.BaseEntity;
import tn.esprit.spring.repository.BaseRepository;

import java.util.List;
import java.util.Optional;


public abstract class BaseService<T extends BaseEntity, ID> implements IBaseService<T, ID> {
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private BaseRepository<T, ID> repository;

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }


    @Override
    public List<T> findAllByOwner(ID id) {
        return repository.findAllByOwner(id);
    }
}
