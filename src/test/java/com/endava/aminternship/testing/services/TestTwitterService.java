package com.endava.aminternship.testing.services;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;


import com.endava.aminternship.testing.configuration.Registry;

public class TestTwitterService {
final ApplicationContext appContext = Registry.getContext("test-context.xml");
	
	@Test
	public void testFunction() {
		assertTrue(false);
	}
}
