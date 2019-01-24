package chinv.nal.kafkademo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ControllerTests
{
	@Test
	public void testCreateJsonMsgWithTwoItems() {
		Map<String, String> map = new HashMap<>();
		map.put("k1", "value1");
		map.put("k2", "2");
		String result = KafkaApiController.createJsonMsg(map);
		assertEquals("{\"k1\":\"value1\",\"k2\":\"2\"}", result);
	}

	@Test
	public void testCreateJsonMsgWithOneItem() {
		Map<String, String> map = new HashMap<>();
		map.put("k1", "value1");
		String result = KafkaApiController.createJsonMsg(map);
		assertEquals("{\"k1\":\"value1\"}", result);
	}

	@Test
	public void testCreateJsonMsgWithZeroItems() {
		Map<String, String> map = new HashMap<>();
		String result = KafkaApiController.createJsonMsg(map);
		assertEquals("{}", result);
	}

}
