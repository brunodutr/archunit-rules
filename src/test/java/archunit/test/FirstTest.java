package archunit.test;

import static archunit.rule.ClassRules.ABSTRACT_CLASS;
import static archunit.rule.VerificationTestRule.haveUnitTest;
import static com.tngtech.archunit.core.domain.JavaModifier.ABSTRACT;
import static com.tngtech.archunit.lang.conditions.ArchConditions.haveSimpleNameStartingWith;
import static com.tngtech.archunit.lang.conditions.ArchConditions.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static java.text.MessageFormat.format;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

public class FirstTest {

	private JavaClasses javaClasses;
	private ArchRule rule;

	@BeforeEach
	public void setup() {
		javaClasses = new ClassFileImporter().importPackages("..br.com.bdutra..");
	}

	@Test
	@DisplayName("Regra - Acesso as camadas da aplicacao devem ser respeitados")
	public void layered_architecture() {

		rule = layeredArchitecture()
				.layer("Controller").definedBy("br.com.bdutra.controller")
				.layer("ControllerImpl").definedBy("br.com.bdutra.controller.impl")
				.layer("Facade").definedBy("br.com.bdutra.facade")
				.layer("FacadeImpl").definedBy("br.com.bdutra.facade.impl")

				.whereLayer("Controller").mayOnlyBeAccessedByLayers("ControllerImpl")
				.whereLayer("ControllerImpl").mayNotBeAccessedByAnyLayer()
				.whereLayer("Facade").mayOnlyBeAccessedByLayers("ControllerImpl", "FacadeImpl")
				.whereLayer("FacadeImpl").mayNotBeAccessedByAnyLayer();
	}

	@Test
	@DisplayName("Regra - Classes abstratas devem começar com \"Abstract\"")
	public void abstracts_should_be_prefixed() {

		rule = classes().that().areNotInterfaces().and().haveModifier(ABSTRACT)
				.should(haveSimpleNameStartingWith("Abstract"));
	}

	public static final ArchCondition<JavaClass> NOT_HAVE_IMPL_CLASS_FIELDS = new ArchCondition<JavaClass>("not have impl class fields") {

		@Override
		public void check(JavaClass item, ConditionEvents events) {
			item.getFields().stream().forEach(field -> {

				JavaClass javaClass = field.getRawType();
				
				String packageName = javaClass.getPackageName();
				String message = format("Field \"{0}\" in {1} is a implementation, please use Interface or Abstract",
						field.getName(), item.getFullName());

				events.add(new SimpleConditionEvent(item, !packageName.contains("impl"), message));

			});
		}

	};

	@Test
	@DisplayName("Regra - Classes não devem ter dependencias que residam em pacotes \"impl\"")
	public void classes_not_dependent_to_impl() {

		rule = classes().should(NOT_HAVE_IMPL_CLASS_FIELDS);
	}

	@Test
	@DisplayName("Regra - Classes devem ter Teste Unitario respectivo na mesma estrutura de pacote")
	public void classes_should_have_unit_test() {

		rule = classes().that().doNotHaveModifier(ABSTRACT).should(haveUnitTest());

	}

	@Test
	@DisplayName("Regra - Classes que não são abstratas ou interfaces e que implementam ou extendem de outra devem estar em pacote nomeado \"impl\"")
	public void classe_that_implement_interfaces_shoud_be_in_package_impl() {

		rule = classes().that().doNotHaveModifier(ABSTRACT).and().areAssignableTo(ABSTRACT_CLASS)
				.should(resideInAPackage("..impl.."));

	}

	@AfterEach
	public void after() {
		rule.check(javaClasses);
	}
}
