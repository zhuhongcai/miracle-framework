package com.miracle.framework.lang.serialize.kryo.asserter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import com.miracle.framework.lang.serialize.kryo.KryoSerialization;
import com.miracle.framework.lang.serialize.kryo.KyroFactory;

public final class KyroSerializationAsserter {
	
	private KyroSerializationAsserter() { }
	
	public static void assertEncodeAndDecode(Object actual) throws IOException {
		assertEquals(getDecodedObject(actual), actual);
	}
	
	public static void assertEncodeAndDecodeArray(Object[] actual) throws IOException {
		assertArrayEquals((Object[]) getDecodedObject(actual), actual);
	}
	
	public static void assertEncodeAndDecodeCollection(Collection<?> actual) throws IOException {
		Object[] decodedArray = ((Collection<?>) getDecodedObject(actual)).toArray();
		assertArrayEquals(decodedArray, actual.toArray());
	}
	
	public static void assertEncodeAndDecodeMap(Map<?, ?> actual) throws IOException {
		Map<?, ?> decodedMap = ((Map<?, ?>) getDecodedObject(actual));
		Object[] decodedArray = decodedMap.keySet().toArray();
		assertArrayEquals(decodedArray, actual.keySet().toArray());
		for (Entry<?, ?> each : decodedMap.entrySet()) {
			assertEquals(each.getValue(), actual.get(each.getKey()));
		}
	}
	
	private static Object getDecodedObject(Object actual) throws IOException {
		KryoSerialization kryoSerialization = new KryoSerialization(new KyroFactory());
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		kryoSerialization.serialize(byteArrayOutputStream, actual);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		return kryoSerialization.deserialize(byteArrayInputStream);
	}
}
