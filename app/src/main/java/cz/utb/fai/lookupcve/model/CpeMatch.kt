package cz.utb.fai.lookupcve.model

data class CpeMatch(
    val criteria: String,
    val matchCriteriaId: String,
    val versionEndIncluding: String,
    val vulnerable: Boolean
)