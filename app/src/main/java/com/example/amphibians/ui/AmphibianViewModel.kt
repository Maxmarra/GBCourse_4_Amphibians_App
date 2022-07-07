package com.example.amphibians.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.amphibians.database.getDatabase
import com.example.amphibians.domain.AmphibianDomain
import com.example.amphibians.network.AmphibianApi
import com.example.amphibians.repository.AmphibianRepository
import kotlinx.coroutines.launch
import java.lang.Exception

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel(application: Application)
    : AndroidViewModel(application) {

    private val amphibianRepository = AmphibianRepository(getDatabase(application))
    val amphibians = amphibianRepository.amphibians


    private val _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus> = _status


//    private val _amphibians = MutableLiveData<List<AmphibianDomain>>()
//    val amphibians: LiveData<List<AmphibianDomain>> = _amphibians


    private val _amphibian = MutableLiveData<AmphibianDomain>()
    val amphibian: LiveData<AmphibianDomain> = _amphibian


    fun getAmphibiansList(){
        _status.value = AmphibianApiStatus.LOADING
        viewModelScope.launch {
            try {
                amphibianRepository.refreshAmphibians()
                _status.value = AmphibianApiStatus.DONE
            }catch (e:Exception){
                _status.value = AmphibianApiStatus.ERROR

            }
        }
    }

    fun onAmphibianClicked(amphibian: AmphibianDomain) {
        _amphibian.value = amphibian
    }
}
