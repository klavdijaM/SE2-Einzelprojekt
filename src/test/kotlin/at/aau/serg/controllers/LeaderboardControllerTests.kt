package at.aau.serg.controllers

import at.aau.serg.models.GameResult
import at.aau.serg.services.GameResultService
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import org.mockito.Mockito.`when` as whenever // when is a reserved keyword in Kotlin

class LeaderboardControllerTests {

    private lateinit var mockedService: GameResultService // mock of the service class
    private lateinit var controller: LeaderboardController // the class we are testing

    @BeforeEach
    fun setup() {
        mockedService = mock<GameResultService>()
        controller = LeaderboardController(mockedService)
    }

    @Test
    fun test_getLeaderboard_correctScoreSorting() {
        val first = GameResult(1, "first", 20, 20.0) // no new keyword needed, because data classes automatically generate constructors
        val second = GameResult(2, "second", 15, 10.0)
        val third = GameResult(3, "third", 10, 15.0)

        whenever(mockedService.getGameResults()).thenReturn(listOf(second, first, third))
        // whenever getGameResults is called, an immutable list of unordered elements will be returned

        val res: List<GameResult> = controller.getLeaderboard()
        // val - we can't reassign res to a new list
        // res is of type List
        // calls the getLeaderboard to sort the objects and return a sorted list

        verify(mockedService).getGameResults() // verifies that the method was called
        assertEquals(3, res.size) // the list should have 3 elements
        assertEquals(first, res[0]) // first el should be first (highest score)
        assertEquals(second, res[1])
        assertEquals(third, res[2])
    }

    @Test
    fun test_getLeaderboard_sameScore_CorrectTimeSorting() {
        val first = GameResult(1, "first", 20, 20.0)
        val second = GameResult(2, "second", 20, 10.0)
        val third = GameResult(3, "third", 20, 15.0)

        whenever(mockedService.getGameResults()).thenReturn(listOf(second, first, third))

        val res: List<GameResult> = controller.getLeaderboard()

        verify(mockedService).getGameResults()
        assertEquals(3, res.size)
        assertEquals(second, res[0]) // 10s is the fastest
        assertEquals(third, res[1])  // 15s
        assertEquals(first, res[2])  // 20s is the slowest
    }

}