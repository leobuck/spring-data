package com.leo.springdata.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leo.springdata.model.Telefone;

@Repository
public interface ITelefoneDAO extends CrudRepository<Telefone, Long> {

}
