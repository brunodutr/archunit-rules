package archunit.test;

import static archunit.regras.dependencias.DependenciasPredicates.anotacaoDoPacote;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.members;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public class RegrasEJB {

	private static final String JAVAX_EJB = "javax.ejb";
	private static final String EJB_PACKAGE = "..br.com.bdutra.ejb..";

	@ArchTest
	static ArchRule classes_nao_podem_usar_EJB = classes().that().resideOutsideOfPackage(EJB_PACKAGE).should()
			.notBeAnnotatedWith(anotacaoDoPacote(JAVAX_EJB));

	@ArchTest
	static ArchRule metodos_e_atributos_nao_podem_usar_EJB = members().that().areDeclaredInClassesThat()
			.resideOutsideOfPackage(EJB_PACKAGE).should().notBeAnnotatedWith(anotacaoDoPacote(JAVAX_EJB));

}
