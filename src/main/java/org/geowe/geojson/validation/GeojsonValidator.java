package org.geowe.geojson.validation;

import java.io.IOException;
import java.util.Optional;

import org.geowe.geojson.schema.GeojsonSchemaProvider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;

/**
 * GeoJSON validator. Validate geojson geometries and features schema using
 * json-schema-validator.
 * 
 * @author lotor
 *
 */
public class GeojsonValidator {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Validate geoJSON schema according to GeojsonSchemaProvider resources/schemas/geojson.json
	 * schema.
	 * 
	 * @param geojson
	 * @return Optional<ProcessingReport> ProcessingReport
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public Optional<ProcessingReport> validateSchema(String geojson) throws ProcessingException, IOException{

		return validate(geojson, new GeojsonSchemaProvider()
				.getJsonSchema(GeojsonSchemaProvider.GEOJSON_SCHEMA));
	}

	/**
	 * Validate geoJSON Geometry schema according to resources/schemas/geometry.json
	 * @param geojson
	 * @return Optional<ProcessingReport> ProcessingReport
	 * @throws ProcessingException
	 * @throws IOException
	 */
	public Optional<ProcessingReport> validateGeometry(String geojson) throws ProcessingException, IOException {

		return validate(geojson, new GeojsonSchemaProvider()
				.getJsonSchema(GeojsonSchemaProvider.GEOMETRY_SCHEMA));
	}
	
	
	private Optional<ProcessingReport> validate(String geojson, JsonSchema jsonSchema) throws ProcessingException, IOException{
		JsonNode geoJsonNode = objectMapper.readTree(geojson);

		final ProcessingReport validation = jsonSchema.validate(geoJsonNode);
		return Optional.of(validation);
	}

}
