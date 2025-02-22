package com.phill.libs.mfvapi;

/** MFV API exception available to be thrown.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.5, 22/SEP/2020 */
public class MandatoryFieldUnsatisfiedException extends Exception {

	private static final long serialVersionUID = -6798511668473411372L;

	/** Constructor simply redirecting the given <code>message</code> to {@link Exception} superclass. */
	public MandatoryFieldUnsatisfiedException(final String message) {
		super(message);
	}

}
