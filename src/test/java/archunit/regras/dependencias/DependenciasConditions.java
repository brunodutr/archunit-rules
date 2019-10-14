package archunit.regras.dependencias;

import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.lang.ArchCondition;

public class DependenciasConditions {

	public static ArchCondition<JavaField> naoTerConstantesRepetidas() {
		return new ConstantesRepetidasCondition();
	}

}
