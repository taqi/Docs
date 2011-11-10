package com.keebraa.docs.model;

import com.keebraa.docs.exceptions.DocumentHandlingException;

public abstract class BasedDocument extends Document
{
    @DocumentField
    private Document basedDocument;

    private BasedDocument() throws DocumentHandlingException
    {
        super();
    }

    public BasedDocument(Document basedDocument) throws DocumentHandlingException
    {
        this();
        if(basedDocument == null)
        {
            throw new DocumentHandlingException("based document can't be null");
        }
        this.basedDocument = basedDocument;
    }
    
    public Document getBasedDocument()
    {
        return basedDocument;
    }
}
