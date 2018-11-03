/*******************************************************************************
 * Copyright 2017 Rafael López Torres
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.geowe.geojson.validation;

import java.io.IOException;
import java.util.Optional;

import org.geowe.geojson.schema.GeojsonSchemaProvider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.vividsolutions.jts.geom.Geometry;

import io.jeo.geojson.GeoJSONReader;

/**
 * GeoJSON validator. Validate geojson geometries and features schema using
 * json-schema-validator and JTS.
 * 
 * @author lotor
 *
 */
public class GeojsonValidator {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Validate geoJSON schema according to GeojsonSchemaProvider
	 * resources/schemas/geojson.json schema.
	 * 
	 * @param geojson
	 * @return Optional<ProcessingReport> ProcessingReport
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public Optional<ProcessingReport> validateSchema(String geojson) throws ProcessingException, IOException {

		return validate(geojson,
				new GeojsonSchemaProvider().getJsonSchema(GeojsonSchemaProvider.Schema.GEOJSON_SCHEMA));
	}

	/**
	 * Validate geoJSON Geometry schema according to
	 * resources/schemas/geometry.json
	 * 
	 * @param geojson
	 * @return Optional<ProcessingReport> ProcessingReport
	 * @throws ProcessingException
	 * @throws IOException
	 */
	public Optional<ProcessingReport> validateGeometrySchema(String geojson) throws ProcessingException, IOException {

		return validate(geojson,
				new GeojsonSchemaProvider().getJsonSchema(GeojsonSchemaProvider.Schema.GEOMETRY_SCHEMA));
	}

	private Optional<ProcessingReport> validate(String geojson, JsonSchema jsonSchema)
			throws ProcessingException, IOException {
		JsonNode geoJsonNode = objectMapper.readTree(geojson);

		final ProcessingReport validation = jsonSchema.validate(geoJsonNode);
		return Optional.of(validation);
	}

	/**
	 * Tests whether the Geometry component is topologically valid, according to
	 * the OGC SFS specification, using JTS implementation<br>
	 * Validate: Geometry, Point, LineString, Polygon, GeometryCollection and Feature.
	 * @param geojson
	 * @return true if geometry is valid
	 * @throws IOException
	 * 
	 */
	public boolean isGeometryValid(String geojson) throws IOException {

		boolean isValid = false;
		Optional<Geometry> geomOpt = getGeometry(geojson);
		if(geomOpt.isPresent()){
			isValid = geomOpt.get().isValid();
		}
		return isValid;
	}
	
	private Optional<Geometry> getGeometry(String geojson) throws IOException{
		GeoJSONReader reader = new GeoJSONReader();
		Geometry geom = null;
		if ("Feature".equals(getGeojsonType(geojson))) {
			geom = reader.feature(geojson).geometry();
		} else {
			geom = reader.geometry(geojson);
		}
		return Optional.ofNullable(geom);
	}
	
	private String getGeojsonType(String geojson) throws IOException{
		JsonNode geoJsonNode = objectMapper.readTree(geojson);
		return geoJsonNode.get("type").textValue();
	}
	
}
