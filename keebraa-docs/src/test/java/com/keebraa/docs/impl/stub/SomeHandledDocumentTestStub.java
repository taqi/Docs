package com.keebraa.docs.impl.stub;

import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.model.Document;

public class SomeHandledDocumentTestStub extends Document
{
    public SomeHandledDocumentTestStub() throws DocumentHandlingException
    {
        super();
    }

    @Override
    public boolean hasRecords()
    {
        return false;
    }
    
    public void changeState() throws DocumentHandlingException
    {
        notifyDocumentLifecycleBus(new SomeHandledStateTestStub());
    }
    
    public void changeThrowState() throws DocumentHandlingException
    {
        notifyDocumentLifecycleBus(new SomeHandledTestStateCausesExceptionStub());
    }
}
