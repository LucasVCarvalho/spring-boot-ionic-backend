package com.licascarvalho.cursospringhiber.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.licascarvalho.cursospringhiber.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	Optional<Cidade> findById(Integer id);
	

}
