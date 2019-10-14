package archunit.regras.testeunitario;

import static java.text.MessageFormat.format;

import java.util.function.Function;
import java.util.stream.Stream;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

class PossuiTesteUnitarioCondition extends ArchCondition<JavaClass> {

	private String message = "Class {0} has unit tests on correct package structure";

	PossuiTesteUnitarioCondition(String description, Object... args) {
		super(description);
	}

	@Override
	public void check(JavaClass javaClass, ConditionEvents events) {
		events.add(new SimpleConditionEvent(javaClass, verify(javaClass), getMessage(javaClass)));
	}

	private String getMessage(JavaClass javaClass) {
		return format(this.message, javaClass.getFullName());
	}

	private boolean verify(JavaClass actualJavaClass) {

		Stream<JavaClass> classes = actualJavaClass.getPackage().getAllClasses().stream();

		if (isTestClass(actualJavaClass)) {

			boolean correctPackage = classes.anyMatch(otherJavaClass -> {
				return compareClasses(actualJavaClass, otherJavaClass, s -> s.substring(0, s.length() - 4));
			});

			if (!correctPackage) {
				this.message = "Unit test {0} is in wrong package structure";
			}

			return correctPackage;

		} else {

			boolean hasUnitTest = classes.anyMatch(otherJavaClass -> {
				return compareClasses(actualJavaClass, otherJavaClass, s -> s.concat("Test"));
			});

			if (!hasUnitTest) {
				this.message = "Class {0} has no unit tests";
			}

			return hasUnitTest;
		}

	}

	private boolean compareClasses(JavaClass actualJavaClass, JavaClass otherJavaClass,
			Function<String, String> function) {

		String result = function.apply(actualJavaClass.getFullName());

		return otherJavaClass.getFullName().equals(result);
	}

	private boolean isTestClass(JavaClass javaClass) {
		return javaClass.getSimpleName().endsWith("Test");
	}

}
