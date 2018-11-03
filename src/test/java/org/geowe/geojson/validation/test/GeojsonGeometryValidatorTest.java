package org.geowe.geojson.validation.test;

import java.io.IOException;

import org.geowe.geojson.validation.GeojsonValidator;
import org.junit.Assert;
import org.junit.Test;

public class GeojsonGeometryValidatorTest {



	@Test
	public void Given_validFeatureGeojson_When_validateFeatureGeometry_Expect_valid(){
		try {
			GeojsonValidator validator = new GeojsonValidator();
			Assert.assertTrue(validator.isGeometryValid(TestDataProvider.VALID_FEATURE_POINT_GEOJSON));
			Assert.assertTrue(validator.isGeometryValid(TestDataProvider.VALID_FEATURE));
		} catch (IOException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	
	@Test
	public void Given_validGeometryGeojson_When_validateGeometry_Expect_valid(){
		try {
			GeojsonValidator validator = new GeojsonValidator();
			Assert.assertTrue(validator.isGeometryValid(TestDataProvider.VALID_POINT_GEOJSON));
		} catch (IOException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
	
	@Test
	public void Given_invalidGeometryGeojson_When_validateGeometry_Expect_notValid(){
		try {
			GeojsonValidator validator = new GeojsonValidator();
			Assert.assertFalse(validator.isGeometryValid(TestDataProvider.INVALID_POLYGON_GEOJSON));
		} catch (IOException e) {
			Assert.fail();
			e.printStackTrace();
		}
		
	}
}
