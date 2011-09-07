package com.keebraa.docs.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.keebraa.docs.Core;
import com.keebraa.docs.DocumentLifecycleBus;
import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.model.Document;
import com.keebraa.docs.model.DocumentState;
import com.keebraa.docs.model.dochandlers.DocumentLifecycleHandler;
import com.keebraa.docs.model.dochandlers.HandlingMethod;

public class SystemDocumentLifecycleBusTest
{
    class IndicatorHandler extends DocumentLifecycleHandler
    {
        private boolean processed = false;
        private Document document;

        @Override
        public Class<MockDocument> getHandlingClass()
        {
            return MockDocument.class;
        }

        public boolean isProcessed()
        {
            return processed;
        }

        private void proccess(Document document)
        {
            processed = true;
            this.document = document;
        }

        @HandlingMethod(state = DocumentState.SAVED)
        public void handleSaving(Document document)
        {
            proccess(document);
        }

        @HandlingMethod(state = DocumentState.MARKED)
        public void handleMarking(Document document)
        {
            proccess(document);
        }

        @HandlingMethod(state = DocumentState.RECORDED)
        public void handleRecording(Document document)
        {
            proccess(document);
        }

        @HandlingMethod(state = DocumentState.NEW)
        public void handleInstantiating(Document document)
        {
            proccess(document);
        }

        @HandlingMethod(state = DocumentState.CANCELED)
        public void handleCanceling(Document document)
        {
            proccess(document);
        }

        @HandlingMethod(state = DocumentState.DELETED)
        public void handleDeleting(Document document)
        {
            proccess(document);
        }

        public boolean isSaved()
        {
            return document.getState().equals(DocumentState.SAVED);
        }

        public boolean isMarked()
        {
            return document.getState().equals(DocumentState.MARKED);
        }

        public boolean isCanceled()
        {
            return document.getState().equals(DocumentState.CANCELED);
        }

        public boolean isDeleted()
        {
            return document.getState().equals(DocumentState.DELETED);
        }

        public boolean isNew()
        {
            return document.getState().equals(DocumentState.NEW);
        }

        public boolean isRecorded()
        {
            return document.getState().equals(DocumentState.RECORDED);
        }
    }

    class MockDocument extends Document
    {
        public MockDocument() throws DocumentHandlingException
        {
            super();
        }
    }

    @Test
    public void handleDocument_InstantiateDocument() throws Exception
    {
        DocumentLifecycleBus bus = Core.getInstance().getDocumentLifecycleBus();
        IndicatorHandler handler = new IndicatorHandler();

        assertFalse(handler.isProcessed());

        bus.registerDocumentLifecycleHandler(handler);

        new MockDocument();

        assertTrue(handler.isProcessed());
        assertEquals(true, handler.isNew());

        assertEquals(false, handler.isSaved());
        assertEquals(false, handler.isDeleted());
        assertEquals(false, handler.isMarked());
        assertEquals(false, handler.isRecorded());
        assertEquals(false, handler.isCanceled());
    }
    
    @Test
    public void handleDocument_SaveDocument() throws Exception
    {
        DocumentLifecycleBus bus = Core.getInstance().getDocumentLifecycleBus();
        IndicatorHandler handler = new IndicatorHandler();

        assertFalse(handler.isProcessed());

        bus.registerDocumentLifecycleHandler(handler);

        Document document = new MockDocument();
        document.performSaving();

        assertTrue(handler.isProcessed());
        
        assertEquals(true, handler.isSaved());
        assertEquals(false, handler.isNew());
        
        assertEquals(false, handler.isDeleted());
        assertEquals(false, handler.isMarked());
        assertEquals(false, handler.isRecorded());
        assertEquals(false, handler.isCanceled());
    }
    
    @Test
    public void handleDocument_RecordDocument() throws Exception
    {
        DocumentLifecycleBus bus = Core.getInstance().getDocumentLifecycleBus();
        IndicatorHandler handler = new IndicatorHandler();

        assertFalse(handler.isProcessed());

        bus.registerDocumentLifecycleHandler(handler);

        Document document = new MockDocument();
        document.performRecording();

        assertTrue(handler.isProcessed());
        
        assertEquals(true, handler.isRecorded());
        
        assertEquals(false, handler.isSaved());
        assertEquals(false, handler.isNew());        
        assertEquals(false, handler.isDeleted());
        assertEquals(false, handler.isMarked());        
        assertEquals(false, handler.isCanceled());
    }
}
