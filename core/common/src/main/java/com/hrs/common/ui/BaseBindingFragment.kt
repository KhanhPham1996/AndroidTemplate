package com.hrs.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.hrs.common.helper.InflateAlias

abstract class BaseBindingFragment<VB : ViewBinding> : BaseFragment() {

    final override fun layoutId(): Int = View.NO_ID

    abstract val bindingInflater: InflateAlias<VB>

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    val binding: VB
        get() = _binding as VB

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = bindingInflater.invoke(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    final override fun setupView(view: View) = setupView()

    final override  fun bindViewEvents(view: View) = bindViewEvents()

    open fun setupView() {}

    open fun bindViewEvents() {}
}