package com.hrs.mutiplemoduleapp.viewmodel

import com.hrs.common.ui.BaseViewModel
import com.hrs.domain.login.LoginUseCase
import com.hrs.extentions.encodeBase64
import com.hrs.network.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    fun login(onLoading : () -> Unit,onSuccess:() ->Unit, onError:() ->Unit) {
        var pass = "Pp654123"
        pass = pass.encodeBase64()
        launch {
            loginUseCase.execute(
                LoginUseCase.Params(
                    id = "sale@paypensevietnam.commmm",
                    password = pass
                )
            ).collect{
                when(it){
                    is NetworkResource.Loading ->{
                         onLoading.invoke()
                    }
                    is NetworkResource.Success ->{
                        onSuccess.invoke()
                    }
                    is NetworkResource.Error ->{
                        onError.invoke()
                    }
                }
            }

        }
    }
}