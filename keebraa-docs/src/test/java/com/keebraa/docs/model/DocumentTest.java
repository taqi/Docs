package com.keebraa.docs.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.easymock.EasyMock;
import org.easymock.IMockBuilder;
import org.junit.Test;

import com.keebraa.docs.model.impl.NewState;
import com.keebraa.docs.model.stub.SomeStateTestStub;
import com.keebraa.docs.model.stub.SutDocumentTestStub;

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
        Document sut = new SutDocumentTestStub();
        assertNotNull(sut);
        assertNotNull(sut.getState());
        assertEquals(NewState.CAPTION, sut.getState().getStateCaption());
    }
    
    @Test 
    public void testSomeState() throws Exception
    {
        SutDocumentTestStub sut = new SutDocumentTestStub();
        assertNotNull(sut);
        sut.changeState();
        assertNotNull(sut.getState());
        assertEquals(SomeStateTestStub.CAPTION, sut.getState().getStateCaption());
    }
}
