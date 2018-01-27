package at.fhv.mail_verify.generator.java.utils;

import java.util.List;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

/**
 * Java services for Stereotype operations
 * 
 * @author Michael Sieber
 *
 */
public class StereotypeService {

	/**
	 * Check if an element has a specific stereotype
	 * 
	 * @param element
	 *            The element to check
	 * @param stereotypeName
	 *            The stereotype to match
	 * @return True if the class has the given stereotype
	 */
	public boolean hasStereotype(Element element, String stereotypeName) {
		List<Stereotype> stereotypes = element.getAppliedStereotypes();
		for (Stereotype stereotype : stereotypes) {
			if (stereotype.getName().equals(stereotypeName)) {
				return true;
			}
		}
		return false;
	}
}
