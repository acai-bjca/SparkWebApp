## Spark App 

En este repositorio, se implementó una aplicación web con Spark desplegada en Heroku. Esta aplicación hace uso de un repositorio, antes creado, Ejercicio-1-AREP, el cual calcula la media y la desviación estándar, haciendo uso de una linked list, el cual puede encontrar en <https://github.com/acai-bjca/Ejercicio-1-AREP.git>.

El link de la aplicación es: <https://sparkwebarep.herokuapp.com/>

![](src/main/resources/componentes.png)

___
### Insignias

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/31b363d5a6fe4c9b8eb2d1cd22bc9c37)](https://www.codacy.com/app/acai-bjca/Ejercicio-1-AREP?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=acai-bjca/Ejercicio-1-AREP&amp;utm_campaign=Badge_Grade)
___
### Documentación

Para leer la documentación diríjase a: <https://github.com/acai-bjca/SparkWebApp/tree/master/src/main/resources/documentacion/apidocs/edu/escuelaing/arep/spark>

___
### Uso del proyecto como librería
Si desea usar éste repositorio como librería en su proyecto, realice los siguientes pasos:

• Descargue o clone él repositorio SparkWebApp: <https://github.com/acai-bjca/SparkWebApp.git>

• Agregue la siguiente dependencia al pom de su proyecto:
``` xml
 <dependency>
	<groupId>edu.escuelaing.arep</groupId>
    <artifactId>SparkWebApp</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

• Importe el proyecto en la clase que lo requiera:
import edu.escuelaing.arep.*;

___
### Ejecutando las pruebas

Para ejecutar las pruebas puede usar el comando:
>mvn package


___
### Pruebas
Para mostrar la correcta funcionalidad de la aplicación, se realizó una prueba manual.
Primero se ingresaron los números separados por ','.

![](src/main/resources/index.png)

A continuación, se puede ver el resultado generado una vez se dió clic en el botón Calcular.

![](src/main/resources/calculo.png)
___
### Construido con

• Java  
• [Maven] (https://maven.apache.org/) - Gestión de dependencias

___
### Autor

**Amalia Inés Alfonso Campuzano** 

Estudiante de la Escuela Colombiana de Ingeniería Julio Garavito

Ingeniería de Sistemas
___
### Licencia

Este proyecto está licenciado bajo la Licencia GNU - vea el archivo [LICENSE.md] (LICENSE.md) para más detalles.

