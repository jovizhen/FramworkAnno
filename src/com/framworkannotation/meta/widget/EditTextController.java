package com.framworkannotation.meta.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class EditTextController implements IFieldController
{
	FieldMeta fieldMeta;
	
	FieldModel fieldModel;
	
	EditText editText;
	
	String fieldName;
	
	OnClickListener listener;
	
	boolean isProgramChange=false;
	
	public EditTextController(View editText, String fieldName)
	{
		this.editText = (EditText) editText;
		this.fieldName = fieldName;
	}
	
	TextWatcher textChangeListner = new TextWatcher()
	{
		@Override
		public void afterTextChanged(Editable s) 
		{
			fieldModel.updateFieldValueFormUI(s.toString());
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after)
		{
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) 
		{
		}
	}; 

	@Override
	public void setFieldMeta(FieldMeta fmeta) 
	{
		this.fieldMeta = fmeta;
		this.fieldModel = fmeta.getFieldModel();
		this.editText.addTextChangedListener(textChangeListner);
		this.fieldModel.setFieldController(this);
	}

	@Override
	public FieldModel getFieldModel() 
	{
		return fieldModel;
	}

	@Override
	public View getBoundedComponent() 
	{
		return editText;
	}

	@Override
	public void updateUI(Object fieldValue) 
	{
		editText.setText((String) fieldValue);
	}

	@Override
	public String getFieldName()
	{
		return fieldName;
	}

	@Override
	public void setOnClickListener(OnClickListener listener)
	{
		this.listener = listener;
		this.editText.setOnClickListener(listener);
	}

}
