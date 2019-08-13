# <center> Object pool </center>

Es un patrón de diseño que utiliza un conjunto de objetos (pool) **inicializados** preparados para su uso, lo cual es más efectivo que crear y destruir objetos bajo demanda.

Los objetos no se **crean** (excepto la primera vez) ni se **destruyen** solo se **reciclan**

## Secuencia de asignación de objetos
  1. Un cliente solicita un objeto al pool
  2. El pool le asigna un objeto disponible
  3. El cliente realiza operaciones con el objeto
  4. El cliente devuelve el objeto, y el pool elimina las referencias anteriores

![enter image description here](https://raw.githubusercontent.com/santiagoQuinter/ObjectPool/master/OP.png)

## Casos particulares
### El pool no tiene objetos disponibles
Crear un nuevo objeto y se lo asigna al cliente que lo solicitó

### Número maximo de objetos a crear
Un pool puede tener un limite de objetos a crear, con el fin de tener mayor control sobre la memoria utilizada

### El pool alcanzó el número maximo de objetos a crear
Tiene dos posibilidades cuando llega una nueva solicitud: 
1.	Lanzar una exception y bloquear al hilo
2.	Esperar hasta que un objeto sea liberado de la pool

# ¿Cuándo usar Object Pool?
* Cuando instanciar objetos tiene un coste alto
	* Conexiones a bases de datos	
	* Conexiones a sockets
* Cada objeto solo se necesita durante un corto perido de tiempo
	* Recursos gráficos
* La tasa de creación de objetos es alta 
	* Recursos gráficos

## Ventajas
* Aumenta el rendimiento
* Limita el número de procesos que puede ejecutarse al mismo tiempo en una aplicación multihilo

## Desventajas
* Aumenta la complejidad de la implementación 
* Se ocupa más memoria al mantener objetos inicializados
