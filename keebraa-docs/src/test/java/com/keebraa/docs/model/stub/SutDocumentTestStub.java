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
public class SutDocumentTestStub extends Document
{

    public SutDocumentTestStub() throws DocumentHandlingException
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
        notifyDocumentLifecycleBus(new SomeStateTestStub());
    }
}
