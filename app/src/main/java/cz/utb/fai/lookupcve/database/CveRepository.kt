package cz.utb.fai.lookupcve.database

import androidx.lifecycle.LiveData

class CveRepository(private val cveDao: CveDao) {

    val readAllData: LiveData<List<CveDTO>> = cveDao.readAllData()

    suspend fun addCve(cve: CveDTO){
        cveDao.addCve(cve)
    }
    suspend fun deleteAllData(){
        cveDao.deleteAllData()
    }
}