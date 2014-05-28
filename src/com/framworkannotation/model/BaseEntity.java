package com.framworkannotation.model;

import com.framworkannotation.meta.FAP;
import com.framworkannotation.meta.FieldAnnotation;

public class BaseEntity 
{
	public static final FAP ID   = new FAP(BaseEntity.class, "id");
	public static final FAP NAME = new FAP(BaseEntity.class, "name");
	
	@FieldAnnotation(formatter = "INTEGER")
	public Integer id;
	
	@FieldAnnotation(formatter = "STRING")
	public String name;
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

}
