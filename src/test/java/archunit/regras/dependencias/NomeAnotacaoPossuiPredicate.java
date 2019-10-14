package archunit.regras.dependencias;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;

class NomeAnotacaoPossuiPredicate extends DescribedPredicate<JavaAnnotation> {

	private String nomePacote;
	
	NomeAnotacaoPossuiPredicate(String nomePacote) {
		super("");
		this.nomePacote = nomePacote;
	}

	@Override
	public boolean apply(JavaAnnotation javaAnnotation) {
		return javaAnnotation.getRawType().getFullName().contains(nomePacote);
	}
}
