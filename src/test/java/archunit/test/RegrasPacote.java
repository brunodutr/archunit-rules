package archunit.test;

import static archunit.regras.ClassesPredicates.abstractClass;
import static com.tngtech.archunit.core.domain.JavaModifier.ABSTRACT;
import static com.tngtech.archunit.lang.conditions.ArchConditions.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public class RegrasPacote {

	@ArchTest
	static ArchRule classes_que_implementa_uma_interface_devem_estar_em_pacote_impl = classes().that()
			.doNotHaveModifier(ABSTRACT).and().areAssignableTo(abstractClass()).should(resideInAPackage("..impl.."));

}
