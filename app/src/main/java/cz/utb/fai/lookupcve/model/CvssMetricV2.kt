package cz.utb.fai.lookupcve.model

data class CvssMetricV2(
    val acInsufInfo: Boolean,
    val baseSeverity: String,
    val cvssData: CvssData,
    val exploitabilityScore: Double,
    val impactScore: Double,
    val obtainAllPrivilege: Boolean,
    val obtainOtherPrivilege: Boolean,
    val obtainUserPrivilege: Boolean,
    val source: String,
    val type: String,
    val userInteractionRequired: Boolean
)