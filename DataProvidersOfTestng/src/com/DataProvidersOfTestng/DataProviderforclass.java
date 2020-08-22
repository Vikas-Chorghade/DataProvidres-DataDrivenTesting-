package com.DataProvidersOfTestng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderforclass {

	@Test(dataProvider = "TestData")
	public void signin(int id, String name, float sal) {

		System.out.println(id + "\t" + name + "\t" + sal);
	}

	@DataProvider(name = "TestData")
	public Object[][] m1() {
		Object[][] values = { { 1, "Vikas", 50000.66f },
				{ 2, "Vilas", 69694.44f },
				{ 3, "Vinay", 65594.44f },
				{ 4, "Jay", 45694.44f } };

		return values;

	}

}
