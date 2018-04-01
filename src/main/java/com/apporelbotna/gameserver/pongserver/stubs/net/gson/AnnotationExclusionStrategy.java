package com.apporelbotna.gameserver.pongserver.stubs.net.gson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class AnnotationExclusionStrategy implements ExclusionStrategy
{
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Exclude
	{
		/* USAGE new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
		 * ----------- */
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f)
	{
		return f.getAnnotation(Exclude.class) != null;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz)
	{
		return false;
	}
}
