package com.keebraa.docs.model.dochandlers;

import com.keebraa.docs.model.Document;

public abstract class DocumentLifecycleHandler
{
	public abstract <T extends Document>  Class<T> getHandlingClass();
}
