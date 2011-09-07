package com.keebraa.docs.model;

import java.util.Date;

import com.keebraa.docs.Core;
import com.keebraa.docs.exceptions.DocumentHandlingException;

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

	@DocumentField
	private DocumentState state;

	public Document() throws DocumentHandlingException
	{
	    Core.getInstance().getDocumentLifecycleBus().handleDocument(this, DocumentState.NEW);
		state = DocumentState.NEW;
	}

	public void performDeleting() throws DocumentHandlingException
	{
	    Core.getInstance().getDocumentLifecycleBus().handleDocument(this, DocumentState.DELETED);
		state = DocumentState.DELETED;
	}
	
	public void performCancelRecording() throws DocumentHandlingException
    {
        Core.getInstance().getDocumentLifecycleBus().handleDocument(this, DocumentState.CANCELED);
        state = DocumentState.CANCELED;
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

	public void performMarking() throws DocumentHandlingException
	{
	    Core.getInstance().getDocumentLifecycleBus().handleDocument(this, DocumentState.MARKED);
		state = DocumentState.MARKED;
	}

	public void performRecording() throws DocumentHandlingException
	{
	    Core.getInstance().getDocumentLifecycleBus().handleDocument(this, DocumentState.RECORDED);
		state = DocumentState.RECORDED;
	}

	public void performSaving() throws DocumentHandlingException
	{
	    Core.getInstance().getDocumentLifecycleBus().handleDocument(this, DocumentState.SAVED);
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