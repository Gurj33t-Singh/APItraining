Feature: Validating Add Place APIs

@AddPlace
Scenario Outline: Verify if place is added using AddPlaceAPI
	Given Add place payload "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" using "POST" http request
	Then The API responds with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify place_id maps to "<name>" using "GetPlaceAPI"
	
Examples: 
	|name			|language		|address			|
	|Emaar Hills	|English		|World Cross Center	|
#	|Wave Estate	|French			|Set Cross Street	|


@DeletePlace
Scenario: Verify if delete place functionality is working
	Given Delete place payload
	When User calls "DeletePlaceAPI" using "POST" http request
	Then The API responds with status code 200
	And "status" in response body is "OK"