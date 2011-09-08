package com.keebraa.docs.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import com.keebraa.docs.Core;
import com.keebraa.docs.DocumentLifecycleBus;
import com.keebraa.docs.exceptions.DocumentHandlingException;
/**
 * Test cases for validate basic abstract Document behavior. 
 * 
 * @author taqi
 *
 */
public class DocumentTest
{
    
    class MockDocument extends Document
    {
        private boolean records;

        public MockDocument() throws DocumentHandlingException
        {
            super();
        }

        @Override
        public boolean hasRecords()
        {
            return records;
        }

        public void setRecords(boolean records)
        {
            this.records = records;
        }
    }
    @Test(expected = DocumentHandlingException.class)
    public void handleDocument_MarkDocumentAfterCreate() throws Exception
    {
        Document document = new MockDocument();
        
        assertEquals(DocumentState.NEW, document.getState());

        document.performMarking();
    }
    
    @Test
    public void handleDocument_MarkDocumentAfterSaving() throws Exception
    {
        Document document = new MockDocument();
        
        assertEquals(DocumentState.NEW, document.getState());
        
        document.performSaving();
        document.performMarking();
        
        assertEquals(DocumentState.MARKED, document.getState());
        assertNotSame(DocumentState.SAVED, document.getState());
        assertNotSame(DocumentState.NEW, document.getState());
    }
    
    @Test
    public void handleDocument_MarkDocumentAfterRecording() throws Exception
    {
        Document document = new MockDocument();

        assertEquals(DocumentState.NEW, document.getState());
        document.performSaving();
        document.performRecording();
        
        assertEquals(DocumentState.RECORDED, document.getState());
        assertNotSame(DocumentState.SAVED, document.getState());
        assertNotSame(DocumentState.NEW, document.getState());
        
        document.performMarking();
        
        assertEquals(DocumentState.MARKED, document.getState());
        assertNotSame(DocumentState.SAVED, document.getState());
        assertNotSame(DocumentState.NEW, document.getState());
        assertNotSame(DocumentState.RECORDED, document.getState());
        
    }
    
    @Test(expected = DocumentHandlingException.class)
    public void handleDocument_MarkDocumentAfterInitializing() throws Exception
    {
        Document document = new MockDocument();
        
        assertEquals(DocumentState.NEW, document.getState());
        
        document.performMarking();       
    }
    
    @Test(expected = DocumentHandlingException.class)
    public void handleDocument_MarkDocumentAfterRemoving() throws Exception
    {
        Document document = new MockDocument();
        document.performSaving();
        document.performDeleting();
        
        assertEquals(DocumentState.DELETED, document.getState());
        assertNotSame(DocumentState.SAVED, document.getState());
        assertNotSame(DocumentState.NEW, document.getState());
        
        document.performMarking();       
    }
    
    @Test
    public void handleDocument_MarkDocumentAfterCanceling() throws Exception
    {
        MockDocument document = new MockDocument();
        
        document.setRecords(true);
        document.performSaving();
        document.performRecording();
        document.performCancelRecording();
        
        assertEquals(DocumentState.CANCELED, document.getState());
        assertNotSame(DocumentState.SAVED, document.getState());
        assertNotSame(DocumentState.NEW, document.getState());

        document.performMarking();    
        
        assertEquals(DocumentState.MARKED, document.getState());
        assertNotSame(DocumentState.SAVED, document.getState());
        assertNotSame(DocumentState.NEW, document.getState());
        assertNotSame(DocumentState.RECORDED, document.getState());
    }
}
