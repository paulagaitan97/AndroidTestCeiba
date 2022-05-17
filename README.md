# Prueba Técnica Android
En este repositorio se encuentra la implementacion del enunciado de la prueba tecnica para desarrollador android.
* Se hace uso de la api proporcionada en el enunciado.

# Explicacion de la implementacion
El desarrollo se segmentó en diferentes etapas:

* Etapa de creación, se realiza la creación del repositorio en el sistema de control de versiones de GIT y se enlaza con la base inicial del proyecto/aplicación creada en Android Studio.
* Etapa estructural de arquitectura multimodular, se genera una arquitectura multimodular donde se divide la aplicación en varios módulos, un módulo es un subconjunto de una única responsabilidad por lo cual se divide por característica. esto teniendo en mente que a futuro la aplicación crecerá en funcionalidad de este modo podrá hacerlo de una manera ordenada y confiable, sin afectar las feature creadas.
* Etapa de creación del módulo de Kotlin DSL (buildSrc), se crea el módulo que permite gestionar las dependencias de forma centralizada en todo el proyecto/aplicación.
* Etapa de implementación por feature, se crean los diferentes módulos correspondientes a las características, donde se hace uso de clean architecture, patrón MVVM, inyección de dependencias, Retrofit, JectpackCompose.
* Etapa de creación de los módulos core, se crea dos módulos core, core-ui donde se almacena las características compartidas por los diferentes módulos feature.
