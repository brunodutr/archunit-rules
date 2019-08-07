package archunit.rule;

import static com.tngtech.archunit.core.domain.JavaModifier.ABSTRACT;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;

public class ClassRules {

	public static final DescribedPredicate<JavaClass> FIELDS_NOT_IN_IMPL = new DescribedPredicate<JavaClass>("teste") {

		@Override
		public boolean apply(JavaClass javaClass) {
			return false;
		}
	};

	public static final DescribedPredicate<JavaClass> ABSTRACT_CLASS = new DescribedPredicate<JavaClass>("interfaces") {

		@Override
		public boolean apply(JavaClass javaClass) {
			return javaClass.getModifiers().contains(ABSTRACT);
		}
	};

	public static final DescribedPredicate<JavaClass> INTERFACE_CLASS = new DescribedPredicate<JavaClass>(
			"interfaces") {

		@Override
		public boolean apply(JavaClass javaClass) {
			return javaClass.isInterface();
		}
	};

}
