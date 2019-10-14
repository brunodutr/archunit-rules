package archunit.test;

import static archunit.regras.testeunitario.TesteUnitarioConditions.chamarClasseTestada;
import static archunit.regras.testeunitario.TesteUnitarioConditions.naoPossuirSomenteAssertNotNull;
import static archunit.regras.testeunitario.TesteUnitarioConditions.possuirAssert;
import static archunit.regras.testeunitario.TesteUnitarioConditions.possuirTesteUnitario;
import static com.tngtech.archunit.core.domain.JavaModifier.ABSTRACT;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.codeUnits;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.elements.GivenCodeUnitsConjunction;

public class RegrasTesteUnitario {

	@ArchTest
	static ArchRule classes_devem_ter_teste_unitario = classes().that().doNotHaveModifier(ABSTRACT)
			.should(possuirTesteUnitario());

	@ArchTest
	static ArchRule testes_deve_chamar_classe_testada = unitTests().should(chamarClasseTestada());

	@ArchTest
	static ArchRule testes_devem_possuir_assert = unitTests().should(possuirAssert());

	@ArchTest
	static ArchRule testes_devem_nao_possuir_somente_assertNotNull = unitTests()
			.should(naoPossuirSomenteAssertNotNull());

	private static GivenCodeUnitsConjunction<JavaCodeUnit> unitTests() {

		return codeUnits().that().areAnnotatedWith(Test.class).and().areDeclaredInClassesThat()
				.haveNameMatching(".*Test");
	}

}
