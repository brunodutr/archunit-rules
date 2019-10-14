package archunit.regras.testeunitario;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.lang.ArchCondition;

public class TesteUnitarioConditions {

	public static ArchCondition<JavaClass> possuirTesteUnitario() {
		return new PossuiTesteUnitarioCondition("ter teste unitario na mesma estrutura de pacote");
	}

	public static ArchCondition<JavaCodeUnit> chamarClasseTestada() {
		return new ChamarClasseTestadaCondition();
	}

	public static ArchCondition<JavaCodeUnit> naoPossuirSomenteAssertNotNull() {
		return new NaoPossuirSomenteAssertNotNullCondition();
	}

	public static ArchCondition<JavaCodeUnit> possuirAssert() {
		return new PossuirAssertCondition();
	}
}
