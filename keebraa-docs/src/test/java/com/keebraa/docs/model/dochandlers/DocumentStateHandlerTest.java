package com.keebraa.docs.model.dochandlers;

import org.junit.Test;

import com.keebraa.docs.Core;
import com.keebraa.docs.exceptions.DocumentLifecycleHandlerException;
import com.keebraa.docs.model.dochandlers.stub.SomeHandlerWithoutAnnotationTestStub;
import com.keebraa.docs.model.dochandlers.stub.SomeWrongHandlerTestStub;

public class DocumentStateHandlerTest
{
    @Test(expected=DocumentLifecycleHandlerException.class)
    public void testWrongHandledClassRegistration() throws Exception
    {
        SomeWrongHandlerTestStub handler = new SomeWrongHandlerTestStub();
        Core.getInstance().getDocumentLifecycleBus().registerDocumentLifecycleHandler(handler);
    }
    
    @Test(expected=DocumentLifecycleHandlerException.class)
    public void testHandlerWithoutAnnotationRegistration() throws Exception
    {
        SomeHandlerWithoutAnnotationTestStub handler = new SomeHandlerWithoutAnnotationTestStub();
        Core.getInstance().getDocumentLifecycleBus().registerDocumentLifecycleHandler(handler);
    }
        
    
}
