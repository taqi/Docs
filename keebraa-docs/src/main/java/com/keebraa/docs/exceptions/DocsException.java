package com.keebraa.docs.exceptions;

/**
 * Base exception class for Docs project
 * 
 * @author taqi
 * 
 */
public class DocsException extends Exception
{

    private static final long serialVersionUID = -8696100403928486726L;

    public DocsException()
    {
        super();
    }

    public DocsException(String message)
    {
        super(message);
    }

    public DocsException(Exception e)
    {
        super(e);
    }
}
