package com.keebraa.docs.model;

import com.keebraa.docs.exceptions.DocumentHandlingException;

public enum DocumentState
{
    /**
     * This state should be setted to Document during instantiation. 
     * At this case, document has been instantiated, but has not been saved yet. 
     * From this state document cannot be moved to RECORDED state without SAVED state.
     * This state throws {@link DocumentHandlingException} while attempting move document with this state directly to
     * MARKED, DELETED, CANCELED states. 
     * 
     * Next state should be SAVED (recommended).
     */
	NEW,
	
	SAVED,
	RECORDED,
	CANCELED,
	MARKED,
	DELETED;
}
