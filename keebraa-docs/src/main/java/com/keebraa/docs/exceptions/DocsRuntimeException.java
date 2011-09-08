package com.keebraa.docs.exceptions;

public class DocsRuntimeException extends RuntimeException
{
    private static final long serialVersionUID = 5719045293077433057L;
    
    public DocsRuntimeException()
    {
        super();
    }
    
    public DocsRuntimeException(String message)
    {
        super(message);
    }
    
    public DocsRuntimeException(RuntimeException e)
    {
        super(e);
    }    
}
