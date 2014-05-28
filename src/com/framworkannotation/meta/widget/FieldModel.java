package com.framworkannotation.meta.widget;

import java.util.Vector;


public class FieldModel 
{
	FieldMeta fieldMeta;
	
	IFieldController fieldController;
	
	Object fieldValue;

	Vector<IFieldChangeListner> fieldchangeListnerList_dormain = new Vector<IFieldChangeListner>();

	Vector<IFieldChangeListner> fieldchangeListnerList_ui = new Vector<IFieldChangeListner>();
	
	public FieldModel(FieldMeta fmeta)
	{
		this.fieldMeta = fmeta;
	}

	public FieldMeta getFieldMeta()
	{
		return fieldMeta;
	}

	public void setFieldMeta(FieldMeta fieldMeta)
	{
		this.fieldMeta = fieldMeta;
	}

	public Vector<IFieldChangeListner> getFieldchangeListnerList_dormain() 
	{
		return fieldchangeListnerList_dormain;
	}

	public Vector<IFieldChangeListner> getFieldchangeListnerList_ui() 
	{
		return fieldchangeListnerList_ui;
	}
	
	public void addFieldChangeListner_dormain(IFieldChangeListner listner)
	{
		fieldchangeListnerList_dormain.add(listner);
	}

	public Object getFieldValue() 
	{
		return fieldValue;
	}

	
	public void updateFieldValueFormUI(Object fieldValue)
	{
		this.fieldValue = fieldValue;
		Object formatValue = fieldMeta.getFieldFAP().getFieldFormatter()
				.formatForStorage((String) fieldValue);
		fieldMeta.fieldFAP.setValue(fieldMeta.getDataSourceObject(),
				formatValue);
	}

	public void updateFieldValueFromDormain(Object fieldValue)
	{
		this.fieldValue = fieldValue;
		String parsedValue = fieldMeta.getFieldFAP().getFieldFormatter()
				.parseFromStorage(fieldValue);
		
		fieldController.updateUI(parsedValue);
	}

	public IFieldController getFieldController() 
	{
		return fieldController;
	}

	public void setFieldController(IFieldController fieldController) 
	{
		this.fieldController = fieldController;
	}
}
