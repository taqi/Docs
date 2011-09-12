package com.keebraa.docs.model;

import java.util.Date;

import com.keebraa.docs.Core;
import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.model.impl.NewState;

public abstract class Document
{
    @DocumentField
    private Date createDate;

    @DocumentField
    private String name;

    @DocumentField
    private long number;

    @DocumentField
    private String prefix;

    private DocumentState state;

    public Document() throws DocumentHandlingException
    {
        notifyDocumentLifecycleBus(new NewState());
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

    protected void notifyDocumentLifecycleBus(DocumentState newState) throws DocumentHandlingException
    {
        Core.getInstance().getDocumentLifecycleBus()
                .handleDocument(this, newState);
        setState(newState);
    }

    protected void setState(DocumentState newState)
    {
        state = newState;
    }
}