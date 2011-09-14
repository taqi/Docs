package com.keebraa.docs.model.dochandlers.stub;

import com.keebraa.docs.model.dochandlers.DocumentLifecycleHandler;
import com.keebraa.docs.model.dochandlers.DocumentStateHandler;
import com.keebraa.docs.model.dochandlers.HandlingMethod;
import com.keebraa.docs.model.impl.NewState;

@DocumentStateHandler(handledClass=SomeDocumentTestStub.class)
public class SomeHandlerTestStub extends DocumentLifecycleHandler
{
    private boolean hasBeenProcessed = false;
    
    @HandlingMethod(state=NewState.CAPTION)
    public void handleCreatingDocument()
    {
        hasBeenProcessed = true;
    }

    @Override
    public int hashCode()
    {
        return 0;
    }

    @Override
    public boolean equals(Object obj)
    {
        return false;
    }
    
}
