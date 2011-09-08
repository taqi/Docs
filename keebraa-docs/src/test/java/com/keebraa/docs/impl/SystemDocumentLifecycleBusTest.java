package com.keebraa.docs.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
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
        private boolean processed;
        private DocumentState targetState;
        private DocumentState sourceState;

        @Override
        public Class<MockDocument> getHandlingClass()
        {
            return MockDocument.class;
        }

        @HandlingMethod(state = DocumentState.CANCELED)
        public void handleCanceling(Document document)
        {
            proccess(document, DocumentState.CANCELED);
        }

        @HandlingMethod(state = DocumentState.DELETED)
        public void handleDeleting(Document document)
        {
            proccess(document, DocumentState.DELETED);
        }

        @HandlingMethod(state = DocumentState.NEW)
        public void handleInstantiating(Document document)
        {
            proccess(document, DocumentState.NEW);
        }

        @HandlingMethod(state = DocumentState.MARKED)
        public void handleMarking(Document document)
        {
            proccess(document, DocumentState.MARKED);
        }

        @HandlingMethod(state = DocumentState.RECORDED)
        public void handleRecording(Document document)
        {
            proccess(document, DocumentState.RECORDED);
        }

        @HandlingMethod(state = DocumentState.SAVED)
        public void handleSaving(Document document)
        {
            proccess(document, DocumentState.SAVED);
        }

        public boolean isProcessed()
        {
            return processed;
        }

        public DocumentState getSourceState()
        {
            return sourceState;
        }

        public DocumentState getTargetState()
        {
            return targetState;
        }

        private void proccess(Document document, DocumentState nextState)
        {
            processed = true;
            this.targetState = nextState;
            this.sourceState = document.getState();
        }
    }

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

    @Test
    public void handleDocument_InstantiateDocument() throws Exception
    {
        DocumentLifecycleBus bus = Core.getInstance().getDocumentLifecycleBus();
        IndicatorHandler handler = new IndicatorHandler();

        assertFalse(handler.isProcessed());

        bus.registerDocumentLifecycleHandler(handler);

        new MockDocument();

        assertTrue(handler.isProcessed());
        assertNull(handler.getSourceState());
        assertEquals(DocumentState.NEW, handler.getTargetState());
        assertNotSame(DocumentState.MARKED, handler.getTargetState());
        assertNotSame(DocumentState.CANCELED, handler.getTargetState());
        assertNotSame(DocumentState.RECORDED, handler.getTargetState());
    }

    @Test
    public void handleDocument_RecordDocument() throws Exception
    {
        DocumentLifecycleBus bus = Core.getInstance().getDocumentLifecycleBus();
        IndicatorHandler handler = new IndicatorHandler();
        assertFalse(handler.isProcessed());
        bus.registerDocumentLifecycleHandler(handler);
        Document document = new MockDocument();

        assertTrue(handler.isProcessed());
        assertNull(handler.getSourceState());
        assertEquals(DocumentState.NEW, handler.getTargetState());

        document.performRecording();
        assertTrue(handler.isProcessed());
        assertEquals(DocumentState.SAVED, handler.getSourceState());
        assertEquals(DocumentState.RECORDED, handler.getTargetState());
        assertNotSame(DocumentState.NEW, handler.getTargetState());
        assertNotSame(DocumentState.MARKED, handler.getTargetState());
        assertNotSame(DocumentState.CANCELED, handler.getTargetState());
    }

    @Test
    public void handleDocument_SaveDocument() throws Exception
    {
        DocumentLifecycleBus bus = Core.getInstance().getDocumentLifecycleBus();
        IndicatorHandler handler = new IndicatorHandler();
        assertFalse(handler.isProcessed());
        bus.registerDocumentLifecycleHandler(handler);
        Document document = new MockDocument();

        assertTrue(handler.isProcessed());
        assertNull(handler.getSourceState());
        assertEquals(DocumentState.NEW, handler.getTargetState());

        document.performSaving();
        assertTrue(handler.isProcessed());
        assertEquals(DocumentState.NEW, handler.getSourceState());
        assertEquals(DocumentState.SAVED, handler.getTargetState());
        assertNotSame(DocumentState.NEW, handler.getTargetState());
        assertNotSame(DocumentState.MARKED, handler.getTargetState());
        assertNotSame(DocumentState.CANCELED, handler.getTargetState());
    }

    @Test
    public void handleDocument_CancelRecordingDocumentWithoutRecords() throws Exception
    {
        DocumentLifecycleBus bus = Core.getInstance().getDocumentLifecycleBus();
        IndicatorHandler handler = new IndicatorHandler();
        assertFalse(handler.isProcessed());
        bus.registerDocumentLifecycleHandler(handler);
        Document document = new MockDocument();

        assertTrue(handler.isProcessed());
        assertNull(handler.getSourceState());
        assertEquals(DocumentState.NEW, handler.getTargetState());

        document.performRecording();

        assertTrue(handler.isProcessed());
        assertEquals(DocumentState.SAVED, handler.getSourceState());
        assertEquals(DocumentState.RECORDED, handler.getTargetState());
        assertNotSame(DocumentState.NEW, handler.getTargetState());
        assertNotSame(DocumentState.MARKED, handler.getTargetState());
        assertNotSame(DocumentState.CANCELED, handler.getTargetState());

        document.performCancelRecording();

        assertTrue(handler.isProcessed());
        assertEquals(DocumentState.CANCELED, handler.getSourceState());
        assertEquals(DocumentState.SAVED, handler.getTargetState());
        assertNotSame(DocumentState.NEW, handler.getTargetState());
        assertNotSame(DocumentState.MARKED, handler.getTargetState());
        assertNotSame(DocumentState.CANCELED, handler.getTargetState());
    }

    @Test
    public void handleDocument_CancelRecordingDocumentWithRecords() throws Exception
    {
        DocumentLifecycleBus bus = Core.getInstance().getDocumentLifecycleBus();
        IndicatorHandler handler = new IndicatorHandler();
        assertFalse(handler.isProcessed());
        bus.registerDocumentLifecycleHandler(handler);
        MockDocument document = new MockDocument();

        assertTrue(handler.isProcessed());
        assertNull(handler.getSourceState());
        assertEquals(DocumentState.NEW, handler.getTargetState());

        document.performRecording();
        document.setRecords(true);

        assertTrue(handler.isProcessed());
        assertEquals(DocumentState.SAVED, handler.getSourceState());
        assertEquals(DocumentState.RECORDED, handler.getTargetState());
        assertNotSame(DocumentState.NEW, handler.getTargetState());
        assertNotSame(DocumentState.MARKED, handler.getTargetState());
        assertNotSame(DocumentState.CANCELED, handler.getTargetState());

        document.performCancelRecording();

        assertTrue(handler.isProcessed());
        assertEquals(DocumentState.RECORDED, handler.getSourceState());
        assertEquals(DocumentState.CANCELED, handler.getTargetState());
        assertNotSame(DocumentState.NEW, handler.getTargetState());
        assertNotSame(DocumentState.MARKED, handler.getTargetState());
    }
}
