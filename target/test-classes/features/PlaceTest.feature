Feature: Testing Places in the Google API

	@AddPlace
  Scenario Outline: Verify AddPlace Functionality
    Given Add Place Payload with "<name>", "<language>" and "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status"  in response is "OK"
    And "scope"  in response is "APP"
    And Verify place_id created maps to "<name>" using "getPlaceAPI"
    
 Examples:
   | name            | language  | address                   |
   | Frontline house | French-IN | 29, side layout, cohen 09 |
#   | Dundder Mufflin | English   | 29, side layout, Scranton |

	@GetPlace
  Scenario: Verify GetPlace functionality
  	Given GetPlace payload
  	When user calls "getPlaceAPI" with "Get" http request
  	Then the API call got success with status code 200
  	And "name"  in response is "Frontline house"

  @UpdatePlace
  Scenario Outline: Verify Update Place Functionality
  	Given Update Place Payload with "<address>"
  	When user calls "updatePlaceAPI" with "Put" http request
  	Then the API call got success with status code 200
  	And "msg"  in response is "Address successfully updated"
  
  Examples:
  |address             | 	
  |70 Summer walk, USA |
  
	@DeletePlace
	Scenario: Verify Delete Place functionality
		Given DeletePlace payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call got success with status code 200		
    And "status"  in response is "OK"
    
 
