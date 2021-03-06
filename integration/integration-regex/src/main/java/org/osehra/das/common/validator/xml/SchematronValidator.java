package org.osehra.das.common.validator.xml;

import org.osehra.das.common.validation.NullChecker;
import org.osehra.das.common.validator.Validator;
import org.osehra.das.common.validator.ValidatorException;
import org.osehra.das.common.xsl.ResolvableXSLTransformer;

import java.io.StringWriter;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * The Schematron validator.
 * 
 * @author Keith Roberts
 */
public class SchematronValidator extends ResolvableXSLTransformer implements
		Validator<Document> {

	/**
	 * Validate the source document using Schematron.
	 * 
	 * @param sourceMessage
	 *            the input message
	 * @return the boolean true if success, false otherwise
	 * @throws ValidatorException
	 *             an exception occured when validating using Schematron
	 */
	@Override
	public final boolean validate(final Document sourceMessage)
			throws ValidatorException {

		if (NullChecker.isEmpty(sourceMessage)) {
			throw new RuntimeException("Source message cannot be empty!");
		}
		final StringWriter stringWriter = new StringWriter();

		try {
			super.transform(new DOMSource(sourceMessage), new StreamResult(
					stringWriter));
			final String result = stringWriter.toString();
			if ((result != null) && (result.trim().length() > 0)) {
				throw new ValidatorException(result.trim());
			}

		} catch (final javax.xml.transform.TransformerException ex) {
			throw new ValidatorException(ex);
		}
		return true;
	}
}
