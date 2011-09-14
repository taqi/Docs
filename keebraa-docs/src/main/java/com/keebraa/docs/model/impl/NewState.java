package com.keebraa.docs.model.impl;

import com.keebraa.docs.model.DocumentState;

/**
 * State for document, that has just been created.
 * 
 * @author taqi
 * 
 */
public class NewState implements DocumentState
{

    public static final String CAPTION = "NEW";

    public String getStateCaption()
    {
        return CAPTION;
    }
}
