package com.keebraa.docs.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.keebraa.docs.Core;
import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.impl.stub.SomeHandledDocumentTestStub;
import com.keebraa.docs.impl.stub.SomeHandledStateTestStub;
import com.keebraa.docs.impl.stub.SomeHandlerTestStub;
import com.keebraa.docs.model.Document;
import com.keebraa.docs.model.impl.NewState;

public class SystemDocumentLifecycleBusTest
{
    @Test
    public void testNewState() throws Exception
    {
        SomeHandlerTestStub handler = new SomeHandlerTestStub();
        Core.getInstance().getDocumentLifecycleBus().registerDocumentLifecycleHandler(handler);
        Document document = new SomeHandledDocumentTestStub();
        assertEquals(NewState.CAPTION, document.getState().getStateCaption());
        assertNotNull(handler.getHandledState());
        assertEquals(NewState.CAPTION, handler.getHandledState());
    }

    @Test
    public void testSomeState() throws Exception
    {
        SomeHandlerTestStub handler = new SomeHandlerTestStub();
        Core.getInstance().getDocumentLifecycleBus().registerDocumentLifecycleHandler(handler);
        SomeHandledDocumentTestStub document = new SomeHandledDocumentTestStub();
        document.changeState();

        assertNotNull(document.getState());
        assertEquals(SomeHandledStateTestStub.CAPTION, document.getState().getStateCaption());
        assertEquals(SomeHandledStateTestStub.CAPTION, handler.getHandledState());
    }

    @Test(expected = DocumentHandlingException.class)
    public void testThrowing() throws Exception
    {
        SomeHandlerTestStub handler = new SomeHandlerTestStub();
        Core.getInstance().getDocumentLifecycleBus().registerDocumentLifecycleHandler(handler);
        SomeHandledDocumentTestStub document = new SomeHandledDocumentTestStub();
        document.changeThrowState();
    }
}
