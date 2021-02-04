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
package me.omico.fragment

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import me.omico.binder.actionbar.FragmentActionBarBinder
import me.omico.binder.actionbar.createActionBarBinder

@Suppress("unused")
open class ActionBarFragment : Fragment {

    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    private lateinit var binder: FragmentActionBarBinder

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binder = createActionBarBinder()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binder.onDestroy()
    }

    protected val actionBar get() = binder.actionBar

    protected fun bindToolbar(toolbar: Toolbar) = binder.bindToolbar(toolbar)

    protected fun unbindToolbar() = binder.unbindToolbar()
}
