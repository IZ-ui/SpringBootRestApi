# SpringBootRestApi
Для запуска выполнить: mvn spring-boot:run
Проект будет доступен по url: http://localhost:8888

curl команды для тестирования:

### ORGANIZATION

##### list:

curl -X POST http://localhost:8888/api/organization/list -H 'content-type: application/json;charset=utf-8' -d '{"name":"Zed"}'

curl -X POST http://localhost:8888/api/organization/list -H 'content-type: application/json;charset=utf-8' -d '{"name":"Zed", "inn":9977004444}'

curl -X POST http://localhost:8888/api/organization/list -H 'content-type: application/json;charset=utf-8' -d '{"name":"Zed", "inn":9977004444, "isActive":true}'

##### error#

curl -X POST http://localhost:8888/api/organization/list -H 'content-type: application/json;charset=utf-8' -d '{"name":"Zed", "inn":9977004444, "isActive":false}

##### {id}:

curl http://localhost:8888/api/organization/1

##### error#

curl http://localhost:8888/api/organization/99

##### update:

curl -X POST http://localhost:8888/api/organization/update -H 'content-type: application/json;charset=utf-8' -d '{"id":3,"name":"Zedd","fullName":"LLC Zed","inn":"9977004444","kpp":"987651111","address":"Baker str.5"}'

curl -X POST http://localhost:8888/api/organization/update -H 'content-type: application/json;charset=utf-8' -d '{"id":3,"name":"Zedd","fullName":"LLC Zed","inn":"9977004444","kpp":"987651111","address":"Baker str.5","phone":"84952228888","isActive":true}'

##### error#

curl -X POST http://localhost:8888/api/organization/update -H 'content-type: application/json;charset=utf-8' -d '{"id":3,"name":"Zedd","fullName":"LLC Zed","inn":"9977004444","kpp":"987651111"}'

curl -X POST http://localhost:8888/api/organization/update -H 'content-type: application/json;charset=utf-8' -d '{"id":99,"name":"Zedd","fullName":"LLC Zed","inn":"9977004444","kpp":"987651111","address":"Baker str.5"}'

##### save:

curl -X POST http://localhost:8888/api/organization/save -H 'content-type: application/json;charset=utf-8' -d '{"name":"New","fullName":"LLC Zed","inn":"9977004444","kpp":"987651111","address":"Baker str.5"}'

curl -X POST http://localhost:8888/api/organization/save -H 'content-type: application/json;charset=utf-8' -d '{"name":"NewName","fullName":"LLC Zed","inn":"9977004444","kpp":"987651111","address":"Baker str.5","phone":"00011122233","isActive":true}'

##### error#

curl -X POST http://localhost:8888/api/organization/save -H 'content-type: application/json;charset=utf-8' -d '{"name":"New","fullName":"LLC Zed","inn":"9977004444","kpp":"987651111","address":"Baker str.5"}'

curl -X POST http://localhost:8888/api/organization/save -H 'content-type: application/json;charset=utf-8' -d '{"name":"NewNewName","fullName":"LLC Zed","inn":"9977004444","kpp":"987651111"}'

### OFFICE

##### list:

curl -X POST http://localhost:8888/api/office/list -H 'content-type: application/json;charset=utf-8' -d '{"orgId":"2"}'

curl -X POST http://localhost:8888/api/office/list -H 'content-type: application/json;charset=utf-8' -d '{"orgId":"2","isActive":true}'

curl -X POST http://localhost:8888/api/office/list -H 'content-type: application/json;charset=utf-8' -d '{"orgId":"2","phone":"84951112233","isActive":true}'

##### error#

curl -X POST http://localhost:8888/api/office/list -H 'content-type: application/json;charset=utf-8' -d '{"orgId":"99"}'

curl -X POST http://localhost:8888/api/office/list -H 'content-type: application/json;charset=utf-8' -d '{"orgId":"2","isActive":false}'

##### {id}:

curl http://localhost:8888/api/office/1

##### error#

curl http://localhost:8888/api/office/99

##### update:

curl -X POST http://localhost:8888/api/office/update -H 'content-type: application/json;charset=utf-8' -d '{"id":1,"name":"Updated Name","address":"Updated Address"}'

curl -X POST http://localhost:8888/api/office/update -H 'content-type: application/json;charset=utf-8' -d '{"id":1,"name":"Updated Name","address":"Updated Address","phone":"11122233344","isActive":false}'

##### error#

curl -X POST http://localhost:8888/api/office/update -H 'content-type: application/json;charset=utf-8' -d '{"id":1,"name":"Updated Name"}'

curl -X POST http://localhost:8888/api/office/update -H 'content-type: application/json;charset=utf-8' -d '{"id":99,"name":"Updated Name","address":"Updated Address"}'

##### save:

curl -X POST http://localhost:8888/api/office/save -H 'content-type: application/json;charset=utf-8' -d '{"orgId":"1"}'

curl -X POST http://localhost:8888/api/office/save -H 'content-type: application/json;charset=utf-8' -d '{"orgId":"2","name":"somename","address":"somestr", "phone":"00011122233","isActive":true}'

##### error#

curl -X POST http://localhost:8888/api/office/save -H 'content-type: application/json;charset=utf-8' -d '{"orgId":"99"}'


### USER

##### list:

curl -X POST http://localhost:8888/api/user/list -H 'content-type: application/json;charset=utf-8' -d '{"officeId":"2"}'

curl -X POST http://localhost:8888/api/user/list -H 'content-type: application/json;charset=utf-8' -d '{"officeId":"2","firstName":"John","secondName":"Doe","middleName":null,"position":"manager",“docCode”:12,“citizenshipCode”:643}'

##### error#

curl -X POST http://localhost:8888/api/user/list -H 'content-type: application/json;charset=utf-8' -d '{"officeId":"99"}'

curl -X POST http://localhost:8888/api/user/list -H 'content-type: application/json;charset=utf-8' -d '{"officeId":"2","firstName":"notFound"}'

##### {id}:

curl http://localhost:8888/api/user/1

##### error#

curl http://localhost:8888/api/user/99

##### update:

curl -X POST http://localhost:8888/api/user/update -H 'content-type: application/json;charset=utf-8' -d '{"id":2,"firstName":"XXX","position":"director"}'

curl -X POST http://localhost:8888/api/user/update -H 'content-type: application/json;charset=utf-8' -d '{"id":2,"firstName":"updatedName","secondName":"upd","middleName":"upd","position":"director","phone":"88880000","citizenshipCode":"643","isIdentified":true}'

##### error#

curl -X POST http://localhost:8888/api/user/update -H 'content-type: application/json;charset=utf-8' -d '{"id":99,"firstName":"XXX","position":"director"}'

curl -X POST http://localhost:8888/api/user/update -H 'content-type: application/json;charset=utf-8' -d '{"id":1,"firstName":"XXX"}'

##### save:

curl -X POST http://localhost:8888/api/user/save -H 'content-type: application/json;charset=utf-8' -d '{"officeId":"1","firstName":"newUserName","position":"newPosition"}'

curl -X POST http://localhost:8888/api/user/save -H 'content-type: application/json;charset=utf-8' -d '{"officeId":"1","firstName":"newUserName","position":"newPosition","docCode":"12","citizenshipCode":"643"}'

##### error#

curl -X POST http://localhost:8888/api/user/save -H 'content-type: application/json;charset=utf-8' -d '{"officeId":"1","firstName":"newUserName"}'

curl -X POST http://localhost:8888/api/user/save -H 'content-type: application/json;charset=utf-8' -d '{"officeId":"99","firstName":"newUserName","position":"newPosition"}'


### DIRECTORY

curl http://localhost:8888/api/countries

curl http://localhost:8888/api/docs

# Текст задания:

Все описанные возвращаемые типы данных находятся в параметре data. В случае ошибки возвращается параметр error.
Например, в случае, если запрос корректно отработает, бэк возвращает в body ответа:
{“data” : {//то, что в параметре out}}
А в случае ошибки возвращает 
{“error” : ”текст ошибки”}
Везде, где не написан метод, использовать POST

### 1. api/organization/list
In (фильтр):
{
  “name”:””, //обязательный параметр
  “inn”:””,
  “isActive”:””
}
Out:
[
  {
    “id”:””,
    “name”:””,
    “isActive”:”true”
  },
  ...
]

### 2. api/organization/{id}
method:GET
Out:
{
  “id”:””,
  “name”:””,
  “fullName”:””,
  “inn”:””,
  “kpp”:””,
  “address”:””,
  “phone”,””,
  “isActive”:”true”
}

### 3. api/organization/update
In:
{
  “id”:””, //обязательный параметр
  “name”:””, //обязательный параметр
  “fullName”:””, //обязательный параметр
  “inn”:””, //обязательный параметр
  “kpp”:””,  //обязательный параметр
  “address”:””, //обязательный параметр
  “phone”,””,
  “isActive”:”true”
}

Out:
{
    “result”:”success”
}

### 4. api/organization/save
In:
{
  “name”:””, //обязательный параметр
  “fullName”:””, //обязательный параметр
  “inn”:””, //обязательный параметр
  “kpp”:””, //обязательный параметр
  “address”:””, //обязательный параметр
  “phone”,””,
  “isActive”:”true”
}

Out:
{
    “result”:”success”
}

### 5. api/office/list
In (фильтр):
{
  “orgId”:””, //обязательный параметр
  “name”:””,
  “phone”:””,
  “isActive” 
}

Out:
[
  {
    “id”:””,
    “name”:””,
    “isActive”:”true”
  },
  ...
]

### ### 6. api/office/{id}
method:GET
Out:
{
  “id”:””,
  “name”:””,
  “address”:””,
  “phone”,””,
  “isActive”:”true”
}

### 7. api/office/update
In:
{
  “id”:””, //обязательный параметр
  “name”:””, //обязательный параметр
  “address”:””, //обязательный параметр
  “phone”,””,
  “isActive”:”true” //пример
}

Out:
{
    “result”:”success”
}

### 8. api/office/save
In:
{
  “orgId”:””, //обязательный параметр
  “name”:””,
  “address”:””,
  “phone”,””,
  “isActive”:”true”
}

Out:
{
    “result”:”success”
}

### 9. api/user/list
In (фильтр):
{
  “officeId”:””, //обязательный параметр
  “firstName”:””,
  “lastName”:””,
  “middleName”:””,
  “position”,””,
  “docCode”:””,
  “citizenshipCode”:””
}
Out:
{
  “id”:””,
  “firstName”:””,
  “secondName”:””,
  “middleName”:””,
  “position”:””
}

### 10. api/user/{id}
method:GET
Out:
{
  “id”:””,
  “firstName”:””,
  “secondName”:””,
  “middleName”:””,
  “position”:””
  “phone”,””,
  “docName”:””,
  “docNumber”:””,
  “docDate”:””,
  “citizenshipName”:””,
  “citizenshipCode”:””,
  “isIdentified”:”true”
}

### 11. api/user/update
In:
{
  “id”:””, //обязательный параметр
  “officeId”:””,
  “firstName”:””, //обязательный параметр
  “secondName”:””,
  “middleName”:””,
  “position”:”” //обязательный параметр
  “phone”,””,
  “docName”:””,
  “docNumber”:””,
  “docDate”:””,
  “citizenshipCode”:””,
  “isIdentified”:”true” //пример
}

Out:
{
    “result”:”success”
}

### 12. api/user/save
In:
{
  “officeId”:””, //обязательный параметр
  “firstName”:””, //обязательный параметр
  “secondName”:””,
  “middleName”:””,
  “position”:”” //обязательный параметр
  “phone”,””,
  “docCode”:””,
  “docName”:””,
  “docNumber”:””,
  “docDate”:””,
  “citizenshipCode”:””,
  “isIdentified”:”true” //пример
}

### Справочники:
api/docs
[
  {
    “name”:“Паспорт гражданина РФ”,
    “code”:”21”
  },
  ...
]

Виды документов, удостоверяющих личность физического лица
api/countries
[
  {
    “name”:“Российская Федерация”,
    “code”:”643”
  },
  ...
]
