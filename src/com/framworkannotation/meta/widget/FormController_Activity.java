package com.framworkannotation.meta.widget;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

import com.framworkannotation.meta.ActionInject;
import com.framworkannotation.meta.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FormController_Activity extends Activity implements IFormController
{
	private Object dataSourceObject;

	private Vector<FieldMeta> fieldMetaSet = new Vector<FieldMeta>();
	
	private Vector<IFieldController> inputControllerSet = new Vector<IFieldController>();


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void setContentView(int layoutResID)
	{
		super.setContentView(layoutResID);
		
		initInjectedView(this, getWindow().getDecorView());
		
		if (fieldMetaSet.size() != 0 && inputControllerSet.size() != 0)
		{
			bindingFieldnView();
		}
	}

	@Override
	public void registerFieldMeta(FieldMeta fmeta)
	{
		fieldMetaSet.add(fmeta);
	}

	@Override
	public void setDataSourceObject(Object datasourceObject)
	{
		this.dataSourceObject = datasourceObject;
		for(FieldMeta fmeta: fieldMetaSet)
		{
			fmeta.setDataSourceObject(datasourceObject);
		}
	}

	public Object getDataSourceObject()
	{
		return dataSourceObject;
	}

	public void initInjectedView(final Object injectSource, View sourceView)
	{
		Field[] fields = injectSource.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0)
		{
			try
			{
				for(Field field: fields)
				{
					field.setAccessible(true);
					//skip the rendered components
					if (field.get(injectSource) != null)
					{
						continue;
					}
					
					ViewInject viewAnno = field.getAnnotation(ViewInject.class);
					IFieldController controller = null;
					if (viewAnno != null)
					{
						int viewId = viewAnno.id();
						String fieldName = viewAnno.name();
						Class<?> preferredUIClass = viewAnno.preferredUIClass();
						View compView = sourceView.findViewById(viewId);
						field.set(injectSource, compView);
						Constructor<?> constructor = preferredUIClass.getConstructor(View.class, String.class);
						controller = (IFieldController) constructor.newInstance(compView, fieldName);
						inputControllerSet.add(controller);
					}
					
					ActionInject actionAnno = field.getAnnotation(ActionInject.class);
					if (actionAnno != null)
					{
						String type = actionAnno.type();
						final String onClick = actionAnno.onClick();
						int id = actionAnno.id();
						
						if (type.equals("Field") && controller != null)
						{
							controller.setOnClickListener(new OnClickListener()
							{
								
								@Override
								public void onClick(View paramView)
								{
									try
									{
										Method method = getCurrentClass().getDeclaredMethod(onClick);
										method.invoke(injectSource);
									}
									catch (NoSuchMethodException e)
									{
										e.printStackTrace();
									}
									catch (Exception e) 
									{
										e.printStackTrace();
									}
								}
							});
						}
						else if(type.equals("Button"))
						{
							Button btn = (Button) findViewById(id);
							btn.setOnClickListener(new OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									try
									{
										Method method = getCurrentClass().getDeclaredMethod(onClick);
										method.invoke(injectSource);
									}
									catch (NoSuchMethodException e)
									{
										e.printStackTrace();
									}
									catch (Exception e) 
									{
										e.printStackTrace();
									}
								}
							});
						}
					}
				}
			}
			catch(NoSuchMethodException ex)
			{
				ex.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void bindingFieldnView()
	{
		if (fieldMetaSet.size() != 0)
		{
			for (FieldMeta fmeta : fieldMetaSet)
			{
				for(IFieldController controller: inputControllerSet)
				{
					if(fmeta.getFieldFAP().getReflectionDescription().equals(controller.getFieldName()))
					{
						controller.setFieldMeta(fmeta);
					}
				}
			}
		}
	}
	
	private Class<? extends FormController_Activity> getCurrentClass()
	{
		return getClass();
	}
}
