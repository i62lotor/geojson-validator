package org.geowe.geojson.validation.test;

import java.io.IOException;

import org.geowe.geojson.validation.GeojsonValidator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;

public class GeojsonSchemaValidatorTest {

	private static final Logger LOG = LoggerFactory.getLogger(GeojsonSchemaValidatorTest.class);

	@Test
	public void Given_validGeojson_When_validate_Expect_success() {
		GeojsonValidator validator = new GeojsonValidator();
		try {
			Assert.assertTrue(validator.validateSchema(TestDataProvider.VALID_POLYGON_GEOJSON).get().isSuccess());
		} catch (IOException | ProcessingException e) {
			LOG.error("Validation fails: " + e);
		}
	}

	@Test
	public void Given_invalidGeojson_When_validate_Expect_fails() {
		GeojsonValidator validator = new GeojsonValidator();
		try {
			ProcessingReport validationResult = validator.validateSchema(TestDataProvider.INVALID_POLYGON_SCHEMA_GEOJSON)
					.get();
			LOG.info(validationResult.toString());
			Assert.assertFalse(validationResult.isSuccess());
		} catch (IOException | ProcessingException e) {
			LOG.error("Validation fails: " + e);
		}
	}

	@Test
	public void Given_validGeometryCollectionGeojson_When_validate_Expect_success() {
		GeojsonValidator validator = new GeojsonValidator();
		try {
			Assert.assertTrue(validator.validateSchema(TestDataProvider.VALID_GEOMETRY_COLLECTION).get().isSuccess());
		} catch (IOException | ProcessingException e) {
			LOG.error("Validation fails: " + e);
		}
	}

	@Test
	public void Given_validFeatureCollectionGeojson_When_validate_Expect_success() {
		GeojsonValidator validator = new GeojsonValidator();
		try {
			Assert.assertTrue(validator.validateSchema(TestDataProvider.VALID_FEATURE_COLLECTION).get().isSuccess());
		} catch (IOException | ProcessingException e) {
			LOG.error("Validation fails: " + e);
		}
	}

	@Test
	public void Given_validFeatureGeojson_When_validate_Expect_success() {
		GeojsonValidator validator = new GeojsonValidator();
		try {
			Assert.assertTrue(validator.validateSchema(TestDataProvider.VALID_FEATURE).get().isSuccess());
		} catch (IOException | ProcessingException e) {
			LOG.error("Validation fails: " + e);
		}
	}
}
