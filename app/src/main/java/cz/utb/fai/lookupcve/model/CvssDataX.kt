package cz.utb.fai.lookupcve.model

data class CvssDataX(
    val attackComplexity: String,
    val attackVector: String,
    val availabilityImpact: String,
    val baseScore: Double,
    val baseSeverity: String,
    val confidentialityImpact: String,
    val integrityImpact: String,
    val privilegesRequired: String,
    val scope: String,
    val userInteraction: String,
    val vectorString: String,
    val version: String
)