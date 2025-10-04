_Hecho por Cristian Stiven Galeano Barbosa_

# Repositorio

Allí también se encuentra el pdf y el archivo cisco por sí quieres echarle una revisada.

# Actividad 1 - Monitoreo de redes.

Para iniciar con esta actividad daré un contexto breve del ambiente donde se desarrollará, consiste en el host que es windows 11 y una VM ubuntu 

## IP Windows
![[Pasted image 20251002154821.png]]

## IP ubuntu
![[Pasted image 20251002154805.png]]

## Tracert 

La función de tracert consiste en rastrea paso a paso los saltos que se va haciendo por las ip's hasta llegar a la IP de destino, se basa en tres RTT (Round trip time)  y nombre del host el DNS resuelto o la dirección IP. 

![[Pasted image 20251002153827.png]]

Vamos a desglosarlo linea a linea.
1. Router domestico de mi casa es decir la gateway local donde tuvo tres resultados un pico mayor de 32ms y los otros dos son 6 ms y 7 ms.
2. Salida al internet nos retorna la IP del router del ISP.
3. No hay respuesta a ICMP TTL.
4. Llegamos a la red de Facebook donde tiene lugar en Bogotá infiriendo por el nombre, brindando una respuesta promedio de 10 ms
5. Salta a otro router interno de Facebook ya en Bogotá, aquí existe una posible congestión por el pico de 25 ms.
6. Volvemos a saltar a otro switch interno en la red de Facebook.
7. Nodo intermedio el cual lo más probable es que nos esté redirigiendo al sevidor destino de Facebook.
8. Por ultimo salto llegamos al servidor de facebook el cual tiene una buena conectividad ronda en 10 ms.
### Desde Windows a ubuntu

![[Pasted image 20251002154722.png]]
#### Análisis

Podemos evidenciar que porque estamos en la misma red solo requiere de un salto y demora <1 ms porque esta dentro de mi misma red LAN.

### Desde ubuntu a windows
![[Pasted image 20251002155231.png]]
#### Análisis

Estaba leyendo un poco sobre esto y realmente me pareció interesante que Ubuntu emplea UDP para traceroute mientras que windows emplea por defecto ICMP, por ende es necesario tener abierta una regla en el firewall para permitir la conexión y del mismo modo es un solo salto, porque esta dentro de la misma red LAN.

## Online
![[Pasted image 20251002155636.png]]

No tiene nada de parecido, porque seguramente el servidor el cual se hospeda esta aplicación web es en estados unidos por ende no tiene ninguna relación con lo que estamos viendo nosotros.
![[Pasted image 20251002155828.png]]
## Iperf3

Es una herramienta que consiste en medir el rendimiento de red, la cual permite observar cómo el control de congestión de TCP actúa cuando se genera tráfico intenso, este soporta lo que es TCP y UDP.

Se descarga por sudo apt install en ubuntu.
![[Pasted image 20251002160450.png]]

y en windows desde la página oficial.
### Desde Windows a ubuntu

![[Pasted image 20251002160704.png]]

-s es para el modo servidor y -p para especificar el puerto que vamos a usar en este caso 5201.

![[Pasted image 20251002160715.png]]
-c es para especificar la IP del servidor -bidir nos dice que es una prueba bidireccional, envio y recepción simultánea.
#### Análisis

A través de esto podemos interpretar su funcionamiento que consiste en "interval" donde ser representa el tiempo de medición, Transfer donde son los datos enviados en ese intervalo, Bitrate el cual es la velocidad promedio, Retr el cual consiste en retransmisiones en el caso de TCP indica congestión

## Online - Speedtest Ookla

### Ubuntu

![[Pasted image 20251002162311.png]]

### Windows
![[Pasted image 20251002162334.png]]

iPerf3 es una herramienta diseñada para medir el rendimiento de red entre dos hosts de manera controlada, permitiendo ajustar parámetros como el protocolo (TCP o UDP), el número de flujos, el intervalo de reporte y la duración de la prueba. Esto lo hace ideal para laboratorios donde se requiere analizar el comportamiento del control de congestión y la capacidad real de la red interna (LAN).

Por otro lado, Speedtest by Ookla es una herramienta en línea que mide la velocidad de subida, bajada y la latencia hacia servidores externos en Internet. Su ventaja es que no requiere instalación y es muy fácil de usar, pero no permite configuraciones avanzadas ni pruebas específicas en la red local. Mientras que iPerf3 permite evaluar el rendimiento interno de la red (LAN) y observar cómo TCP maneja la congestión bajo diferentes condiciones. Speedtest permite medir el rendimiento hacia Internet, mostrando la capacidad real del enlace del ISP y la latencia hacia servidores externos.
# Actividad 2 - Ping automatizado.

# Repositorio

https://github.com/CristianSGaleanoB/computer-networks-2-week-5/blob/main/src/AutomatizationPing.java
## Evidencia
![[Pasted image 20251002233918.png]]
## Análisis.

Se realizaron 10 pruebas de ping a ocho servidores DNS: hukumusume.com, bbc.co.uk, amazon.fr, lanacion.com.ar, elmundo.es, baidu.com, sis.gov.eg y google.de. A continuación, presentaré un resumen e interpretación de los resultados en texto plano:

#### Resumen general:

- **Servidores accesibles**: Cinco de los ocho servidores (bbc.co.uk, amazon.fr, elmundo.es, baidu.com y google.de) fueron accesibles en todas las pruebas.
- **Servidores no accesibles**: Tres servidores (hukumusume.com, lanacion.com.ar y sis.gov.eg) resultaron inalcanzables en todas las pruebas.
- **Tiempos de respuesta**: Los servidores accesibles mostraron tiempos de respuesta consistentes, con variaciones mínimas entre pruebas.

#### Análisis por servidor:

1. **hukumusume.com**:
    - **Estado**: Inalcanzable en todas las pruebas (10/10).
    - **Interpretación**: Este servidor no respondió a ninguna solicitud de ping, lo que podría indicar que está fuera de servicio, bloqueado, o que la configuración de red impide el acceso.
2. **bbc.co.uk**:
    - **Estado**: Accesible en todas las pruebas.
    - **Tiempo promedio**: ~8.2 ms (rango: 6–10 ms).
    - **Interpretación**: Respuesta rápida y estable, indicando una conexión confiable y posiblemente una ubicación geográfica cercana o una infraestructura optimizada.
3. **amazon.fr**:
    - **Estado**: Accesible en todas las pruebas.
    - **Tiempo promedio**: ~175.4 ms (rango: 171–187 ms).
    - **Interpretación**: Tiempos de respuesta más altos, posiblemente debido a la distancia geográfica o la carga del servidor. La variación es moderada, lo que sugiere estabilidad relativa.
4. **lanacion.com.ar**:
    - **Estado**: Inalcanzable en todas las pruebas (10/10).
    - **Interpretación**: Similar a hukumusume.com, este servidor no respondió, lo que podría deberse a problemas técnicos, restricciones de red o falta de soporte para ping.
5. **elmundo.es**:
    - **Estado**: Accesible en todas las pruebas.
    - **Tiempo promedio**: ~169.1 ms (rango: 166–187 ms).
    - **Interpretación**: Tiempos de respuesta similares a amazon.fr, con una ligera variación. La conexión es estable, pero los tiempos sugieren una latencia moderada.
6. **baidu.com**:
    - **Estado**: Accesible en todas las pruebas.
    - **Tiempo promedio**: ~296.8 ms (rango: 293–320 ms).
    - **Interpretación**: Los tiempos de respuesta son los más altos entre los servidores accesibles, probablemente debido a la distancia geográfica (servidor ubicado en China) o restricciones de red. La conexión es consistente, pero con mayor latencia.
7. **sis.gov.eg**:
    - **Estado**: Inalcanzable en todas las pruebas (10/10).
    - **Interpretación**: No se obtuvo respuesta, lo que podría indicar problemas de accesibilidad, bloqueos de red o que el servidor no permite solicitudes de ping.
8. **google.de**:
    - **Estado**: Accesible en todas las pruebas.
    - **Tiempo promedio**: ~10.5 ms (rango: 7–34 ms).
    - **Interpretación**: Respuesta rápida, aunque con una anomalía en la prueba 2 (34 ms). Esto sugiere una conexión generalmente eficiente, con una posible fluctuación momentánea.
9. **elespectador.com**:
    - **Estado**: Accesible en todas las pruebas.
    - **Tiempo promedio**: ~14.6 ms (rango: 6–46 ms).
    - **Interpretación**: Respuesta rápida en general, con una fluctuación notable en la prueba 4 (46 ms). Esto podría indicar congestión momentánea o variabilidad en la red, pero la conexión es mayormente estable.

#### Observaciones generales:

- **Estabilidad de la red**: Los servidores accesibles muestran tiempos de respuesta consistentes, con variaciones menores en la mayoría de los casos, salvo algunas anomalías (google.de en prueba 2 y elespectador.com en prueba 4).
- **Problemas de accesibilidad**: Los servidores hukumusume.com, lanacion.com.ar y sis.gov.eg no respondieron en ninguna prueba, lo que sugiere problemas estructurales, configuraciones que bloquean el ping o restricciones geográficas/red.
- **Latencia geográfica**: Los servidores con tiempos de respuesta más altos (baidu.com, amazon.fr, elmundo.es) podrían estar más alejados geográficamente o tener rutas de red más complejas, mientras que bbc.co.uk, google.de y elespectador.com muestran latencias bajas, indicando cercanía o infraestructura optimizada.
# Actividad 3 - Packet Tracer

## 1. Realizar la topología

![[Pasted image 20251003144419.png]]

Está es la topología inical en la cual cumplo con los 20 dispositivos que se solicitan, estaba investigando decidí implementar "router on a stick" el cual se basa en un solo router donde se utiliza para manejar el ruteo entre múltiples VLANs (Redes virtuales como lo vimos en clase). A través de un único enlace físico que conecta el router a un switch. Esto se configura como un trunk segú lo que leí el trunk es enlace entre switches o entre un switch y un router. Los cuales se configura para transportar el tráfico de todas las VLANs. En el router, se crean subinterfaces, cada una asociada a una VLAN específica, permitiendo que el router actúe como gateway para cada red segmentada.
## 2. Mapeo de red  - ip computador e ip gateway

Para el laboratorio voy a definir las 4 VLANs como lo planteamos en la clase del Jueves, 
- VLAN 10: Administración (PCs 0-4, subnet 192.168.10.0/24, gateway 192.168.10.1)
- VLAN 20: Ventas (PCs 5-9, subnet 192.168.20.0/24, gateway 192.168.20.1)
- VLAN 30: Ingeniería (PCs 10-14, subnet 192.168.30.0/24, gateway 192.168.30.1)
- VLAN 40: Servidores (PCs 15-19 y Server0, subnet 192.168.40.0/24, gateway 192.168.40.1). Los PCs 15-19 simulan dispositivos en el data center.

De este modo agregaré las IP's estáticas a cada computador de mi red, mostraré el ejemplo de una para evitar sobrecargar la documentación.
![[Pasted image 20251003145703.png]]
## 3. Levanta las interfaces

En todos los dispositivos se accede al CLI.
### Router
En el router: enable > conf t > interface > no shutdown > end
![[Pasted image 20251003151111.png]]
### Switches
En cada switch: enable > conf t > interface range Fa0/1 - 24  > no shutdown > end
![[Pasted image 20251003151736.png]]

### Evidencia

Esta funcionando correctamente hay un cambio entre el cable que se conecta entre switches, porque yo habilitaba las interfaces para la conexión y no funcionaba investigando para el tipo de conexión que es entre un dispositivo igual se usa el cable crossover.

![[Pasted image 20251003201505.png]]

## 4. Configurar router
![[Pasted image 20251003202511.png]]

Configuramos el router para usar encapsulation dot1Q, que implementa el protocolo IEEE 802.1Q, el cual permite segmentar redes Ethernet mediante VLANs.
## 5. Configurar Switches

En el switch central desarrollamos lo siguiente:
![[Pasted image 20251003204316.png]]
- Se crearon 4 VLANs:
    
    - VLAN 10: Administración
    - VLAN 20: Ventas
    - VLAN 30: Ingeniería
    - VLAN 40: Servidores
    
    - Las interfaces FastEthernet0/1 a 4/1 se configuraron en modo **trunk**, permitiendo transportar tráfico de las VLANs 10, 20, 30 y 40.
    - La interfaz FastEthernet6/1 se configuró en modo **access** y se asignó correctamente a la VLAN 40 (Servidores).
## 6. Configurar Vlans

La configuración a lo largo de cada VLAN será de este modo solo que cambiará el número de vlan al igual que antes no se sobrecargará con imágenes pero será el mismo desarrollo, básicamente  para configurar desde el switch de los VLANs.
![[Pasted image 20251003210822.png]]
## 7. Probar Ping y que tenga concordancia con las VLans

Se puede ver como funciona correctamente el ping entre la misma VLAN y no entre otras VLANs, retornando ping entre la misma y no retornando nada con otras, en este caso haré las pruebas con todas porque es necesario ver que todas estén funcionando.

### VLAN 10
![[Pasted image 20251003213923.png]]

### VLAN 20

![[Pasted image 20251003214132.png]]
### VLAN 30
![[Pasted image 20251003214400.png]]
### VLAN 40
![[Pasted image 20251003214507.png]]


# Conclusión

Bueno realmente esta conclusión la hago ya derrotado por CISCO realmente jaja, pero fue un laboratorio realmente extenso donde se pudo poner en practica demasiadas temáticas realmente esta ultima fue la que más confundido me dejo porque hay demasiadas rutas para poder hacerlo, y al final tuve ciertos problemas pero es lo normal cuando se trata de Packet Tracer, mientras que en las otras dos actividades realmente pude ir más a fondo de lo que nos devuelve un traceroute y como este realmente funcionará para mi CAPSTONE, además del ping donde hay ciertas DNS bloqueadas por mi ubicación y al hacer el ping desde Colombia. Sin embargo fueron actividades muy buenas pienso que para sacarle el provecho y sacarlo con más calma debí hacer todo lo posible desde el martes para poder llegar con miles de preguntas de como hacer esta parte de packet tracer pero de igual forma veré si lo puedo aplicar para siguientes labs.