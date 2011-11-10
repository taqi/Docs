package com.keebraa.docs.exceptions;

/**
 * Class that represents situation with wrong Document handling 
 * @author taqi
 *
 */
public class DocumentHandlingException extends DocsException
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
