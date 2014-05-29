package com.miracle.framework.lang.serialize.kryo;

import com.miracle.framework.exception.SystemException;

public final class KryoPoolException extends SystemException {
	
	private static final long serialVersionUID = -2992257109597526961L;
	
	public KryoPoolException(final Exception cause) {
		super(cause);
	}
}
