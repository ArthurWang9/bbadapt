package org.osehra.das.common.validator;

import org.osehra.das.common.validation.NullChecker;

/**
 * Check whether an object is empty or not.
 * 
 * @author Julian Jewel
 */
public class EmptyValidator implements Validator<Object> {

	/**
	 * Validate whether an object is empty or not.
	 * 
	 * @param object
	 *            the input message
	 * @return true if the object is not empty and false if empty
	 * @throws ValidatorException
	 *             if an exception occurs
	 */
	@Override
	public final boolean validate(final Object object)
			throws ValidatorException {
		return NullChecker.isNotEmpty(object);
	}
}
