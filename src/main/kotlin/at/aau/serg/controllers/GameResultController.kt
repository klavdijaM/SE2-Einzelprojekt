package at.aau.serg.controllers

import at.aau.serg.models.GameResult
import at.aau.serg.services.GameResultService
import org.springframework.web.bind.annotation.* // imports Spring Web annotations (@RestController, @GetMapping)

@RestController // marks the class as a REST API controller
// the controller layer is responsible for handling requests
@RequestMapping("/game-results") // Defines the base URL for all API calls inside this controller.
// every function (API method) inside the controller will start with this base URL
class GameResultController(
    private val gameResultService: GameResultService
    // dependency injection - springboot automatically provides a GameResultService instance, unlike java, where we would have to create a new obj manually inside a constructor
) {

    @GetMapping("/{gameResultId}") // maps any GET request that follows this pattern and captures and uses the value of gameResultId in the function
    // /{gameResultId} URI template variable - means that part of the URL path will be a variable, and it will be captured and passed to the method as a parameter.
    // example: GET /game-results/1 - id is 1 (is passed as parameter to the method)
    fun getGameResult(@PathVariable gameResultId: Long): GameResult? {
        // PathVariable - Spring Web annotation which binds method parameter to the URI template variable in the URL
        // when the request GET /game-results/1 is made, Spring will automatically map 1 from the URL into the gameResultId parameter
        // GameResult? - return type of the function - ? indicates that the method can return a valid obj or null
        return gameResultService.getGameResult(gameResultId);
    }

    @GetMapping
    fun getAllGameResults(): List<GameResult> { // returns a list of all game results
        return gameResultService.getGameResults();
    }

    @PostMapping // handles POST requests (POST /game-results)
    // no specified path - means this method will handle POST requests to the base URL
    // A POST request made to /game-results will trigger this method
    fun addGameResult(@RequestBody gameResult: GameResult) {
        // requestBody - tells Spring to get the data from the body of an HTTP request (usually JSON) and automatically convert it into an object (like GameResult)
        // example: a client sends a POST request with JSON ("score":100,..) ans spring sonverts it into GameResult obj with a field score = 100
        gameResultService.addGameResult(gameResult)
    }

    @DeleteMapping("/{gameResultId}")
    fun deleteGameResult(@PathVariable gameResultId: Long) {
        gameResultService.deleteGameResult(gameResultId)
    }
    
}