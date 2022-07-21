package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import com.example.amphibians.network.AmphibianApiService
import kotlinx.coroutines.launch
import java.lang.Exception

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel : ViewModel() {


    private var _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus> = _status


    private var _amphibians = MutableLiveData<List<Amphibian>>()
    val amphibians: LiveData<List<Amphibian>> = _amphibians

    private var _amphibian = MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian> = _amphibian

    fun getAmphibiansList(){
        _status.value = AmphibianApiStatus.LOADING
        try {
            viewModelScope.launch {
                _amphibians.value = AmphibianApi.retrofitService.getAmphibians()
                _status.value = AmphibianApiStatus.DONE
            }
        }catch (e:Exception){
            _status.value = AmphibianApiStatus.ERROR
            _amphibians.value = listOf()
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        _amphibian.value = amphibian
    }
}
