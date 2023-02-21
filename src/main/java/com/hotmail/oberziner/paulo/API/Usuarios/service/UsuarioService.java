package com.hotmail.oberziner.paulo.API.Usuarios.service;

import java.util.Collection;
import java.util.Optional;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.hotmail.oberziner.paulo.API.Usuarios.entities.Usuarios;
import com.hotmail.oberziner.paulo.API.Usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

//	public ResponseEntity buscarPorIdDoUsuario(Long id) {
//		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
//				.orElse(ResponseEntity.notFound().build());
//	}




	public ResponseEntity<?> buscarPorIdDoUsuario(Long id) {
			Optional<Usuarios> record = this.repository.findById(id);

			if (record.orElseGet(() -> null) != null) {
				return new ResponseEntity<Usuarios>(record.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Usuário não localizado. Tente novamente!", HttpStatus.BAD_REQUEST);
			}
		}

	public ResponseEntity<?> buscarTodosUsuarios() {
		try {
			Collection<Usuarios> lista = this.repository.findAll();
			return new ResponseEntity<Collection<Usuarios>>(lista, HttpStatus.OK);
		} catch (MethodArgumentTypeMismatchException | NumberFormatException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(
					"Não foi possível encontrar os dados. Verifique se o link digitado está correto.",
					HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> salvarUsuario(Usuarios usuario) {
		try {
			return new ResponseEntity<Usuarios>(this.repository.save(usuario), HttpStatus.CREATED);
		} catch (JpaSystemException | GenericJDBCException | HttpMessageNotReadableException
				| DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(
					"Dados informados inválido! Verificar se os dados informados já foram cadastrados.",
					HttpStatus.BAD_REQUEST);
		}
	}


	public ResponseEntity<?> atualizarUsuario(long id, Usuarios usuario) {
		try {
			return (ResponseEntity<?>) repository.findById(id).map(record -> extracted(usuario, record)).orElse(ResponseEntity.badRequest()
					.body("Não foi possível atualizar o usuário. Por favor, tente novamente."));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Erro não identificado", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	private Object extracted(Usuarios usuario, Usuarios record) {
        return null;
    }

    public ResponseEntity<?> excluirUsuario(long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);

			return ResponseEntity.ok().body("Usuario do ID " + id + " foi deletado com sucesso!");
		}).orElse(ResponseEntity.notFound().build());
	}

}