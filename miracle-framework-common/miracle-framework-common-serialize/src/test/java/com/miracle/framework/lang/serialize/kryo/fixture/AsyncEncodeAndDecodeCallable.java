package com.miracle.framework.lang.serialize.kryo.fixture;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Callable;

import com.miracle.framework.lang.serialize.kryo.KryoSerialization;
import com.miracle.framework.lang.serialize.kryo.KyroFactory;

public final class AsyncEncodeAndDecodeCallable implements Callable<Integer> {
	
	private final KyroFactory kyroFactory;
	private final int value;
	
	public AsyncEncodeAndDecodeCallable(final KyroFactory kyroFactory, final int value) {
		this.kyroFactory = kyroFactory;
		this.value = value;
	}
	
	@Override
	public Integer call() throws Exception {
		KryoSerialization kryoSerialization = new KryoSerialization(kyroFactory);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		kryoSerialization.serialize(byteArrayOutputStream, value);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		return (Integer) kryoSerialization.deserialize(byteArrayInputStream);
	}
}
