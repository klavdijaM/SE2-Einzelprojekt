package at.aau.serg.models

data class GameResult(var id: Long, var playerName: String, var score: Int, var timeInSeconds: Double)

// data class is a special class in kotlin, which automatically generates a constructor, toString(), equals() and hasCode() methods (similar to records in java)
// each GameResult object has an id, name, score and a time