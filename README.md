En esta carpeta se encuentra la resolución del ejercicio 1 utilizando el IDE Intellij. Al no haber usado nunca las tecnologías que se requerían en este ejercicio (Spring WebFlux, MongoDB) tuve que simplificar el código, por lo que solo se utiliza un objeto llamado Nodo que tiene los atributos equivalentes de nodeDesc. Además se ha utilizado la librería "Lomboc" para Java. He usado como guía varias páginas y videos para poder aprender a utilizar los distitos softwares:

- https://staging--okta-blog.netlify.app/blog/2018/09/24/reactive-apis-with-spring-webflux
- https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
- https://www.baeldung.com/spring-webflux
- https://www.youtube.com/watch?v=i0lJZeLdAi8
- https://www.youtube.com/watch?v=XigrXBLIafU

He realizado 3 conjuntos de tests, el primero comprueba las funciones interas de lista de objetos y de guardado mientras que el segundo y tercero testean los endpoints. 

Para la ejecución del código y de los tests, es requerido tener instalado MongoDB sobre Docker utilizando el puerto base (27017). Para inicializar el microservicio utilizando un IDE, solo hay que compilar y ejecutar el la clase main (Ejercicio1Application). Para acceder a ambos endpoints, se utiliza el puerto 8080 y la dirección /nodes. Si se accede a este enlace se mostrará un JSON con los datos almacenados en la BD. En el caso de que se requiera añadir un objeto nuevo, se deberá utilizar un gestor de mensajes con la misma dirección (/nodes). 

Para la ejecución de los tests, se requerirá todo lo descrito en el apartado anterior.
