package com.framworkannotation.meta.widget;

import com.framworkannotation.meta.FAP;

public class FieldMeta implements IFieldChangeListner
{
	protected FAP fieldFAP;
	protected Object dataSourceObject;
	protected IFormController formController;
	protected FieldModel fieldModel;
	
	public FieldMeta(FAP fieldFAP, IFormController formController)
	{
		configure(fieldFAP, formController);
		
		
	}
	
	private void configure(FAP fieldFAP, IFormController formController) 
	{
		this.fieldFAP = fieldFAP;
		this.formController = formController;
		formController.registerFieldMeta(this);
		this.fieldModel = new FieldModel(this);
	}

	public FAP getFieldFAP() 
	{
		return fieldFAP;
	}

	public void setFieldFAP(FAP fieldFAP) 
	{
		this.fieldFAP = fieldFAP;
	}

	public Object getDataSourceObject() 
	{
		return dataSourceObject;
	}

	public void setDataSourceObject(Object dataSourceObject) 
	{
		this.dataSourceObject = dataSourceObject;
		Object fieldValue = getFieldFAP().getValue(dataSourceObject);
		fieldModel.updateFieldValueFromDormain(fieldValue);
	}

	public FieldModel getFieldModel() 
	{
		return fieldModel;
	}

	public void setFieldModel(FieldModel fieldModel) 
	{
		this.fieldModel = fieldModel;
	}
	
	@Override
	public void fieldChanging(FieldChangeEvent event) 
	{
	}

	@Override
	public void fieldChanged(FieldChangeEvent event) 
	{
		
	}  
}

