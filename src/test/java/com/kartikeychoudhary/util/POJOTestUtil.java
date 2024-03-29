package com.kartikeychoudhary.util;

import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class POJOTestUtil {
	private static final Validator ACCESS_VALIDATOR = ValidatorBuilder.create().with(new GetterTester())
			.with(new SetterTester()).build();

	public static void validateAccessors(final Class<?> c) {
		ACCESS_VALIDATOR.validate(PojoClassFactory.getPojoClass(c));
	}
}
