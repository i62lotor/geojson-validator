# geojson-validator
geojson validator java library. Se apoya en [json-schema-validator](https://json-schema.org/)

## Objetivo
Proporcionar una utilidad para validar el esquema de un GeoJSON. Incluyendo geometría y feature.

## Tecnología
- Java 8

## Construir el software
Para construir el software podemos usar maven de la siguiente forma:

`mvn clean package`

Se generará un jar en el directorio target.

## Uso
Puedes instalar el artefacto en tu repositorio local de maven:

`mvn install`

luego incluir la dependencia en tu proyecto

```
<dependency>
	<groupId>org.geowe.geojson</groupId>
	<artifactId>geojson-validator</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Ejemplo:
Cómo validar un GeoJSON:

```
GeojsonValidator validator = new GeojsonValidator();
ProcessingReport validationResult = validator.validateSchema(geojson);
```

## Normas de contribución
Solo pido por favor que se sigua el patrón: **KISS**.

- Código fuente: podeis enviar vuestras PR, y encantado las incorporo, si al menos siguen un mínimo de limpieza de código, y algún test unitario.
- Testing: me parecen fundamentales. Si quieres hacer pruebas de rendimiento, estabilidad, carga, etc. ¡Genial!
- Documentación: ¿Quieres ayudarme?

## TODO
Aún en desarrollo pero lista para usar. Se acepta sugerencias de mejoras. Algunas que se me ocurren:
 
- Desacoplar los ficheros de esquemas (ahora están en resources/schemas/) de tal manera que si modifican los esquemas la librería no tenga que volver a distribuirse.

- Revisar los esquemas para verificar que cumplen con el [último RFC](http://geojson.org/).

- Revisar los TODOs en el código