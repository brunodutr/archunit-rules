package archunit.regras.testeunitario;

import java.text.MessageFormat;

import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

class ChamarClasseTestadaCondition extends ArchCondition<JavaCodeUnit> {

	ChamarClasseTestadaCondition() {
		super("Teste unitario devem chamar Classe que esta sendo testada");
	}

	@Override
	public void check(JavaCodeUnit item, ConditionEvents events) {

		if (!item.isConstructor()) {
			String classeDeTeste = item.getOwner().getName();

			String classeTestada = classeDeTeste.substring(0, classeDeTeste.length() - 4);

			boolean chamaClasseDeTeste = item.getMethodCallsFromSelf().parallelStream()
					.anyMatch(mc -> mc.getTargetOwner().getName().equals(classeTestada));

			events.add(
					new SimpleConditionEvent(item, chamaClasseDeTeste, getMessage(classeTestada, item.getFullName())));

		}
	}

	public String getMessage(String classeTestada, String classeDeTeste) {
		return MessageFormat.format("Algum metodo da classe \"{0}\" precisar ser chamado no Teste Unitario: \"{1}\"",
				classeTestada, classeDeTeste);
	}
}
