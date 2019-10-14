package archunit.test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegrasAcessos extends BaseArchunit {

	@Test
	@DisplayName("Regra - Acesso as camadas da aplicacao devem ser respeitados")
	void layered_architecture() {

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
}
