package at.fhv.mail_verify.generator.java.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.TypedElement;

/**
 * Support class for getting the correct java type from the model
 * 
 * @author Michael Sieber
 *
 */
public class JavaTypeService {
	private static final String TYPE_BOOLEAN = "UMLPrimitiveTypes.library.uml#Boolean";
	private static final String TYPE_INTEGER = "UMLPrimitiveTypes.library.uml#Integer";
	private static final String TYPE_REAL = "UMLPrimitiveTypes.library.uml#Real";
	private static final String TYPE_STRING = "UMLPrimitiveTypes.library.uml#String";
	private static final String TYPE_UNLIMITED_NATURAL = "UMLPrimitiveTypes.library.uml#UnlimitedNatural";
	private static final String TYPE_LONG = "JavaPrimitiveTypes.library.uml#long";
	private static final String TYPE_FLOAT = "JavaPrimitiveTypes.library.uml#float";

	/**
	 * Get the Java data type from a generic data type element
	 * 
	 * @param typedElement
	 *            The typed element
	 * @return The Java type of the given data type
	 */
	public String getJavaType(TypedElement typedElement) {
		if (typedElement.getType().eIsProxy()) {
			return resolveType(typedElement.getType().toString());
		}

		return generateFullPackagePath(typedElement) + typedElement.getType().getName();
	}

	/**
	 * Resolve the type from the given URI
	 * 
	 * @param uri
	 *            The URI for which the type should be loaded
	 * @return The type of the given URI or an empty string if it cannot be resolved
	 */
	private String resolveType(String uri) {
		if (uri.contains(TYPE_BOOLEAN)) {
			return "boolean";
		} else if (uri.contains(TYPE_INTEGER)) {
			return "int";
		} else if (uri.contains(TYPE_REAL)) {
			return "float";
		} else if (uri.contains(TYPE_STRING)) {
			return "String";
		} else if (uri.contains(TYPE_UNLIMITED_NATURAL)) {
			return "int";
		} else if (uri.contains(TYPE_LONG))	{
			return "long";
		} else if (uri.contains(TYPE_FLOAT))	{
			return "float";
		}

		return "";
	}

	/**
	 * Generate the full package path for the given type
	 * 
	 * @param typedElement
	 *            The type for which the full package path should be resolved
	 * @return The full package path
	 */
	private String generateFullPackagePath(TypedElement typedElement) {
		String modelName = typedElement.getModel().getName();

		List<String> packageNames = new ArrayList<String>();
		org.eclipse.uml2.uml.Package p = typedElement.getType().getNearestPackage();
		while (p != null) {
			// do not add the name of the model to the package names
			if (!modelName.equalsIgnoreCase(p.getName())) {
				packageNames.add(p.getName());
			}
			p = (Package) p.eContainer();
		}

		Collections.reverse(packageNames);

		StringBuilder packageNameBuilder = new StringBuilder();
		for (String packageName : packageNames) {
			packageNameBuilder.append(packageName).append(".");
		}

		return packageNameBuilder.toString();
	}
}
