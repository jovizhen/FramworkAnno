package com.framworkannotation.meta.formatter;

public interface IFormatter 
{
	public Object formatForStorage(String s);

	public Object format(String s);
	
	public String parseFromStorage(Object obj);

	public String parse(Object obj); 
}
