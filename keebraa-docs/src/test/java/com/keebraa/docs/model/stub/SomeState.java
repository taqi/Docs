package com.keebraa.docs.model.stub;

import com.keebraa.docs.model.DocumentState;

public class SomeState implements DocumentState
{
    public static final String CAPTION = "someState";
    public String getStateCaption()
    {
        return CAPTION;
    }

}
