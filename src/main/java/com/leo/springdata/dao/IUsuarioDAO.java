package com.leo.springdata.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leo.springdata.model.Usuario;

@Repository
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

	@Lock(LockModeType.READ)
	@Query(value = "SELECT u FROM Usuario u WHERE u.login LIKE %?1%")
	public List<Usuario> buscarPorNome(String login);
	
	@Lock(LockModeType.READ)
	@Query(value = "SELECT u FROM Usuario u WHERE u.login = :login")
	public Usuario buscarPorNomeParam(@Param("login") String login);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Usuario u WHERE u.login = ?1")
	public void deletePorNome(String login);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario u SET u.email = ?1 WHERE u.login = ?2")
	public void updatePorNome(String email, String nome);
}
