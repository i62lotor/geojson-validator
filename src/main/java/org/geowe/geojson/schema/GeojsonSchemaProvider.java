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
package org.geowe.geojson.schema;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.geowe.geojson.validation.GeojsonValidator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

/**
 * Provides the schema against which to validate.
 * <BR>Available schemas:
 * 	<BR>GEOJSON_SCHEMA: resources/schemas/geojson.json
 * 	<BR>GEOMETRY SCHEMA: resources/schemas/geometry.json
 * 	<BR>CRS_SCHEMA: resources/schemas/crs.json
 * 	<BR>BBOX_SCHEMA: resources/schemas/bbox.json
 * 
 * @author lotor
 *
 */
public class GeojsonSchemaProvider {

	private static final Logger LOG = Logger.getLogger(GeojsonSchemaProvider.class);

	//TODO: perhaps an enum is better idea
	public static String GEOJSON_SCHEMA = "/schemas/geojson.json";
	public static String GEOMETRY_SCHEMA = "/schemas/geometry.json";
	public static String CRS_SCHEMA = "/schemas/crs.json";
	public static String BBOX_SCHEMA = "/schemas/bbox.json";

	
	private Map<String, JsonNode> schemas = new HashMap<String, JsonNode>();
	private JsonSchemaFactory jsonSchemaFactory;

	/**
	 * Build a GeojsonSchemaProvider loading available schemas.
	 */
	public GeojsonSchemaProvider() {
		super();
		load();
	}

	private void load() {
		schemas.put(GEOJSON_SCHEMA, loadSchema(GEOJSON_SCHEMA));
		schemas.put(GEOMETRY_SCHEMA, loadSchema(GEOMETRY_SCHEMA));
		schemas.put(CRS_SCHEMA, loadSchema(CRS_SCHEMA));
		schemas.put(BBOX_SCHEMA, loadSchema(BBOX_SCHEMA));

		jsonSchemaFactory = JsonSchemaFactory.newBuilder()
				.setLoadingConfiguration(configure()).freeze();
	}

	private JsonNode loadSchema(String jsonSchemaName) {
		JsonNode jsonNode = null;
		try {
			InputStream is = GeojsonValidator.class.getResourceAsStream(jsonSchemaName);
			jsonNode = new ObjectMapper().readTree(is);

		} catch (IOException e) {
			LOG.error("Error obtaining schema: " + jsonSchemaName + ": " + e);			
		}
		return jsonNode;
	}

	private LoadingConfiguration configure() {
		return LoadingConfiguration.newBuilder().preloadSchema(this.schemas.get(GEOJSON_SCHEMA))
				.preloadSchema(this.schemas.get(GEOMETRY_SCHEMA)).preloadSchema(this.schemas.get(CRS_SCHEMA))
				.preloadSchema(this.schemas.get(BBOX_SCHEMA)).setEnableCache(true).freeze();
	}

	/**
	 * Obtain a valid geojson schema.
	 * @param schemaName values: GeojsonSchemaProvider.GEOMETRY_SCHEMA, GeojsonSchemaProvider.GEOJSON_SCHEMA,
	 * GeojsonSchemaProvider.CRS_SCHEMA, GeojsonSchemaProvider.BBOX_SCHEMA
	 * @return JsonSchema
	 * @throws ProcessingException
	 */
	public JsonSchema getJsonSchema(String schemaName) throws ProcessingException {
		return jsonSchemaFactory.getJsonSchema(schemas.get(schemaName));
	}

}
