package com.keebraa.docs.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.keebraa.docs.exceptions.DocsRuntimeException;

public class TemplateComponentTest
{
	private TemplateComponent sut;

	@Before
	public void setUp() throws Exception
	{
		sut = new TemplateComponent();
	}

	@Test
	public void addTemplatePlaceNormalCase() throws Exception
	{
		sut.addTemplatePlace("{test}", new Object());
		sut.addTemplatePlace("{anothertest}", new Object());
		sut.addTemplatePlace("{another_test}", new Object());
		sut.addTemplatePlace("{another-test}", new Object());
		
		assertTrue(sut.containsTemplatePlace("{test}"));
		assertTrue(sut.containsTemplatePlace("{anothertest}"));
		assertTrue(sut.containsTemplatePlace("{another_test}"));
		assertTrue(sut.containsTemplatePlace("{another-test}"));
	}
	
	@Test(expected=DocsRuntimeException.class)
	public void addTemplateWithSymbolsInTheEnd() throws Exception
	{
		sut.addTemplatePlace("{test}test", new Object());
	}
	
	@Test(expected=DocsRuntimeException.class)
	public void addTemplateWithSymbolsInTheBegin() throws Exception
	{
		sut.addTemplatePlace("test{test}", new Object());
	}
	
	@Test(expected=DocsRuntimeException.class)
	public void addTemplateWithWrongSymbols() throws Exception
	{
		sut.addTemplatePlace("{te st}", new Object());
	}
	
	@Test(expected=DocsRuntimeException.class)
	public void addTemplateWithEmpty() throws Exception
	{
		sut.addTemplatePlace("{}", new Object());
	}
}
