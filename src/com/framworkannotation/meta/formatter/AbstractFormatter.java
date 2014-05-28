package com.framworkannotation.meta.formatter;

public abstract class AbstractFormatter implements IFormatter
{
protected Class supportedClass;
	
	public AbstractFormatter(Class supportedClass)
	{
		this.supportedClass = supportedClass;
	}
	
	public boolean isSupported(Object obj)
	{
		return obj == null || supportedClass.isInstance(obj);
	}
	
	public Class<Object> getSupportedClass()
	{
		return supportedClass;
	}

	@Override
	public Object formatForStorage(String s)
	{
		return format(s);
	}

	@Override
	public String parseFromStorage(Object obj)
	{
		return parse(obj);
	}


}
