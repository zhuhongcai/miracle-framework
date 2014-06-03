package com.miracle.framework.lang.serialize.kryo;

import static com.miracle.framework.lang.serialize.kryo.asserter.KyroSerializationAsserter.assertEncodeAndDecode;
import static com.miracle.framework.lang.serialize.kryo.asserter.KyroSerializationAsserter.assertEncodeAndDecodeArray;
import static com.miracle.framework.lang.serialize.kryo.asserter.KyroSerializationAsserter.assertEncodeAndDecodeCollection;
import static com.miracle.framework.lang.serialize.kryo.asserter.KyroSerializationAsserter.assertEncodeAndDecodeMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.miracle.framework.lang.serialize.kryo.fixture.AsyncEncodeAndDecodeCallable;
import com.miracle.framework.lang.serialize.kryo.fixture.Foo;
import com.miracle.test.enums.TestEnum;

public final class KryoSerializationTest {
	
	@Test
	public void encodeAndDecodeBoolean() throws IOException {
		assertEncodeAndDecode(false);
	}
	
	@Test
	public void encodeAndDecodeByte() throws IOException {
		assertEncodeAndDecode((byte) 1);
	}
	
	@Test
	public void encodeAndDecodeChar() throws IOException {
		assertEncodeAndDecode('汉');
	}
	
	@Test
	public void encodeAndDecodeShort() throws IOException {
		assertEncodeAndDecode((short) 1);
	}
	
	@Test
	public void encodeAndDecodeInt() throws IOException {
		assertEncodeAndDecode(1);
	}
	
	@Test
	public void encodeAndDecodeLong() throws IOException {
		assertEncodeAndDecode(1L);
	}
	
	@Test
	public void encodeAndDecodeFloat() throws IOException {
		assertEncodeAndDecode(1.0f);
	}
	
	@Test
	public void encodeAndDecodeDouble() throws IOException {
		assertEncodeAndDecode(1.0d);
	}
	
	@Test
	public void encodeAndDecodeString() throws IOException {
		assertEncodeAndDecode("中文");
	}
	
	@Test
	public void encodeAndDecodeBigDecimal() throws IOException {
		assertEncodeAndDecode(new BigDecimal("100000000.01"));
	}
	
	@Test
	public void encodeAndDecodeArray() throws IOException {
		assertEncodeAndDecodeArray(new Integer[] {1, 2, 3});
		assertEncodeAndDecodeArray(new String[] {"test1", "test2"});
		assertEncodeAndDecodeArray(new Object[] {1, 2L, 3.0f, 4.0d, "5" ,true});
	}
	
	@Test
	public void encodeAndDecodeList() throws IOException {
		assertEncodeAndDecodeCollection(Collections.emptyList());
		assertEncodeAndDecodeCollection(Lists.newArrayList(1, 2));
		assertEncodeAndDecodeCollection(Lists.newLinkedList(Lists.newArrayList("2", "1")));
		assertEncodeAndDecodeCollection(Lists.newCopyOnWriteArrayList(Lists.newArrayList(new Date(), new Date(0L))));
		assertEncodeAndDecodeCollection(Arrays.asList("1", 2));
		assertEncodeAndDecodeCollection(Collections.unmodifiableList(Lists.newArrayList(1, 2)));
		assertEncodeAndDecodeCollection(Lists.newArrayList(1, 2).subList(0, 1));
		List<String> list = new ArrayList<String>(2);
		list.add("a");
		list.add("b");
		assertEncodeAndDecodeCollection(list);
	}
	
	@Test
	public void encodeAndDecodeSet() throws IOException {
		assertEncodeAndDecodeCollection(Collections.emptySet());
		assertEncodeAndDecodeCollection(Sets.newHashSet("3", "4"));
		assertEncodeAndDecodeCollection(Sets.newLinkedHashSet(Sets.newHashSet("4", "3")));
		assertEncodeAndDecodeCollection(Sets.newTreeSet(Sets.newHashSet(4, 3)));
		assertEncodeAndDecodeCollection(Sets.newCopyOnWriteArraySet(Sets.newHashSet(3, 4)));
		assertEncodeAndDecodeCollection(Sets.newEnumSet(Sets.newHashSet(TestEnum.TEST), TestEnum.class));
		Set<String> set = new TreeSet<String>();
		set.add("z");
		set.add("b");
		set.add("c");
		set.add("a");
		assertEncodeAndDecodeCollection(set);
	}
	
	@Test
	public void encodeAndDecodeMap() throws IOException {
		assertEncodeAndDecodeMap(Collections.emptyMap());
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("z", "a");
		hashMap.put("b", "c");
		hashMap.put("c", "v");
		hashMap.put("a", "f");
		assertEncodeAndDecodeMap(hashMap);
		assertEncodeAndDecodeMap(Maps.newLinkedHashMap(hashMap));
		Map<String, String>  treeMap = new TreeMap<>();
		treeMap.put("z", "a");
		treeMap.put("b", "c");
		treeMap.put("c", "v");
		treeMap.put("a", "f");
		assertEncodeAndDecodeMap(treeMap);
		Map<TestEnum, String> enumMap = new EnumMap<>(TestEnum.class);
		enumMap.put(TestEnum.TEST, "1");
		assertEncodeAndDecodeMap(enumMap);
		Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
		concurrentHashMap.put("z", "a");
		concurrentHashMap.put("b", "c");
		concurrentHashMap.put("c", "v");
		concurrentHashMap.put("a", "f");
		assertEncodeAndDecodeMap(concurrentHashMap);
	}
	
	@Test
	public void encodeAndDecodeMapInMultipleThread() throws InterruptedException, ExecutionException {
		KyroFactory kyroFactory = new KyroFactory(1, 1, -1, 10000);
		ExecutorService executorService = Executors.newFixedThreadPool(30);
		List<Future<Integer>> decodedValues = new ArrayList<>(100);
		for (int i = 0;i < 100;i++) {
			decodedValues.add(executorService.submit(new AsyncEncodeAndDecodeCallable(kyroFactory, i)));
		}
		int i = 0;
		for (Future<Integer> each : decodedValues) {
			assertThat(each.get(), is(i));
			i++;
		}
	}
	
	@Test
	public void encodeAndDecodeObjectWithoutDefaultConstructor() throws IOException {
		KryoSerialization kryoSerialization = new KryoSerialization(new KyroFactory());
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		kryoSerialization.serialize(byteArrayOutputStream, new Foo("bar"));
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		assertThat(((Foo) kryoSerialization.deserialize(byteArrayInputStream)).getBar(), is("bar"));
	}
	
	@Test(expected = KryoPoolException.class)
	public void encodeAndDecodeWhenPoolTimeout() throws IOException {
		KryoSerialization kryoSerialization = new KryoSerialization(new KyroFactory(0, 0, 10, 1000));
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		kryoSerialization.serialize(byteArrayOutputStream, new Foo("bar"));
	}
}
