package com.miracle.framework.encoding;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public final class EncodingTest {
	
	@Test
	public void encodingNullVaule() {
		assertNull(Encoding.encoding(null, "", ""));
	}
	
	@Test(expected = InvalidEncodingException.class)
	public void encodingFailureWhenInvalidCharset() {
		Encoding.encoding("", "invalidCharset", "invalidCharset");
	}
	
	@Test
	public void encodingSuccess() {
		assertThat(Encoding.encoding("test", "ISO-8859-1", "UTF-8"), is("test"));
	}
	
	@Test
	public void encodingIso8859ToUtf8() {
		assertThat(Encoding.encodingIso8859ToUtf8("test"), is("test"));
	}
}
