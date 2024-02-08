package com.licascarvalho.cursospringhiber.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.licascarvalho.cursospringhiber.domain.Cliente;
import com.licascarvalho.cursospringhiber.domain.enums.TipoCliente;
import com.licascarvalho.cursospringhiber.dto.ClienteNewDTO;
import com.licascarvalho.cursospringhiber.repositories.ClienteRepository;
import com.licascarvalho.cursospringhiber.resources.exception.FieldMessage;
import com.licascarvalho.cursospringhiber.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	//O initialize funciona semelhante ao Awake da unity, 
	//o que eu codificar dentro vai fazer no momento que for instanciado o objeto/função
	@Override
	public void initialize(ClienteInsert ann) {
	}

	//esse IsValid afeta o @Valid presente no post/put do REST na hora de validar os campos
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}