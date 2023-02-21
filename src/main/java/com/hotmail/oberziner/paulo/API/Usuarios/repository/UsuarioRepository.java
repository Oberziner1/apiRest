package com.hotmail.oberziner.paulo.API.Usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotmail.oberziner.paulo.API.Usuarios.entities.Usuarios;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

}