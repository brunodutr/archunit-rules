package archunit.test;

import static archunit.regras.dependencias.DependenciasPredicates.metodoComecaCom;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

public class RegrasDDD {

	@ArchTest
	static ArchRule metodos_setters_nao_devem_ser_acessado_diretamente = noClasses().should().callMethodWhere(metodoComecaCom("set"));

}
