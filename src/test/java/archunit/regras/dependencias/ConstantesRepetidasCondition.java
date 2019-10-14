package archunit.regras.dependencias;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

class ConstantesRepetidasCondition extends ArchCondition<JavaField> {

	Map<String, JavaClass> valores = new HashMap<>();
	
	ConstantesRepetidasCondition() {
		super("Constantes nao podem ser repetidas");
	}

	@Override
	public void check(JavaField item, ConditionEvents events) {
		try {
			String fieldName = item.getName();

			JavaClass javaClass = item.getOwner();
			String fullName = javaClass.getFullName();
			Class<?> clazzTarget = Class.forName(fullName);

			Field declaredField = clazzTarget.getDeclaredField(fieldName);
			boolean accessible = declaredField.isAccessible();

			if (!accessible) {
				declaredField.setAccessible(true);
			}

			String valor = (String) declaredField.get(null);

			if (!accessible) {
				declaredField.setAccessible(false);
			}

			if (valores.containsKey(valor)) {
				JavaClass otherJavaClass = valores.get(valor);
				String mensagem = obterMensagem(javaClass, valor, otherJavaClass);
				events.add(new SimpleConditionEvent(javaClass, false, mensagem));
			} else {
				valores.put(valor, javaClass);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String obterMensagem(JavaClass javaClass, String valor, JavaClass otherJavaClass) {
		return MessageFormat.format(
				"Constante com valor \"{0}\" repetida nas classes \"{1}\" e \"{2}\", isole o valor em outra classe",
				valor, javaClass.getFullName(), otherJavaClass.getFullName());
	}

}
