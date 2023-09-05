
# Air Pollution Data Collector Project - Spring Boot - Java
This project involves the development of a Spring Boot-based RESTful application that retrieves historical air pollution data based on city name and date range from the OpenWeatherMap API. The retrieved data is then categorized and stored in a database. 

## Requirements

`Data Sources:`

OpenWeatherMap API: The project utilizes the Historical Air Pollution and Geocoding endpoints to obtain air pollution data and city coordinates.
Functionality:

1. The application takes as input the city name and an optional date range to query historical air pollution data.
2. The Geocoding API is used to obtain the coordinates of the specified city.
3. The queried air pollution data includes Carbon Monoxide (CO), Ozone (O3), and Sulfur Dioxide (SO2) concentrations.
4. Air quality is classified into categories such as "Good," "Satisfactory," "Moderate," "Poor," "Severe," and "Hazardous" based on "AQI Category" table on https://en.wikipedia.org/wiki/Air_quality_index#CAQI 

5. The retrieved data is sorted by date and returned in JSON format.

`Database Operations:`

Before querying the OpenWeatherMap API, the application checks the database for existing records.
Only date ranges that are not present in the database are queried from the API, and the results are stored in the database.
The application logs whether data was fetched from the database or the API for each query.

`Supported Cities:` Queries are supported for the following cities: London, Barcelona, Ankara, Tokyo, and Mumbai.
Date Range Limitation:

Historical data retrieval is limited to dates from 27th November 2020 to the present. Queries outside this range will result in an error message regarding the date range.



## Prerequisites
- Java JDK 17
- Spring Boot 2.5.4
- PostgreSQL


## Installation

1. Clone the repository: `https://github.com/sudeakarcay/Air-Pollution-Data-Collector.git`

2. Open the project in your IDE.

3. Build Gradle project

4. Create a database and configure the application-common-dev.properties file with the database credentials.

5. Run both WebAppConfig.java and WorkerAppConfig.java

## Queue

1. Go to RabbitMQ (which should be already installed and running on http://localhost:15672/) and create a virtual host named "guest".
2. Create queues whose names are located in application.properties files of both web and worker modules under resources folder.
3. Bind queues to exchanges whose names are located in application.properties files of both modules.

## Endpoints

1. GET http://localhost:8080/geocoding/coordinates/{cityName} first run this endpoint to get coordinates of the city.
2. GET http://localhost:8080/historical/data?city={cityName}&startDate={startDate}&endDate={endDate} run this endpoint to get historical pollution data information of the city.

## API Key

1. Go to https://openweathermap.org/api and create an account.
2. Get your API key.


## Example Request

GET http://localhost:8080/historical/data?city=Mumbai&startDate=01-02-2023&endDate=02-02-2023

## Example Response 

``` json
{
    "City": "Mumbai",
    "Results": [
        {
            "Date": "01-02-2023",
            "Categories": {
                "SO2": "Satisfactory",
                "CO": "Hazardous",
                "O3": "Satisfactory"
            }
        },
        {
            "Date": "02-02-2023",
            "Categories": {
                "SO2": "Satisfactory",
                "CO": "Hazardous",
                "O3": "Moderate"
            }
        }
    ]
}
