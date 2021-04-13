# Hind

#### TravisCI Build Status
[![Build Status](https://travis-ci.com/vishalsonar/hind.svg?branch=main)](https://travis-ci.com/vishalsonar/hind) 
---

#### Sonar Cloud Status
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=bugs)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=code_smells)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=coverage)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=ncloc)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=alert_status)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=security_rating)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=sqale_index)](https://sonarcloud.io/dashboard?id=vishalsonar_hind) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=vishalsonar_hind&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=vishalsonar_hind)
---

Hind is a Java application which expose api to perform CRUD operation in MongoDB. Hind provide flexibility to switch between Database and Collection just by changing host path given Host path format: `<URL>/database/collection`. Validation of URL i.e. Database and Collection can be configured in hind.json. Hind make use of Json Web Token instead of normal string query to query selection of document. Currently hind is develop and tested with Mongodb Atlas Database with all servlet request map to `/hind/*`.

## HTTP Method mapping to CRUD Action

HTTP Method | CRUD Action 
------------|------------ 
GET         | SELECT
PUT         | INSERT
POST        | UPDATE
DELETE      | DELETE

## Configuration

Hind require two mandatory configuration to execute. First configuration is hind.json file which hold list of all whitelisted Database and collection. Second configuration is realted to JAVA_OPTION which provide basic context as follows:
* -DconfigPath=_PATH to hind.json file e.g. `C:\..\..\hind.json`_
* -Durl=_MongoDB Connection URL e.g. `mongodb+srv://<username>:<password>@cluster0.tarir.mongodb.net/?retryWrites=true`_
* -Dsecret=_Secret token for HmacSHA512 algorithm used to validate Json Web Token e.g. `token`_

## Hind.json Format

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

## API

## JWT Get Query Generation

## License
Distributed under the MIT License. See `LICENSE` for more information.
