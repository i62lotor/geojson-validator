package org.geowe.geojson.validation.test;

public class TestDataProvider {

	
	public static final String VALID_POLYGON_GEOJSON = "{\"type\": \"Polygon\",\"coordinates\": [[[35.250506,31.820620],[35.250516,31.820696],[35.250573,31.820733],[35.250599,31.820692],[35.250601,31.820623],[35.250510,31.820619],[35.250506,31.820620]]]}";
	public static final String INVALID_POLYGON_SCHEMA_GEOJSON = "{\"t-ype\": \"Poly-gon\",\"coordinates\": [[[35.250506,31.820620],[35.250516,31.820696],[35.250573,31.820733],[35.250599,31.820692],[35.250601,31.820623],[35.250510,31.820619],[35.250506,31.820620]]]}";
	public static final String VALID_GEOMETRY_COLLECTION = "{ \"type\": \"GeometryCollection\",\"geometries\": [{ \"type\": \"Point\",  \"coordinates\": [100.0, 0.0]  },{ \"type\": \"LineString\",  \"coordinates\": [ [101.0, 0.0], [102.0, 1.0] ]  }]  }";
	public static final String VALID_FEATURE_COLLECTION = "{ \"type\": \"FeatureCollection\",\"features\": [{ \"type\": \"Feature\",  \"geometry\": {\"type\": \"Point\", \"coordinates\": [102.0, 0.5]},  \"properties\": {\"prop0\": \"value0\"}  },{ \"type\": \"Feature\",  \"geometry\": {\"type\": \"LineString\",\"coordinates\": [[102.0, 0.0], [103.0, 1.0], [104.0, 0.0], [105.0, 1.0]]},  \"properties\": {\"prop0\": \"value0\",\"prop1\": 0.0}  },{ \"type\": \"Feature\",\"geometry\": {\"type\": \"Polygon\",\"coordinates\": [ [ [100.0, 0.0], [101.0, 0.0], [101.0, 1.0],[100.0, 1.0], [100.0, 0.0] ] ]},\"properties\": {\"prop0\": \"value0\",\"prop1\": {\"this\": \"that\"}}} ]}";
	public static final String VALID_FEATURE = "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[102,0.5]},\"properties\":{\"prop0\":\"value0\",\"prop1\":\"value1\"}}";
	
	public static final String VALID_FEATURE_POINT_GEOJSON = "{\"type\":\"Feature\",\"geometry\":{\"type\":\"Point\",\"coordinates\":[101.3818359375,-0.3790255558308115]},\"properties\":{\"prop0\":\"value0\",\"prop1\":\"value1\"}}";
	public static final String VALID_POINT_GEOJSON = "{\"type\":\"Point\",\"coordinates\":[101.3818359375,-0.3790255558308115]}";
	public static final String VALID_LINESTRING_GEOJSON = "{\"type\":\"LineString\",\"coordinates\":[[101.38170182704926,-0.37865273692742313],[101.38204783201218,-0.37883512315554363],[101.38206660747528,-0.3791006560396358],[101.38178765773773,-0.37937155321600796],[101.38165354728699,-0.3792454921547884]]}";
	public static final String INVALID_POLYGON_GEOJSON = "{\"type\":\"Polygon\",\"coordinates\":[[[101.38203978538513,-0.37824236790011306],[101.38233482837676,-0.3783147859651637],[101.38202100992203,-0.3785856831661152],[101.38224631547928,-0.3787385657411375],[101.38234555721283,-0.37850790080234714],[101.38203978538513,-0.37824236790011306]]]}";
	
	public static final String VALID_BBOX_GEOJSON = "{\"bbox\":[-180,-90,180,90]}";
	public static final String INVALID_BBOX_GEOJSON = "{\"bbox\":[-180,-90,180]}";
	
	public static final String VALID_CRS_GEOJSON = "{\"type\":\"name\",\"properties\":{\"name\":\"urn:ogc:def:crs:OGC:1.3:CRS84\"}}";
	public static final String INVALID_CRS_GEOJSON = "{\"typ-e\":\"name\",\"properties\":{\"name\":\"urn:ogc:def:crs:OGC:1.3:CRS84\"}}";
}

