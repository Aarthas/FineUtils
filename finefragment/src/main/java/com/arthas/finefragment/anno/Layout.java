package com.arthas.finefragment.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Layout
{
	int value();

	int DvBottom() default 0;

	int DvTop()default 0;
}
