package com.keebraa.docs;

import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.model.Document;
import com.keebraa.docs.model.DocumentState;
import com.keebraa.docs.model.dochandlers.DocumentLifecycleHandler;

/**
 * This interface provides common mechanism for document handling. Each
 * {@link DocumentLifeCycleHandler} should be registered by this interface.
 * 
 * @author taqi
 * 
 */
public interface DocumentLifecycleBus
{
    public static final String LIFECYCLE_BUS_BEANNAME = "documentLifecycleBus";
	/**
	 * Method should register new {@link DocumentLifecycleHandler}
	 * 
	 * @param handler
	 *            - new document lifecycle handler
	 */
	public void registerDocumentLifecycleHandler(DocumentLifecycleHandler handler);

	/**
	 * Method handles document changestate, calls handlers.
	 * 
	 * @param document
	 *            - {@link Document} to be handled
	 * @param nextState
	 *            - Next state for document
	 * @throws DocumentHandlingException
	 *             - in case, when document has not been filled correctly, etc.
	 */
	public void handleDocument(Document document, DocumentState nextState)
			throws DocumentHandlingException;

}
