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
package me.omico.buildSrc

object Versions {

    object Project {

        const val versionCode = 1
        const val versionName = "1.0.0"
    }

    object Androidx {

        object Appcompat {

            private const val groupName = "androidx.appcompat"
            private const val version = "1.3.0-beta01"

            const val appcompat = "$groupName:appcompat:$version"
        }

        object ConstraintLayout {

            private const val groupName = "androidx.constraintlayout"
            private const val version = "2.1.0-alpha2"

            const val constraintlayout = "$groupName:constraintlayout:$version"
        }

        object Core {

            private const val groupName = "androidx.core"
            private const val version = "1.5.0-beta01"

            const val coreKtx = "$groupName:core-ktx:$version"
        }

        object Fragment {

            private const val groupName = "androidx.fragment"
            private const val version = "1.3.0-rc02"

            const val fragmentKtx = "$groupName:fragment-ktx:$version"
        }

        object Navigation {

            private const val groupName = "androidx.navigation"
            private const val version = "2.3.3"

            const val navigationFragmentKtx = "$groupName:navigation-fragment-ktx:$version"
            const val navigationUiKtx = "$groupName:navigation-ui-ktx:$version"
        }
    }

    object Material {

        private const val groupName = "com.google.android.material"
        private const val version = "1.3.0-rc01"

        const val material = "$groupName:material:$version"
    }
}
