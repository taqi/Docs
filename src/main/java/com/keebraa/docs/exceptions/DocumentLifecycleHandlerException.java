package com.keebraa.docs.exceptions;

/**
 * Exception, that tells you about wrong realization or implementing
 * {@link DocumentLifecycleHandler}
 * 
 * @author taqi
 * 
 */
public class DocumentLifecycleHandlerException extends DocsRuntimeException
{
    private static final long serialVersionUID = -210068519706104806L;

    public DocumentLifecycleHandlerException()
    {
        super();
    }

    public DocumentLifecycleHandlerException(String message)
    {
        super(message);
    }

    public DocumentLifecycleHandlerException(RuntimeException e)
    {
        super(e);
    }
}
