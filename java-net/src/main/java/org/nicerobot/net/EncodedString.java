package org.nicerobot.net;


/**
 * URLEncodedString Value
 * 
 * @author nicerobot
 * 
 */
public class EncodedString extends Value<String> {

	public EncodedString (final String value) {
		super(value);
		setEncode(true);
	}

}
