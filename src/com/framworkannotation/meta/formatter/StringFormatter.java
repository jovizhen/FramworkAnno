package com.framworkannotation.meta.formatter;

public class StringFormatter extends AbstractFormatter
{

	public StringFormatter() 
	{
		super(String.class);
	}

	@Override
	public Object format(String s)
	{
		return s;
	}

	@Override
	public String parse(Object obj)
	{
		return (String) obj;
	}
}
