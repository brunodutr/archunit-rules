package br.com.bdutra.ejb.facade.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.bdutra.ejb.facade.impl.FacadePessoa;

class FacadePessoaTest {

	private FacadePessoa facadaPessoa;
	
	@Test
	void test() throws Exception {
		Assertions.assertNotNull(facadaPessoa.testname());
	}

}
