Feature: Testing Places in the Google API

	@AddPlace
  Scenario Outline: Testing Add Place API
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

	@DeletePlace
	Scenario: Verify if Delete Place functionality
		Given DeletePlace payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call got success with status code 200		
    And "status"  in response is "OK"
