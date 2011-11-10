package com.keebraa.docs.model.stub;

import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.model.BasedDocument;
import com.keebraa.docs.model.Document;

public class SomeBasedDocumentStub extends BasedDocument
{

    public SomeBasedDocumentStub(Document basedDocument) throws DocumentHandlingException
    {
        super(basedDocument);
    }

    @Override
    public boolean hasRecords()
    {
        return false;
    }

}
