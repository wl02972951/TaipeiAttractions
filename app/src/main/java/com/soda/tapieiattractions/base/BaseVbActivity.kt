package com.soda.tapieiattractions.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * 用於封裝ViewBinding
 * 行為的的BaseActivity
 *
 */
abstract class BaseVbActivity <B: ViewBinding>(private val bindingFactory: (LayoutInflater) -> B) : AppCompatActivity() {

    protected val binding: B by lazy { bindingFactory(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}