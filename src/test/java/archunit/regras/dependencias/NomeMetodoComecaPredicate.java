package archunit.regras.dependencias;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaMethodCall;

class NomeMetodoComecaPredicate extends DescribedPredicate<JavaMethodCall> {

	private String nomeMetodo;

	NomeMetodoComecaPredicate(String nomeMetodo) {
		super("");
		this.nomeMetodo = nomeMetodo;
	}

	@Override
	public boolean apply(JavaMethodCall input) {
		return input.getName().startsWith(nomeMetodo);
	}
	
}
