# Split Car
## A simple project for share your route on Kotlin MVVM + live data + room + firebase + clean architecture

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
![Alt Text](https://github.globant.com/storage/user/2520/files/6a729a80-ffe5-11e9-8fba-4e1c17b80845)
#
![Alt Text](https://github.globant.com/storage/user/2520/files/8b3af000-ffe5-11e9-8411-a986d572498a)
#
![Alt Text](https://github.globant.com/storage/user/2520/files/a148b080-ffe5-11e9-9597-6c877bc2d0a8)
#
![Alt Text](https://github.globant.com/storage/user/2520/files/cc330480-ffe5-11e9-9bba-8a70b0f7407d)


## User Historys

#
#
Historia ver el listado de las rutas disponibles  
Como usuario  
Quiero visualizar cada ruta con las siguientes propiedades: propietario de la ruta origen de la ruta destino de la ruta   fecha de la ruta hora de la ruta cupos disponibles de la ruta
Para identificar fácilmente la ruta en la que estoy interesado  
#  
Historia realizar una búsqueda de los sitios principales de referencia destino que han sido digitados al crear una ruta  
Como usuario  
Quiero filtrar los sitios principales de referencia destino que son de interés para que aparezcan en el listado sólo las   rutas disponibles por las que va a pasar la ruta
Para identificar fácilmente sólo las rutas de interés y poderlas seleccionar  
#  
Historia crear una ruta  
Como usuario  
Quiero adicionar una ruta por medio de un boton flotante y me lleve a otro fragmento con las siguientes propiedades:   propietario de la ruta, origen de la ruta, destino de la ruta, fecha de la ruta, hora de la ruta, cupos disponibles de la ruta
Para identificar fácilmente la ruta en la que estoy interesado  
#  
Historia fragmento de crear una ruta  
Como usuario  
Quiero una vista con un formulario editable en el que pueda crear una ruta con los siguientes propiedades: propietario   de la ruta, fecha de la ruta, hora de la ruta, origen de la ruta, destino de la ruta, cupos disponibles, lugar de encuentro para salida de la ruta, referencia de destino y un historial de los anteriores viajes 
Para crear una ruta con todos los datos que necesita un usuario con perfil pasajero y se pueda unir a mi ruta  
#  
Historia precargar datos de creación de ruta en la vista de creación de ruta  
Como usuario  
Quiero que en la vista de creación de ruta pueda acceder a los viajes anteriores  
Para poder precargar fácilmente de mis viajes anteriores la información de la hora, origen, destino, cupos, lugar de   encuentro y referencia de destino
#  
Historia confirmar los datos de creación de ruta fragmento de crear una ruta  
Como usuario  
Quiero tener un botón para confirmar todos los datos al crear una ruta  
Para confirmar todos los datos de creación de una ruta y que se guarden  
#  
Historia seleccionar la fecha, seleccionar la hora, seleccionar los cupos fragmento de crear una ruta  
Como usuario  
Quiero tener la posibilidad de seleccionar la fecha seleccionar la hora y seleccionar los cupos sin necesidad de   digitarlos
Para fácilmente crear una ruta seleccionando fecha hora y cupos  
#  
Historia digitar la referencia destino que son todos los lugares principales por los que voy a pasar al realizar mi ruta  
Como usuario  
Quiero tener la posibilidad de escribir los lugares principales por los que voy a pasar al realizar mi ruta  
Para que al usuario pasajero pueda realizar una búsqueda de interés por los sitios principales de la ruta que estoy   creando
#  
Historia modificar una ruta creada  
Como usuario  
Quiero tener la opción de modificar el nombre del propietario de una ruta, la fecha de partida de la ruta, la hora de   partida de la ruta, el lugar de origen de una ruta, el lugar de destino de la ruta, los cupos disponibles de una ruta, el lugar de encuentro de la ruta, la referencia destino de una ruta
Para tener la opción de corregir una ruta en caso de alguna equivocación  
#  
Historia eliminar una ruta creada  
Como usuario  
Quiero tener la opción de eliminar una ruta  
Para tener la opción de borrar una ruta en caso de alguna equivocación  
#  
Historia unirse a una ruta  
Como usuario  
Quiero tener la opción de unirme a una ruta seleccionando una ruta disponible del listado en la vista principal,   lanzando otro fragmento de unirse a la ruta
Para poder conectarme con el propietario que creó la ruta  
#  
Historia fragmento de unirse a una ruta  
Como usuario  
Quiero una vista con un formulario de información en el que pueda visualizar una ruta seleccionada con los siguientes   propiedades: propietario de la ruta, fecha de la ruta, hora de la ruta, origen de la ruta, destino de la ruta, cupos disponibles, lugar de encuentro para salida de la ruta, referencia de destino, cuenta regresiva
Para poder unirme a la ruta de interés  
#  
Historia alerta próxima a la hora de salida  
Como usuario  
Quiero faltando 15 minutos en la hora de salida de la ruta emitirá un sonido y/o vibración faltando 15 minutos de la   hora de partida de la ruta
Para llegar puntualmente al lugar de encuentro de salida de la ruta  