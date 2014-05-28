package com.framworkannotation.meta.formatter;

public class IntegerFormatter extends AbstractFormatter
{

	
	public IntegerFormatter()
	{
		super(IntegerFormatter.class);
	}

	@Override
	public Object format(String s)
	{
		return Integer.parseInt(s);
	}

	@Override
	public String parse(Object obj)
	{
		return String.valueOf((Integer) obj);
	}

}
