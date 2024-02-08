package com.licascarvalho.cursospringhiber.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.licascarvalho.cursospringhiber.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	//como não estamos utilizando webservice pra gerar boleto criamos essa função para fazer a data do 
	//boleto ser até de 1 semana para pagar
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
