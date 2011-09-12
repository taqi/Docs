package com.keebraa.docs.impl.stub;

import com.keebraa.docs.model.DocumentState;

public class SomeHandledState implements DocumentState
{
    public static final String CAPTION = "some handled state";
    public String getStateCaption()
    {
        return CAPTION;
    }

}
