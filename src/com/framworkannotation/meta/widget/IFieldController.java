package com.framworkannotation.meta.widget;

import android.view.View;
import android.view.View.OnClickListener;

public interface IFieldController 
{
	public void setFieldMeta(FieldMeta fmeta);
	
	public FieldModel getFieldModel();
	
	public View getBoundedComponent();
	
	public void updateUI(Object fieldValue);
	
	public String getFieldName();
	
	public void setOnClickListener(OnClickListener listener);
}
