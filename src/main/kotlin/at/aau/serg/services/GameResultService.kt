package at.aau.serg.services

import at.aau.serg.models.GameResult
import org.springframework.stereotype.Service // marks the class as a service layer
import java.util.concurrent.atomic.AtomicLong // used for generating unique ids

@Service // manages the logic of the program
// middleman between the controller and the data storage
class GameResultService {
    // service layer automatically creates a GameResultService obj, we don't need to create new obj in other classes when we want to use them

    private val gameResults = mutableListOf<GameResult>() // stores game results in memory (temporary database)
    private var nextId = AtomicLong(1) // starts at one and increments for each id

    fun addGameResult(gameResult: GameResult) {
        gameResult.id = nextId.getAndIncrement() // reads the current id value and increases it by 1 for the next entry
        gameResults.add(gameResult)
    }

    fun getGameResult(id: Long): GameResult? = gameResults.find { it.id == id } // ? allows null
    // single expression function - directly returns the result of the expression after =
    // find.() searches a list and returns the first matching el or null if no el found
    // it.id represents the current element inside a loop or operation (

    fun getGameResults(): List<GameResult> = gameResults.toList() // returns immutable list copy
    // toList ensures the list can't be modified outside the service

    /**
     * Kotlin-idiomatic for:
     * fun deleteGameResult(gameResultId: Long) {
     *     gameResults.removeIf({ gameResult -> gameResult.id == gameResultId })
     * }
     */
    fun deleteGameResult(id: Long) = gameResults.removeIf { it.id == id }

}