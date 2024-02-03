package cz.utb.fai.lookupcve.model

data class CvssMetricV31(
    val cvssData: CvssDataX,
    val exploitabilityScore: Double,
    val impactScore: Double,
    val source: String,
    val type: String
)