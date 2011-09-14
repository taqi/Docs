package com.keebraa.docs.model.dochandlers.stub;

import com.keebraa.docs.model.dochandlers.DocumentStateHandler;

@DocumentStateHandler(handledClass = Object.class)
public class SomeWrongHandlerTestStub
{

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
