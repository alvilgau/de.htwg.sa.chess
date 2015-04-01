package de.htwg.util.observer;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EventTest {

	Event event;
	
	class TestEvent extends Event {
		public TestEvent() {
		}
	}
	
	@Before
	public void setUp() {
		event = new TestEvent();
	}
	
	@Test
	public void testEvent() {
		assertNotNull(event);
	}
	
}
