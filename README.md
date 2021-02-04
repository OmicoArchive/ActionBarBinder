# ActionBarBinder

An easy way to bind Toolbar into one activity with multiple fragments.

## Usage

[![Maven Central](https://shields.io/maven-central/v/me.omico.binder.actionbar/binder)](https://search.maven.org/search?q=g:me.omico.binder.actionbar)

### Add the ActionBarBinder dependency to your app

<details open>
<summary>Kotlin</summary>

```kotlin
dependencies {
    implementation("me.omico.binder.actionbar:binder:<version>")
}
```

</details>

<details>
<summary>Groovy</summary>

```gradle
dependencies {
    implementation 'me.omico.binder.actionbar:binder:<version>'
}
```

</details>

### Refer to the following code to bind your fragment's Toolbar

<details open>
<summary>Kotlin</summary>

You can follow the [FirstFragment.kt](sample\src\main\java\me\omico\binder\actionbar\sampleFirstFragment.kt) just simplify add `bindToolbar(Toolbar)` method, and then we are all done. It will automatically unbind our Toolbar when the fragment is destroyed.

</details>

<details open>
<summary>Java</summary>

It will be a little complicated in Java.

There are two ways to achieve this.

First, make your fragment extends `ActionBarFragment`. You can find this example in [SecondFragment.java](sample/src/main/java/me/omico/binder/actionbar/sample/SecondFragment.java).

Or, you can fowllow the `ActionBarFragment` to create an `FragmentActionBarBinder` instance in your own fragment.

```java
FragmentActionBarBinder binder = FragmentActionBarBinder.create(Fragment)
binder.bindToolbar(Toolbar)
```

</details>

For more information, please look at [FragmentActionBarBinder.kt](action-bar-binder/src/main/java/me/omico/binder/actionbar/FragmentActionBarBinder.kt).

## License

```txt
Copyright 2020 Omico

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
