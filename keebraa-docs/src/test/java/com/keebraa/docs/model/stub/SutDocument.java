package com.keebraa.docs.model.stub;

import com.keebraa.docs.exceptions.DocumentHandlingException;
import com.keebraa.docs.model.Document;
/**
 * This is the stub class for testing abstract Document class.
 * TODO: Make this through EasyMock (what a hell with a partial mocking??????)
 * 
 * @author taqi
 *
 */
public class SutDocument extends Document
{

    public SutDocument() throws DocumentHandlingException
    {
        super();
    }

    @Override
    public boolean hasRecords()
    {
        return false;
    }
    
    public void changeState() throws DocumentHandlingException
    {
        notifyDocumentLifecycleBus(new SomeState());
    }
}
