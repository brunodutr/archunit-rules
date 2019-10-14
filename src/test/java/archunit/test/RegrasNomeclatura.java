package archunit.test;

import static com.tngtech.archunit.core.domain.JavaModifier.ABSTRACT;
import static com.tngtech.archunit.lang.conditions.ArchConditions.haveSimpleNameStartingWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public class RegrasNomeclatura {

	@ArchTest
	static ArchRule classes_abstratas_devem_comecar_com_abstract = classes().that().areNotInterfaces().and()
			.haveModifier(ABSTRACT).should(haveSimpleNameStartingWith("Abstract"));

}
