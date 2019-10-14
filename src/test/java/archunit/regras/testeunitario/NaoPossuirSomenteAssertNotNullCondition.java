package archunit.regras.testeunitario;

import java.text.MessageFormat;
import java.util.Set;
import java.util.stream.Collectors;

import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

class NaoPossuirSomenteAssertNotNullCondition extends ArchCondition<JavaCodeUnit> {

	NaoPossuirSomenteAssertNotNullCondition() {
		super("Teste Unitario nao podem ter somente AssertNotNull");
	}

	@Override
	public void check(JavaCodeUnit item, ConditionEvents events) {

		if (!item.isConstructor()) {

			Set<String> asserts = item.getMethodCallsFromSelf().parallelStream()
					.filter(mc -> mc.getName().startsWith("assert")).map(mc -> mc.getName())
					.collect(Collectors.toSet());

			if (!asserts.isEmpty() && asserts.size() == 1 && asserts.contains("assertNotNull")) {
				events.add(new SimpleConditionEvent(item, false, getMessage(item.getFullName())));
			}

		}

	}

	public String getMessage(String classeDeTeste) {
		return MessageFormat.format(
				"Necessario realizar \"Asserts\" que nao sejam somente \"AssertNotNull\" no Teste Unitario: \"{0}\"",
				classeDeTeste);
	}

}