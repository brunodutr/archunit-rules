package archunit.regras.dependencias;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaMethodCall;

public class DependenciasPredicates {

	public static DescribedPredicate<JavaAnnotation> anotacaoDoPacote(String nomePacote) {
		return new NomeAnotacaoPossuiPredicate(nomePacote);
	}
	
	public static DescribedPredicate<JavaMethodCall> metodoComecaCom(String nomeMetodo) {
		return new NomeMetodoComecaPredicate(nomeMetodo);
	}
}
