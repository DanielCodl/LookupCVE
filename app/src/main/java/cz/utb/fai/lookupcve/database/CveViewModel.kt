package cz.utb.fai.lookupcve.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CveViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<CveDTO>>
    private val repository: CveRepository

    init {
        val cveDao = CveDatabase.getDatabase(application).CveDao()
        repository = CveRepository(cveDao)
        readAllData = repository.readAllData
    }

    fun addCve(cve: CveDTO){
        viewModelScope.launch {
            repository.addCve(cve)
        }
    }
    fun deleteAllData(){
        viewModelScope.launch {
            repository.deleteAllData()
        }
    }
}