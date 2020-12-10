package ch.heigvd.amt.stackovergoat.domain;

public interface IEntity<ENTITY extends IEntity, ID extends Id> {
    ID getId();
    ENTITY deepClone();
}
