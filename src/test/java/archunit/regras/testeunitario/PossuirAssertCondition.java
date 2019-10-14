package archunit.regras.testeunitario;

import java.text.MessageFormat;

import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

class PossuirAssertCondition extends ArchCondition<JavaCodeUnit> {

	PossuirAssertCondition() {
		super("Teste unitario deve possuir algum \"Assert\"");
	}

	@Override
	public void check(JavaCodeUnit item, ConditionEvents events) {

		if (!item.isConstructor()) {

			boolean temAssert = item.getMethodCallsFromSelf().parallelStream()
					.anyMatch(mc -> mc.getName().startsWith("assert"));

			events.add(new SimpleConditionEvent(item, temAssert, getMessage(item.getFullName())));

		}
	}

	public String getMessage(String classeDeTeste) {
		return MessageFormat.format("Necessario realizar algum \"Assert\" no Teste Unitario: \"{0}\"", classeDeTeste);
	}
};
