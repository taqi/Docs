package com.keebraa.docs.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.keebraa.docs.model.impl.NewState;
import com.keebraa.docs.model.stub.SomeState;
import com.keebraa.docs.model.stub.SutDocument;

/**
 * Test cases for validate basic abstract Document behavior. 
 * 
 * @author taqi
 *
 */
public class DocumentTest
{
    @Test 
    public void testCreation() throws Exception
    {
        Document sut = new SutDocument();
        assertNotNull(sut);
        assertNotNull(sut.getState());
        assertEquals(NewState.CAPTION, sut.getState().getStateCaption());
    }
    
    @Test 
    public void testSomeState() throws Exception
    {
        SutDocument sut = new SutDocument();
        assertNotNull(sut);
        sut.changeState();
        assertNotNull(sut.getState());
        assertEquals(SomeState.CAPTION, sut.getState().getStateCaption());
    }
}
