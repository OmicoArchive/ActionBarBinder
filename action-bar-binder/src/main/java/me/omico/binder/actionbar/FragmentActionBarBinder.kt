/*
 * Copyright 2021 Omico
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.omico.binder.actionbar

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class FragmentActionBarBinder(private val fragment: Fragment) : LifecycleObserver {

    private val host: AppCompatActivity by lazy(LazyThreadSafetyMode.PUBLICATION) {
        fragment.requireActivity() as AppCompatActivity
    }

    private val binder = SupportActionBarBinderImpl(host)

    val actionBar: ActionBar get() = binder.actionBar

    fun bindToolbar(toolbar: Toolbar) = binder.bind(toolbar)

    fun unbindToolbar() = binder.unbind()

    init {
        fragment.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        unbindToolbar()
        fragment.lifecycle.removeObserver(this)
    }

    companion object {

        @JvmStatic
        fun create(fragment: Fragment): FragmentActionBarBinder = FragmentActionBarBinder(fragment)

        @JvmStatic
        fun bind(fragment: Fragment, toolbar: Toolbar): FragmentActionBarBinder =
            create(fragment).also { it.bindToolbar(toolbar) }
    }
}
