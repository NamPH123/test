package com.namseox.app_tcare.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.namseox.app_tcare.data.api.reponse.DataResponse
import com.namseox.app_tcare.data.api.reponse.LoadingStatus
import com.namseox.app_tcare.data.model.ChangePass
import com.namseox.app_tcare.data.model.RegisterResponse
import com.namseox.app_tcare.data.model.UserLoginModel
import com.namseox.app_tcare.data.model.UserLoginModelRe
import com.namseox.app_tcare.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(app : Application, var apiRepository: ApiRepository) : AndroidViewModel(app){
var data = MutableLiveData<DataResponse<Response<RegisterResponse>>>()

    fun register(user : UserLoginModelRe){
        data.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result = apiRepository.register(user)
            if(result==null){
                data.postValue(DataResponse.DataError())
            }else{
                data.postValue(DataResponse.DataSuccess(result))
            }
        }
    }
    fun login(user : UserLoginModel){
        data.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch {
            var result = apiRepository.login(user)
            if(result==null){
                data.postValue(DataResponse.DataError())
            }else{
                data.postValue(DataResponse.DataSuccess(result))
            }
        }
    }

    fun changePass(token : String,user: ChangePass){
        viewModelScope.launch {
            apiRepository.changePass(token,user)
        }
    }
}