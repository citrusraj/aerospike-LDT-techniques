package com.aerospike.examples.ldt;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;

public class LDTMapTest {
	public static final Logger LOG = Logger.getLogger(LDTMapTest.class);
	AerospikeClient client;
	Key key;
	LDTMap<String, Long> subject;

	@Before
	public void setUp() throws Exception {
		client = new AerospikeClient("localhost", 3000);
		key = new Key("test", "demo", "the-map-001");
		client.delete(null, key);
		subject = new LDTMap<String, Long>(client, key, "the-map");
	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

	@Test
	public void testPut() throws Exception {
		subject.put("cows", 3);
		subject.put("sheep", 18);
		subject.put("ducks", 73);
		subject.put("mice", 36);
		subject.put("dogs", 63);
		subject.put("cats", 43);
		subject.put("birds", 23);
		int size = subject.size();
		Assert.assertEquals(7, size);
	}
	
	@Test
	public void testGet() throws Exception {
		subject.put("cows", 3);
		long result = subject.get("cows");
		Assert.assertEquals(3, result);
	}

	@Test
	public void testEntrySet() throws Exception {
		subject.put("cows", 3);
		subject.put("sheep", 18);
		subject.put("ducks", 73);
		subject.put("mice", 36);
		subject.put("dogs", 63);
		subject.put("cats", 43);
		subject.put("birds", 23);
		Set<Entry<String, Long>> set = subject.entrySet();
		Assert.assertEquals(7, set.size());
		LOG.info(set);
	}
	@Test
	public void testValues() throws Exception {
		subject.put("cows", 3);
		subject.put("sheep", 18);
		subject.put("ducks", 73);
		subject.put("mice", 36);
		subject.put("dogs", 63);
		subject.put("cats", 43);
		subject.put("birds", 23);
		Collection<Long> values = subject.values();
		Assert.assertEquals(7, values.size());
		LOG.info(values);
	}

}
