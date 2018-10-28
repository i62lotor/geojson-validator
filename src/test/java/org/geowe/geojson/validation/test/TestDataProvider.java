package org.geowe.geojson.validation.test;

public class TestDataProvider {

	
	public static final String VALID_POLYGON_GEOJSON = "{\"type\": \"Polygon\",\"coordinates\": [[[35.250506,31.820620],[35.250516,31.820696],[35.250573,31.820733],[35.250599,31.820692],[35.250601,31.820623],[35.250510,31.820619],[35.250506,31.820620]]]}";
	public static final String INVALID_POLYGON_GEOJSON = "{\"t-ype\": \"Poly-gon\",\"coordinates\": [[[35.250506,31.820620],[35.250516,31.820696],[35.250573,31.820733],[35.250599,31.820692],[35.250601,31.820623],[35.250510,31.820619],[35.250506,31.820620]]]}";
	public static final String VALID_GEOMETRY_COLLECTION = "{ \"type\": \"GeometryCollection\",\"geometries\": [{ \"type\": \"Point\",  \"coordinates\": [100.0, 0.0]  },{ \"type\": \"LineString\",  \"coordinates\": [ [101.0, 0.0], [102.0, 1.0] ]  }]  }";
	public static final String VALID_FEATURE_COLLECTION = "{ \"type\": \"FeatureCollection\",\"features\": [{ \"type\": \"Feature\",  \"geometry\": {\"type\": \"Point\", \"coordinates\": [102.0, 0.5]},  \"properties\": {\"prop0\": \"value0\"}  },{ \"type\": \"Feature\",  \"geometry\": {\"type\": \"LineString\",\"coordinates\": [[102.0, 0.0], [103.0, 1.0], [104.0, 0.0], [105.0, 1.0]]},  \"properties\": {\"prop0\": \"value0\",\"prop1\": 0.0}  },{ \"type\": \"Feature\",\"geometry\": {\"type\": \"Polygon\",\"coordinates\": [ [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0],[100.0, 1.0], [100.0, 0.0] ] ]},\"properties\": {\"prop0\": \"value0\",\"prop1\": {\"this\": \"that\"}}} ]}";
	public static final String VALID_FEATURE = "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[102,0.5]},\"properties\":{\"prop0\":\"value0\",\"prop1\":\"value1\"}}";
	
	public static final String VALID_FEATURE_POINT_GEOJSON = "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[101.3818359375,-0.3790255558308115]},\"properties\":{\"prop0\":\"value0\",\"prop1\":\"value1\"}}";
	public static final String VALID_POINT_GEOMETRY = "{\"type\":\"Point\",\"coordinates\":[101.3818359375,-0.3790255558308115]},\"properties\":{\"prop0\":\"value0\",\"prop1\":\"value1\"}";
}
