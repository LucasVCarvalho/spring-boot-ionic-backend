package com.licascarvalho.cursospringhiber.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.licascarvalho.cursospringhiber.domain.Categoria;
import com.licascarvalho.cursospringhiber.domain.Produto;
import com.licascarvalho.cursospringhiber.repositories.CategoriaRepository;
import com.licascarvalho.cursospringhiber.repositories.ProdutoRepository;
import com.licascarvalho.cursospringhiber.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	public ProdutoRepository repo;
	
	@Autowired
	public CategoriaRepository categoriaRepo;
	
	public Produto find(Integer id){
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}
}
