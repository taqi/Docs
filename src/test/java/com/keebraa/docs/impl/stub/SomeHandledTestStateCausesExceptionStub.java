package com.keebraa.docs.impl.stub;

import com.keebraa.docs.model.DocumentState;

public class SomeHandledTestStateCausesExceptionStub implements DocumentState
{
    public static final String CAPTION = "exceptionstate";

    public String getStateCaption()
    {
        return CAPTION;
    }
}
