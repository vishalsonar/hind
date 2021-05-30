# Hind

#### TravisCI Build Status
[![Build Status](https://travis-ci.com/vishalsonar/hind.svg?branch=main)](https://travis-ci.com/vishalsonar/hind) 
---

#### Sonar Cloud Status
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=bugs)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=code_smells)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=coverage)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=ncloc)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=alert_status)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=security_rating)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=sqale_index)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=vishalsonar_hind)
---

Hind is a Java based application which expose api to perform CRUD operation in MongoDB. Hind provide flexibility to switch between Database and Collection just by changing host path given Host path format: `<URL>/database/collection`. Validation of URL i.e. Database and Collection is to be configured in hind.json. Hind make use of Json Web Token to query collection instead of normal MongoDB query string. Currently hind is develop and tested with Mongodb Atlas Database with all servlet request map to `/hind/*`.

#### HTTP Method mapping to CRUD Action

HTTP Method | CRUD Action 
------------|------------ 
GET         | SELECT
PUT         | INSERT
POST        | UPDATE
DELETE      | DELETE

#### Configuration

Hind require two mandatory configuration to execute. First configuration is hind.json file which hold list of all whitelisted Database and collection. Second configuration is related to JAVA_OPTION which provide basic application context as follows:
* -DconfigPath=_PATH to hind.json file e.g. `C:\..\..\hind.json`_
* -Durl=_MongoDB Connection URL e.g. `mongodb+srv://<username>:<password>@cluster0.tarir.mongodb.net/?retryWrites=true`_
* -Dsecret=_Secret token for HmacSHA512 algorithm used to validate Json Web Token e.g. `token`_

#### Hind.json Format

 ```json
 {
    "config": [
        {
            "database": "sample_weatherdata",
            "collections": ["data", "test_data"]
        }
    ]
}
```

#### API

##### PUT (_is to insert_)
To insert data into database, Set HTTP request method to `PUT` in header with request body containing JSON data which needs to be inserted. JSON data must have unique identifier holded by key `_id`. example:
```json
{
    "_id": "12345",
    "key": "data",
    "key1": "data1",
    "key2": "some object"
}
```
##### POST (_is to update_)
To update data in database, Set HTTP request method to `POST` in header with request body containing JSON data which needs to be updated. JSON data must have unique identifier `_id` for which data needs to be updated. example:
```json
{
    "_id": "12345",
    "key": "updated_data",
    "key1": "updated_data1",
    "key2": "some object"
}
```
##### DELETE (_is to delete_)
To delete data from database, Set HTTP request method to `DELETE` in header with request body containing JSON data with only unique identifier. example:
```json
{
    "_id": "12345"
}
```
##### GET (_is to select_)
To select data from database, Set HTTP request method to `GET` in header with query parameter as `query` containing JWT string of mongodb query object. Mongodb query object is same as mongodb JSON query object which is used to fetch data. example:
```
https://someurl/databasename/collectionname?query=jwtString_version_of_mongodb_query_object
```

#### JWT Get Query Generation



#### License
Distributed under the MIT License. See `LICENSE` for more information.
