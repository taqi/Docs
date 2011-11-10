package com.keebraa.docs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TextComponentTest
{
	@Test
    public void testAddTextLineToEmptyText() throws Exception
    {
        TextComponent sut = new TextComponent();

        assertEquals("", sut.getText());

        sut.addTextLine("some text");
        sut.addTextLine("some another text");

        String expected = "some text" + System.getProperty("line.separator") + "some another text";
        assertNotNull(sut.getText());
        assertEquals(expected, sut.getText());
    }

    @Test
    public void testAddTextLineToFilledText() throws Exception
    {
    	TextComponent sut = new TextComponent();

        assertEquals("", sut.getText());
        sut.setText("filled");
        sut.addTextLine("some text");
        sut.addTextLine("some another text");

        String expected = "filled" + System.getProperty("line.separator") + "some text"
            + System.getProperty("line.separator") + "some another text";
        assertNotNull(sut.getText());
        assertEquals(expected, sut.getText());
    }

    @Test
    public void testAppendText() throws Exception
    {
    	TextComponent sut = new TextComponent();

        assertEquals("", sut.getText());

        sut.setText("some text");
        sut.addText("some another text");

        assertNotNull(sut.getText());
        assertEquals("some textsome another text", sut.getText());
    }

    @Test
    public void testSetText() throws Exception
    {

    	TextComponent sut = new TextComponent();

        assertEquals("", sut.getText());

        sut.setText("some text");

        assertNotNull(sut.getText());
        assertEquals("some text", sut.getText());
    }

}
