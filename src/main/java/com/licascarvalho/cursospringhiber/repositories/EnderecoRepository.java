package com.licascarvalho.cursospringhiber.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.licascarvalho.cursospringhiber.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	Optional<Endereco> findById(Integer id);
	

}
