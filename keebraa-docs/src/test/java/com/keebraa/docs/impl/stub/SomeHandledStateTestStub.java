package com.keebraa.docs.impl.stub;

import com.keebraa.docs.model.DocumentState;

public class SomeHandledStateTestStub implements DocumentState
{
    public static final String CAPTION = "some handled state";
    public String getStateCaption()
    {
        return CAPTION;
    }

}
