package com.keebraa.docs.model;

public class TextComponent extends Component
{
	private StringBuilder text;

	private static final String lineSeparatorKey = "line.separator";

	public TextComponent()
	{
		text = new StringBuilder();
	}

	public void setText(String text)
	{
		this.text = new StringBuilder();
		this.text.append(text);
	}

	public void addText(String text)
	{
		this.text.append(text);
	}

	public void addTextLine(String text)
	{
		if (this.text.length() > 0)
		{
			this.text.append(System.getProperty(lineSeparatorKey));
		}
		this.text.append(text);
	}

	public String getText()
	{
		return this.text.toString();
	}

	@Override
	public int hashCode()
	{
		return text.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof TextComponent))
		{
			return false;
		}
		return text.equals(obj);
	}
}
