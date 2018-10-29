package org.geowe.geojson.validation.test;

import java.io.IOException;

import org.geowe.geojson.validation.GeojsonValidator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;

public class GeojsonGeometrySchemaValidatorTest {

	private static final Logger LOG = LoggerFactory.getLogger(GeojsonGeometrySchemaValidatorTest.class);


	@Test
	public void Given_validGeojson_When_validateGeometry_Expect_success() {
		GeojsonValidator validator = new GeojsonValidator();
		try {
			Assert.assertTrue(validator.validateGeometrySchema(TestDataProvider.VALID_POINT_GEOMETRY).get().isSuccess());
		} catch (IOException | ProcessingException e) {
			LOG.error("Validation fails: " + e);
		}
	}
	
	@Test
	public void Given_invalidGeometryGeojson_When_validateGeometry_Expect_fails() {
		GeojsonValidator validator = new GeojsonValidator();
		try {
			ProcessingReport validationResult = validator.validateGeometrySchema(TestDataProvider.VALID_FEATURE_POINT_GEOJSON)
					.get();
			LOG.info(validationResult.toString());
			
			Assert.assertFalse(validationResult.isSuccess());			
		} catch (IOException | ProcessingException e) {
			LOG.error("Validation fails: " + e);
		}
	}
}
