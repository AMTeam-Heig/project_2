package ch.heigvd.amt.stackovergoat.domain;

import ch.heigvd.amt.stackovergoat.infrastructure.persistence.exception.IntegrityConstraintViolationException;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<ENTITY extends IEntity, ID extends Id> {
    public void save(ENTITY entity) throws IntegrityConstraintViolationException;
    public void remove(ID id);
    public int  getSize();
    public Optional<ENTITY> findById(ID id);
    public Collection<ENTITY> findAll();
}
