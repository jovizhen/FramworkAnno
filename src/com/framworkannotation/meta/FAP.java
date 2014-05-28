package com.framworkannotation.meta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.framworkannotation.meta.formatter.CoreFormatter;
import com.framworkannotation.meta.formatter.IFormatter;

//FieldAnnotationProcessor

public class FAP
{
	private Class<?> clazz;
	private String reflectionDescription;

	public FAP(Class<?> clazz)
	{
		this(clazz, null);
	}

	public FAP(Class<?> clazz, String reflectionDescription)
	{
		this.clazz = clazz;
		this.reflectionDescription = reflectionDescription;
	}

	public Object getValue(Object obj)
	{
		Method getMethod = getFieldGetMethod(reflectionDescription);
		if (getMethod != null)
		{
			try
			{
				return getMethod.invoke(obj);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public void setValue(Object obj, Object value)

	{
		Method setMethod = getFieldSetMethod(reflectionDescription);
		if (setMethod != null)
		{
			try
			{
				setMethod.invoke(obj, value);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public Method getFieldGetMethod(String reflectionDescription)
	{
		String mn = "get" + reflectionDescription.substring(0, 1).toUpperCase()
				+ reflectionDescription.substring(1);
		try
		{
			return clazz.getDeclaredMethod(mn);
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public Method getFieldSetMethod(String reflectionDescription)
	{
		String mn = "set" + reflectionDescription.substring(0, 1).toUpperCase()
				+ reflectionDescription.substring(1);
		try
		{
			return clazz.getDeclaredMethod(mn,
					getFieldByName(reflectionDescription).getType());
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public Field getFieldByName(String reflectionDescription)
	{
		Field field = null;
		try
		{
			field = clazz.getDeclaredField(reflectionDescription);
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		return field;
	}

	public IFormatter getFieldFormatter()
	{
		if (getFieldByName(reflectionDescription) != null)
		{
			FieldAnnotation anno = getFieldByName(reflectionDescription)
					.getAnnotation(FieldAnnotation.class);
			if (anno != null)
			{
				String formatterStr = anno.formatter();
				if ("STRING".equals(formatterStr))
				{
					return CoreFormatter.STRING;
				}
				else if ("INTEGER".equals(formatterStr))
				{
					return CoreFormatter.INTEGER;
				}
			}
		}
		return null;
	}

	public String getReflectionDescription()
	{
		return reflectionDescription;
	}

}
