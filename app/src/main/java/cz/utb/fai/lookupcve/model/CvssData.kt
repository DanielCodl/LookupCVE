package cz.utb.fai.lookupcve.model

data class CvssData(
    val accessComplexity: String,
    val accessVector: String,
    val authentication: String,
    val availabilityImpact: String,
    val baseScore: Double,
    val confidentialityImpact: String,
    val integrityImpact: String,
    val vectorString: String,
    val version: String
)