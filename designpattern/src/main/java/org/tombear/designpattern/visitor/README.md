### Description

![system overview](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.github.com/anoff/plantbuddy/master/assets/overview.iuml)
![uncached image](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.github.com/plantuml/plantuml-server/master/src/main/webapp/resource/test2diagrams.txt)


```plantuml
@startuml component
actor client
node app
database db

db -> app
app -> client
@enduml
```