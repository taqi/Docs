package com.keebraa.docs.impl.stub;

import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.model.Document;
import com.keebraa.docs.model.dochandlers.DocumentLifecycleHandler;
import com.keebraa.docs.model.dochandlers.DocumentStateHandler;
import com.keebraa.docs.model.dochandlers.HandlingMethod;
import com.keebraa.docs.model.impl.NewState;

@DocumentStateHandler(handledClass = SomeHandledDocumentTestStub.class)
public class SomeHandlerTestStub extends DocumentLifecycleHandler
{
    private String handledState;
    
    @HandlingMethod(state = NewState.CAPTION)
    public void handleNewState(Document document)
    {
        handledState = NewState.CAPTION;
    }
    
    @HandlingMethod(state = SomeHandledStateTestStub.CAPTION)
    public  void handleSomeState(Document document)
    {
        handledState = SomeHandledStateTestStub.CAPTION;
    }
    
    @HandlingMethod(state = SomeHandledTestStateCausesExceptionStub.CAPTION)
    public void handleWrongState(Document document) throws DocumentHandlingException
    {
        throw new DocumentHandlingException("blablalba");
    }
    
    public String getHandledState()
    {
        return handledState;
    }

    @Override
    public int hashCode()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean equals(Object obj)
    {        
        return false;
    }
}
