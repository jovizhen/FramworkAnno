package com.framworkannotation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.framworkannotation.meta.ActionInject;
import com.framworkannotation.meta.ViewInject;
import com.framworkannotation.meta.widget.EditTextController;
import com.framworkannotation.meta.widget.FieldMeta;
import com.framworkannotation.meta.widget.FormController_Activity;
import com.framworkannotation.model.BaseEntity;

public class FormActivityExample extends FormController_Activity
{
	public final FieldMeta ID = new FieldMeta(BaseEntity.ID, this);
	
	public final FieldMeta NAME = new FieldMeta(BaseEntity.NAME, this);

	@ViewInject(name = "id", id = R.id.example_id, preferredUIClass = EditTextController.class)
	EditText id_text;
	@ViewInject(name = "name", id = R.id.example_name, preferredUIClass = EditTextController.class)
	EditText name_text;
	
	@ActionInject(id= R.id.button1, type = "Button", onClick = "test_on_click")
	Button testBtnButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_activity_example);
		BaseEntity entity = new BaseEntity();
		entity.setId(11);
		entity.setName("testEntity");
		setDataSourceObject(entity);
	}
	
	public void test_on_click()
	{
		
		System.out.print("");
		System.out.print("");
	}
	
	
}
