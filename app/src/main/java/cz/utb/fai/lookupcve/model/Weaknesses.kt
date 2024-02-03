package cz.utb.fai.lookupcve.model

data class Weaknesses(
    val description: List<Description>,
    val source: String,
    val type: String
)