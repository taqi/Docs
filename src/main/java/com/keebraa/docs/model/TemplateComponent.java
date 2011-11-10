package com.keebraa.docs.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.keebraa.docs.exceptions.DocsRuntimeException;

public class TemplateComponent extends TextComponent
{
	private static final Pattern TEMP_PATTERN = Pattern
			.compile("^\\{[a-zA-Z0-9_-]+\\}$");
	private Map<String, Object> templateValues;
	
	public TemplateComponent()
	{
		templateValues = new HashMap<String, Object>();
	}

	public void addTemplatePlace(String temp, Object value)
	{
		Matcher matcher = TEMP_PATTERN.matcher(temp);
		if (matcher.find())
		{
			templateValues.put(temp, value);
		} else
		{
			throw new DocsRuntimeException("wrong template format!");
		}
	}

	public boolean containsTemplatePlace(String place)
	{
		return templateValues.containsKey(place);
	}
		
}
