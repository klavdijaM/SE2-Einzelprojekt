package at.aau.serg.controllers

import at.aau.serg.models.GameResult
import at.aau.serg.services.GameResultService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/leaderboard")
class LeaderboardController(
    private val gameResultService: GameResultService
) {

    @GetMapping
    fun getLeaderboard(): List<GameResult> = // returns a list of game obj sorted by score
        //gameResultService.getGameResults().sortedWith(compareBy({ -it.score }, { it.id }))
    // sortedWith: a Kotlin function that sorts a collection based on a given comparator. it converts the list into an array and uses pairwise comparison to determine the order
    // compareBy: defines criteria for sorting - by default sorts in ascending order
    // -it.score: first criterion - we're sorting by the score in reverse order (higher score comes first)
    // it.id: second criterion - if two GameResult objects have the same score, we sort it by their id from lowest to highest
        gameResultService.getGameResults().sortedWith(compareBy({-it.score}, {it.timeInSeconds}))

}