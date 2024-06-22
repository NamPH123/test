package com.namseox.app_tcare.ui.home

import android.app.Application
import android.icu.text.IDNA.Info
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.namseox.app_tcare.data.api.reponse.DataResponse
import com.namseox.app_tcare.data.api.reponse.LoadingStatus
import com.namseox.app_tcare.data.model.AddBooking
import com.namseox.app_tcare.data.model.BookingModel
import com.namseox.app_tcare.data.model.ItemNotifi
import com.namseox.app_tcare.data.model.RegisterResponse
import com.namseox.app_tcare.data.model.TimeModel
import com.namseox.app_tcare.data.repository.ApiRepository
import com.namseox.app_tcare.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(app : Application, var apiRepository: ApiRepository,var roomRepository: RoomRepository) : AndroidViewModel(app){
    var data = MutableLiveData<DataResponse<Response<BookingModel>>>()
    var dataInfo = MutableLiveData<List<ItemNotifi>>()

    fun booking(token : String, booking: AddBooking){
        data.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch ( Dispatchers.IO ){
            var result = apiRepository.booking(token,booking)
            if(result==null){
                data.postValue(DataResponse.DataError())
            }else{
                data.postValue(DataResponse.DataSuccess(result))
            }
        }
    }
    fun updateBooking(id : Int,d : TimeModel){
        data.value = DataResponse.DataLoading(LoadingStatus.Loading)
        viewModelScope.launch ( Dispatchers.IO ){
            var result = apiRepository.updateBooking(id,d)
            if(result==null){
                data.postValue(DataResponse.DataError())
            }else{
                data.postValue(DataResponse.DataSuccess(result))
            }
        }
    }
     fun addNotifi(item : ItemNotifi){
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.setItemInfo(item)
        }
    }
    fun getAllItem(){
        viewModelScope.launch ( Dispatchers.IO ){
            dataInfo.postValue(roomRepository.getAllItemInfo())
        }
    }
    fun updateItem(id:Int, chuoi: String){
        viewModelScope.launch (Dispatchers.IO){
            roomRepository.updateItemInfo(id, chuoi)
        }
    }

}