# VIN details API Getting Started
This project provides a RESTful API that when provided a VIN number will retrieve a vehicle's make, model and year.

The API leverages the NHTSA Vehicle API to obtain the vehicle details.

The application leverages caching to allow repeat requests to return previously cached responses without having to make repeat calls to the NHTSA API.

## Build and Run API
Clone the git repository or download as a zip and extract to a local directory i.e. ~/vinapi

Navigate to the project directory and run the following command to build and run the application

```console
./gradlew bootRun
```

This will build and run the application on the localhost at the default port of 8080.

## Testing the API
The application exposes a single endpoint domain/vinDetails/{vin} that accepts two operations.

### GET Vin Details
Performing a GET request on the endpoint and providing a valid VIN will return a JSON response object containing the make, model and year of the vehicle.

Example request: GET /vinDetails/5NPDH4AE7DH195017

Results:

HTTP Response 200 

```json
{
  "make": "HYUNDAI",
  "model": "Elantra",
  "year": "2013"
}
```

Providing an invalid VIN will return a NOT FOUND and null vehicle object.

GET /vinDetails/12345678901234567

HTTP Response 404

```json
{
  "make": null,
  "model": null,
  "year": null
}
```

### POST to add custom details
By performing a POST request on the endpoint and providing a JSON request object with make, model, year you can add a custom set of vehicle details that will be saved in the cache and can be retrieved on subsequent GET requests.

Example:

POST /vinDetails/ELONMUSK123456789
Request Body

```json
{
  "make": "BORING",
  "model": "Tunneler",
  "year": "2017"
}
```

HTTP Response 200

```json
{
  "make": "BORING",
  "model": "Tunneler",
  "year": "2017"
}
```

Once the custom details are added to the cache a GET request with the custom VIN will return the custom details.

GET /vinDetails/ELONMUSK123456789

HTTP Response 200

```json
{
  "make": "BORING",
  "model": "Tunneler",
  "year": "2017"
}
```
