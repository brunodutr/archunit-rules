package archunit.test;

import static archunit.regras.dependencias.DependenciasConditions.naoTerConstantesRepetidas;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public class RegrasDependencias {

	@ArchTest
	static ArchRule classes_nao_devem_ter_constantes_repetidas = fields().that().areFinal().and().areStatic().and()
			.haveRawType(String.class).should(naoTerConstantesRepetidas());

	@ArchTest
	static ArchRule classes_nao_devem_ter_dependencias_impl = noClasses().should().dependOnClassesThat()
			.resideInAPackage("..impl..");

}
