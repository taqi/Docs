package com.keebraa.docs.impl.stub;

import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.model.Document;

public class SomeHandledDocument extends Document
{
    public SomeHandledDocument() throws DocumentHandlingException
    {
        super();
    }

    @Override
    public boolean hasRecords()
    {
        return false;
    } 
}
