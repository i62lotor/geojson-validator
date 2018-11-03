package org.geowe.geojson.validation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({  
  GeojsonSchemaValidatorTest.class,
  GeojsonGeometrySchemaValidatorTest.class,
  GeojsonGeometryValidatorTest.class
})
public class TestSuite {

}
