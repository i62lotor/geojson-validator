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
import java.util.stream.Stream;

import org.geowe.geojson.validation.GeojsonValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger LOG = LoggerFactory.getLogger(GeojsonSchemaProvider.class);

	//TODO: perhaps externalize to a property file is a better idea
	public enum Schema {
		GEOJSON_SCHEMA("/schemas/geojson.json"),
		GEOMETRY_SCHEMA("/schemas/geometry.json"),
		CRS_SCHEMA("/schemas/crs.json"),
		BBOX_SCHEMA("/schemas/bbox.json");
		
		private String source;
		
		private Schema(String source){
			this.source = source;
		}
		
		public String getSource(){
			return source;
		}
	}
	
	private Map<Schema, JsonNode> schemas = new HashMap<Schema, JsonNode>();
	private JsonSchemaFactory jsonSchemaFactory;

	/**
	 * Build a GeojsonSchemaProvider loading available schemas.
	 */
	public GeojsonSchemaProvider() {
		super();
		load();
	}

	private void load() {
		Stream.of(Schema.values()).forEach(s -> schemas.put(s, loadSchema(s)));
		
		jsonSchemaFactory = JsonSchemaFactory.newBuilder()
				.setLoadingConfiguration(configure()).freeze();
	}

	private JsonNode loadSchema(Schema jsonSchemaName) {
		JsonNode jsonNode = null;
		try {
			InputStream is = GeojsonValidator.class.getResourceAsStream(jsonSchemaName.getSource());
			jsonNode = new ObjectMapper().readTree(is);

		} catch (IOException e) {
			LOG.error("Error obtaining schema: " + jsonSchemaName + ": " + e);			
		}
		return jsonNode;
	}

	private LoadingConfiguration configure() {
		return LoadingConfiguration.newBuilder().preloadSchema(this.schemas.get(Schema.GEOJSON_SCHEMA))
				.preloadSchema(this.schemas.get(Schema.GEOMETRY_SCHEMA)).preloadSchema(this.schemas.get(Schema.CRS_SCHEMA))
				.preloadSchema(this.schemas.get(Schema.BBOX_SCHEMA)).setEnableCache(true).freeze();
	}

	/**
	 * Obtain a valid geojson schema.
	 * @param schema values: Schema.GEOMETRY_SCHEMA, Schema..GEOJSON_SCHEMA,
	 * Schema..CRS_SCHEMA, Schema..BBOX_SCHEMA
	 * @return JsonSchema
	 * @throws ProcessingException
	 */
	public JsonSchema getJsonSchema(Schema schema) throws ProcessingException {
		return jsonSchemaFactory.getJsonSchema(schemas.get(schema));
	}

}
