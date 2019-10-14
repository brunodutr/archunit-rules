package archunit.test;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;

@AnalyzeClasses(packages = "..br.com.bdutra..")
public class ValidarArquitetura {

	@ArchTest
	private final ArchRules REGRAS_DDD = ArchRules.in(RegrasDDD.class);

	@ArchTest
	private final ArchRules REGRAS_DEPENDENCIA = ArchRules.in(RegrasDependencias.class);

	@ArchTest
	private final ArchRules REGRAS_EJB = ArchRules.in(RegrasEJB.class);

	@ArchTest
	private final ArchRules REGRAS_NOMECLATURA = ArchRules.in(RegrasNomeclatura.class);

	@ArchTest
	private final ArchRules REGRAS_PACOTE = ArchRules.in(RegrasPacote.class);

	@ArchTest
	private final ArchRules REGRAS_TESTE_UNITARIO = ArchRules.in(RegrasTesteUnitario.class);

}
