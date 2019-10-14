package archunit.regras;

import static com.tngtech.archunit.core.domain.JavaModifier.ABSTRACT;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;

public class ClassesPredicates {

	public static final DescribedPredicate<JavaClass> abstractClass() {

		return new DescribedPredicate<JavaClass>("interfaces") {

			@Override
			public boolean apply(JavaClass javaClass) {
				return javaClass.getModifiers().contains(ABSTRACT);
			}

		};
	}

}
