package com.licascarvalho.cursospringhiber.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.licascarvalho.cursospringhiber.domain.Categoria;
import com.licascarvalho.cursospringhiber.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Optional<Produto> findById(Integer id);
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")		
	Page<Produto> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	//Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
	/*o @Query e findDistinctByNomeContainingAndCategoriasIn conseguem fazer a mesma função de 
	query para pesquisa de banco de dados JPQL
	doc pra fazer query no nome https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html*/
}
