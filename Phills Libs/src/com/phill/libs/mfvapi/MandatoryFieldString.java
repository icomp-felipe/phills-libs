package com.phill.libs.mfvapi;

/** Contains the validation method scope to be run by the {@link MandatoryFieldsManager}.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.5, 22/SEP/2020 */
@FunctionalInterface
public interface MandatoryFieldString {
	
	/** Mandatory field validation method.
	 *  Here you must implement a piece of code that returns 'true' if the field is valid.
	 *  For example: lets suppose we have a textfield and, to be valid, it must not be empty,
	 *  so the validate method goes like this:<br><br>
	 *  MandatoryFieldValidator validator = () -> !textField.isEmpty(); */
	public String getErrorString();

}
