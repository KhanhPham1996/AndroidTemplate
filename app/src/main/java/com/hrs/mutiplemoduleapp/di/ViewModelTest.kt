package com.hrs.mutiplemoduleapp.di

import androidx.lifecycle.ViewModel
import com.hrs.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelTest @Inject constructor (loginUseCase: LoginUseCase) : ViewModel() {
}