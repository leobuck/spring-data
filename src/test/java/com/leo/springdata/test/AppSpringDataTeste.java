package com.leo.springdata.test;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leo.springdata.dao.ITelefoneDAO;
import com.leo.springdata.dao.IUsuarioDAO;
import com.leo.springdata.model.Telefone;
import com.leo.springdata.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTeste {

	@Autowired
	private ITelefoneDAO telefoneDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Test
	public void testeInsert() {
		Usuario usuario = new Usuario();
		usuario.setLogin("java");
		usuario.setSenha("Java123");
		usuario.setEmail("java@email.com");
		usuario.setIdade(22);
		
		usuarioDAO.save(usuario);
	}
	
	@Test
	public void testeConsulta() {
		Optional<Usuario> usuario = usuarioDAO.findById(3L);
		System.out.println(usuario.get().toString());
		for (Telefone telefone : usuario.get().getTelefones()) {
			System.out.println(telefone.toString());
		}
	}
	
	@Test
	public void testeConsultaTodos() {
		Iterable<Usuario> lista = usuarioDAO.findAll();
		
		for (Usuario usuario : lista) {
			System.out.println("> " + usuario.toString());
		}
	}
	
	@Test
	public void testeUpdate() {
		Optional<Usuario> usuarioOpt = usuarioDAO.findById(10L);
		
		Usuario usuario = usuarioOpt.get();
		
		usuario.setLogin("leo");
		
		usuarioDAO.save(usuario);
	}
	
	@Test
	public void testeDelete() {
		usuarioDAO.deleteById(2L);
		
		Optional<Usuario> usuarioOpt = usuarioDAO.findById(3L);
		
		Usuario usuario = usuarioOpt.get();
		
		usuarioDAO.delete(usuario);
	}
	
	@Test
	public void testeBuscaNome() {
		List<Usuario> lista = usuarioDAO.buscarPorNome("java");
		
		for (Usuario usuario : lista) {
			System.out.println("# " + usuario.toString());
		}
	}
	
	@Test
	public void testeBuscaNomeParam() {
		Usuario usuario = usuarioDAO.buscarPorNomeParam("leo");
		
		System.out.println("@ " + usuario.toString());
	}
	
	@Test
	public void testeDeleteNomeParam() {
		usuarioDAO.deletePorNome("leo");
	}
	
	@Test
	public void testeUpdateNomeParam() {
		usuarioDAO.updatePorNome("leo@email.com", "leo");
	}
	
	@Test
	public void testeInsertTelefone() {
		Telefone telefone = new Telefone();
		telefone.setTipo("Celular");
		telefone.setNumero("9999999999");
		
		Optional<Usuario> usuario = usuarioDAO.findById(3L);
		telefone.setUsuario(usuario.get());
		
		telefoneDAO.save(telefone);
	}
	
}
