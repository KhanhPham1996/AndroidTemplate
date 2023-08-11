package com.hrs.mutiplemoduleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.hrs.common.extentions.safeClick
import com.hrs.common.ui.BaseActivity
import com.hrs.common.ui.BaseBindingActivity
import com.hrs.mutiplemoduleapp.databinding.ActivityMainBinding
import com.hrs.mutiplemoduleapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    private val viewModel: MainViewModel by viewModels()
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnLogin.safeClick {
            viewModel.login(
                onLoading = {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                },
                onSuccess = {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                },
                onError = {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}