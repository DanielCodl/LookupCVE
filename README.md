Závěrečný projekt pro předmět "Mobilní technologie" (ak. rok 2023/24, ZS, UTB, Informační Technologie, Kyber. bezpečnost).

Jedná se o jednoduchou aplikaci pro Android napsanou v Kotlinu, která vrací detaily zadaného CVE (Common Vulnerabilities and Exposures) z databáze nvd.nist.gov pomocí volání REST API/Retrofit.

Popis příslušného API viz: https://nvd.nist.gov/developers/vulnerabilities

Aplikace podporuje jak novější formát metrik cvssMetricV31, tak i starší cvssMetricV2, a zobrazuje/ukládá tyto položky: id, published, description (en), vectorString, baseScore, baseSeverity a cvssMetric.

Pomoci RecycleView je následně v samostatném fragmentu zobrazena historie výsledků (setříděna od nejnovějšího) uložena v databázi Room.

