package cz.utb.fai.lookupcve.model

data class CveJson(
    val format: String,
    val resultsPerPage: Int,
    val startIndex: Int,
    val timestamp: String,
    val totalResults: Int,
    val version: String,
    val vulnerabilities: List<Vulnerability>
)