package cz.utb.fai.lookupcve.model

data class Metrics(
    val cvssMetricV2: List<CvssMetricV2>,
    val cvssMetricV31: List<CvssMetricV31>
)