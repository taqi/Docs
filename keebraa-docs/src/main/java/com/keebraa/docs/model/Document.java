package com.keebraa.docs.model;

import java.util.Date;

import com.keebraa.docs.Core;
import com.keebraa.docs.exceptions.DocumentHandlingException;

public abstract class Document
{
    private static String WRONG_DOCUMENT_STATE_EXCEPTION_MESSAGE = "wrong state of the document.";
    @DocumentField
    private Date createDate;

    @DocumentField
    private String name;

    @DocumentField
    private long number;

    @DocumentField
    private String prefix;

    @DocumentField
    private DocumentState state;

    public Document() throws DocumentHandlingException
    {
        Core.getInstance().getDocumentLifecycleBus()
                .handleDocument(this, DocumentState.NEW);
        state = DocumentState.NEW;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public String getName()
    {
        return name;
    }

    public long getNumber()
    {
        return number;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public DocumentState getState()
    {
        return state;
    }

    public abstract boolean hasRecords();

    public final void performCancelRecording() throws DocumentHandlingException
    {
        switch(state)
        {
        case NEW:
        case MARKED:
        case DELETED:
        case SAVED:
            throw new DocumentHandlingException(WRONG_DOCUMENT_STATE_EXCEPTION_MESSAGE);        
        }
        
        if(hasRecords())
        {
            Core.getInstance().getDocumentLifecycleBus()
                .handleDocument(this, DocumentState.CANCELED);
            state = DocumentState.CANCELED;
        }
        else
        {
            state = DocumentState.CANCELED;
            performSaving();
        }
    }

    public final void performDeleting() throws DocumentHandlingException
    {
        switch (state)
        {
        case NEW:
            throw new DocumentHandlingException(WRONG_DOCUMENT_STATE_EXCEPTION_MESSAGE);
        case RECORDED:
            performCancelRecording();
            break;
        }
        Core.getInstance().getDocumentLifecycleBus()
                .handleDocument(this, DocumentState.DELETED);
        state = DocumentState.DELETED;
    }

    public final void performMarking() throws DocumentHandlingException
    {
        switch (state)
        {
        case NEW:
        case DELETED:
            throw new DocumentHandlingException(WRONG_DOCUMENT_STATE_EXCEPTION_MESSAGE);
        case RECORDED:
            performCancelRecording();
        }
        
        Core.getInstance().getDocumentLifecycleBus()
                .handleDocument(this, DocumentState.MARKED);
        state = DocumentState.MARKED;
    }

    public final void performRecording() throws DocumentHandlingException
    {
        switch (state)
        {
        case NEW:
            performSaving();
            break;
        case MARKED:
        case DELETED:
            throw new DocumentHandlingException(WRONG_DOCUMENT_STATE_EXCEPTION_MESSAGE);
        }
        Core.getInstance().getDocumentLifecycleBus()
                .handleDocument(this, DocumentState.RECORDED);
        state = DocumentState.RECORDED;
    }

    public final void performSaving() throws DocumentHandlingException
    {
        switch (state)
        {
        case RECORDED:
            performCancelRecording();
            break;
        case MARKED:
        case DELETED:
            throw new DocumentHandlingException(WRONG_DOCUMENT_STATE_EXCEPTION_MESSAGE);
        }
        Core.getInstance().getDocumentLifecycleBus()
                .handleDocument(this, DocumentState.SAVED);
        state = DocumentState.SAVED;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNumber(long number)
    {
        this.number = number;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }
}