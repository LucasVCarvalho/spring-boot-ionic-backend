package com.licascarvalho.cursospringhiber.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.licascarvalho.cursospringhiber.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

	Optional<Pagamento> findById(Integer id);
	

}
