# Split Car
## A simple project for share your route on Kotlin MVVM + live com.globant.splitcar.data + room + firebase + clean architecture

### With Split Car you can get an economical trip in minutes.
### Split Car allows you to request a car at the touch of a button and be picked up by a friendly driver who will take you to your destination immediately. Get trips from our partners instead of taking a taxi or waiting for the bus and enjoy a cozy, affordable and memorable trip today!

### Split Car app is free, cheaper than a taxi, faster than the bus and easy to use. Travel to any place you want to go without the need for car rental services or find out bus routes: we will take you directly to your destination.

### Shared ride made easy
• Use Split Car, register, then simply open the application and request a ride.
• Your Split Car driver will be at your available location at the agreed time, ready to take you to your destination.
### Affordable transit
• Is there no money for the taxi or the bus? With Split Car: it's easy, fast and safe!
### Transportation you can trust
• All Split Car drivers are partner and checks before they are approved for service.
• Drivers are qualified by passengers.
### DRIVER PROFILE
### Why split car?
Flexible
### Happy passengers
The Split Car community is full of friendly people, passengers and drivers alike from our partners.
Keywords: Split Car, save the environment, Less traffic
### General objective:
Implement an application in Android Studio with Kotlin to share the travel routes of our partners.
### Specific objectives:
###
§ Officialize the use of shared route with ours partners
###
§ Raise awareness ours partners regarding the environment, less cars, better quality of life.
###
§ Increase security in night travel to homes, less robberies and less robberies.
###
§ Expand the use of collective economy.
###
§ Reduce transportation costs for our partners.
###
### Impact achieved or expected with this theme in the target population
### Be friendly to the environment, less cars less pollution
### Travel and join the users who chose Split Car instead of a taxi to travel, go out at night and get home safely. When you need a trip, skip the bus, taxi or car service and choose Split Car. Forget about the peak and license plate
### The use of Split Car simplifies heavy city traffic and provides a shared economy: use it now and make your next trip the best trip you have ever had.
### Technique or methodology used
### Programming in Android Studio
### KOTLIN Programming Language
### Android architecture components

#
![Alt Text](https://github.globant.com/storage/user/2520/files/0e71cc80-060a-11ea-8765-a75ba2a7d1d9)
#
![Alt Text](https://github.globant.com/storage/user/2520/files/d3ae9680-07b9-11ea-9314-6873c68559a8)
#
![Alt Text](https://github.globant.com/storage/user/2520/files/e65ee080-09f0-11ea-839d-a4cc8ed4f9f6)
#
![Alt Text](https://github.globant.com/storage/user/2520/files/11abba80-07ba-11ea-9076-a97c7903bbc6)  


## Use cases   
#  Make a Route  
![Alt Text](https://github.globant.com/storage/user/2520/files/7ed21b00-0615-11ea-9bed-3704dea37b08)
#  
#  Routes List        
![Alt Text](https://github.globant.com/storage/user/2520/files/cfe20f00-0615-11ea-8d79-071b4db7c4da)
#  
#  Detail    
![Alt Text](https://github.globant.com/storage/user/2520/files/9dd0ad00-0615-11ea-8f49-8c1d0ee2d432)
#    
  
    
      
## Class Diagram   
#  
![Alt Text](https://github.globant.com/storage/user/2520/files/28fd7300-0615-11ea-894b-1fd89ed0b788)
#  

## User Stories  
 
#
#
1. **Ver el listado de las rutas disponibles.**
<br />:walking: Como usuario. <br /> **_Quiero_** visualizar un listado de turas con una paginacion de 10 elementos y cada elemento debe mostrar los
siguientes campos: `propietario`, `origen`, `destino`, `fecha`, `hora` y `cupos disponibles`. Para poder identificar la ruta en la que estoy
interesado
#  
2. **Realizar una búsqueda, de los sitios principales del campo `referencia destino` que han sido ingresados al crear una ruta.**
<br />:walking: Como usuario. <br /> **_Quiero_** tener una caja de texto de busqueda donde pueda digitar `sitios de referencia`.   
Para filtrar el listado de rutas, cada vez que digite una letra; si la caja de texto esta vacía mostrará las todas las rutas con una paginación de 10 elementos.
#  
3. **Crear una ruta.**
<br />:walking: Como usuario. <br /> **_Quiero_** crear una ruta en un formulario con los siguientes campos: `propietario`, `destino`, `hora`, `cupos disponibles`, `lugar de encuentro` y `referencia destino`. <br /> **_Quiero_** que en la vista de creación de ruta poder acceder a los viajes anteriores. <br /> **_Quiero_** tener un botón para confirmar todos los datos al crear una ruta. <br /> **_Quiero_** poder precargar fácilmente de mis viajes anteriores la información de la `hora`, `origen`, `destino`, `cupos`, `lugar de encuentro` y `referencia destino`. <br /> **_Quiero_** tener la posibilidad de seleccionar: `hora` y `cupos` sin necesidad de   digitarlos. <br /> **_Quiero_** tener la posibilidad de elegir los `lugares principales` por los que voy a pasar al realizar mi ruta.
Para crear una ruta con todos los datos que necesita un usuario con perfil pasajero.  

:no_entry: El usuario que cree una ruta, no puede suscribirse a ninguna ruta existente.  
:no_entry: Si un usuario se suscribe a una ruta, no puede crear rutas hasta que finalice dicha ruta.  
 

#  
4. **Eliminar una ruta creada.**
<br />:walking: Como usuario. <br /> **_Quiero_** tener la opción de eliminar una ruta. 
<br /> :email: Se enviará una notificación a cada usuario pasajero que se suscribio a la ruta eliminada.
#  
5. **Unirse a una ruta.**
<br />:walking: Como usuario. <br /> **_Quiero_** tener la opción de unirme a una ruta seleccionando una ruta disponible del listado en la vista principal. <br /> **_Quiero_** una vista en la que pueda visualizar una ruta seleccionada con los siguientes campos: `propietario`, `fecha`, `hora`, `origen`, `destino`, `cupos disponibles`, `lugar de encuentro`, `referencia destino`.
Para poder unirme a la ruta que creó un propietario.  
#  
6. **Alerta próxima a la hora de salida.**
<br />:walking: Como usuario. <br /> **_Quiero_** una notificación antes de 15 minutos de la hora de salida mediante un sonido y/o vibración  
Para llegar puntualmente al lugar de encuentro de salida