package com.keebraa.docs.exceptions;

public class DocumentHandlingException extends Exception
{
	private static final long serialVersionUID = -3036131776085803597L;

	public DocumentHandlingException()
	{
		super();
	}
	
	public DocumentHandlingException(Exception e)
    {
        super(e);
    }
	
	public DocumentHandlingException(String message)
    {
        super(message);
    }
}
