package com.keebraa.docs.model.dochandlers.stub;

import com.keebraa.docs.model.dochandlers.DocumentStateHandler;
import com.keebraa.docs.model.dochandlers.HandlingMethod;
import com.keebraa.docs.model.impl.NewState;

@DocumentStateHandler(handledClass=SomeDocumentTestStub.class)
public class SomeHandlerTestStub
{   
    
    @HandlingMethod(state=NewState.CAPTION)
    public void handleCreatingDocument()
    {

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
