package com.keebraa.docs.model;

import com.keebraa.docs.exceptions.DocumentHandlingException;

public enum DocumentState
{
    /**
     * This state should be setted to Document during instantiation. At this
     * case, document has been instantiated, but has not been saved yet. From
     * this state document cannot be moved to RECORDED state without SAVED
     * state. This state throws {@link DocumentHandlingException} while
     * attempting move document with this state directly to MARKED, DELETED,
     * CANCELED states.
     * 
     * Next state should be SAVED (recommended).
     */
    NEW,
    /**
     * This state document gets after correct saving in DataBase or something
     * like that. This state should not generate records or modify all system.
     * If current doc state is CANCELED (see below) this state initiates
     * REMOVE_RECORDS state handling.
     */
    SAVED,

    /**
     * This state initiates records generating for system. After this state the
     * document became a part of the system state. If the document is a NEW,
     * RECORDED state initiate saving (SAVED), and after - RECORDED.
     */
    RECORDED,
    /**
     * This state document gets in case, when you want to rollback all records
     * from your system. This state is the same as SAVED, but(!) with all
     * generated records with "invisible" flag. When you want to record this
     * document again without any updates, handlers must set some "visible" flag
     * on records. But, if you update your document, this state should initiates
     * changing state to SAVED first.
     */
    CANCELED,
    /**
     * This state is necessary for notify all handlers about update CANCELED
     * document. This state should tell handlers, that they must correctly
     * remove all records from DataBase, file system, etc...
     */
    REMOVE_RECORDS,
    /**
     * Marked as deleted.
     * 
     */
    MARKED,
    /**
     * Deleted.
     */
    DELETED;
}
