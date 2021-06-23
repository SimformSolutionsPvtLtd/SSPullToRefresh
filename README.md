# SSPullToRefresh
## Pull to Refresh with custom animations

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)][git-repo-url] [![](https://jitpack.io/v/SimformSolutionsPvtLtd/SSPullToRefresh.svg)](https://jitpack.io/#SimformSolutionsPvtLtd/SSPullToRefresh) [![Kotlin Version](https://img.shields.io/badge/Kotlin-v1.5.0-blue.svg)](https://kotlinlang.org)  [![Platform](https://img.shields.io/badge/Platform-Android-green.svg?style=flat)](https://www.android.com/) [![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

SSPullToRefresh uses lottie animations to render high quality animations on pull refresh.

## Features

- Simple and easy to use ( no complex animations to deal with )
- Customize the animation view by providing you own ( need to subclass SSAnimationView )
- Import lottie jason in assets folder and apply animation ( as simple as that )
- Customize repeateMode, repeateCount and Interpolators on different points of animations

# 🎬 Preview

| Default refreshView | Custom animation 1 | Custom animation 2 |
|--|--| --|
| ![](default_anim.gif) | ![](custom_anim.gif) | ![](custom_anim2.gif) |

# How it works:

1. Gradle Dependency

- Add the JitPack repository to your project's build.gradle file

```groovy
    allprojects {
        repositories {
            ...
    	    maven { url 'https://jitpack.io' }
        }
    }
```
- Add the dependency in your app's build.gradle file

```groovy
    dependencies {
        implementation 'com.github.SimformSolutionsPvtLtd:SSPullToRefresh:1.1'
    }
```
2. Wrap your refreshing view ( RecyclerView, listView etc..) with SSPullToRefreshLayout
```xml
    <com.simform.refresh.SSPullToRefreshLayout
        android:id="@+id/ssPullRefresh"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rv"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </com.simform.refresh.SSPullToRefreshLayout>
```
3. Set OnRefreshListener on SSPullToRefreshLayout and you are good to go 👍
```kotlin
    ssPullRefresh.setOnRefreshListener(object : SSPullToRefreshLayout.OnRefreshListener {
        override fun onRefresh() {
            // This is demo code to perform
            GlobalScope.launch {
                delay(3000)
                ssPullRefresh.setRefreshing(false) // This line stops layout refreshing
                MainScope().launch {
                    Toast.makeText(this@MainActivity,"Refresh Complete",Toast.LENGTH_SHORT).show()
                }
            }
        }
    })
```

# To customize SSPullToRefreshLayout:

* To customize SSPullToRefreshLayout, you can set a different lottie animation of your choice
* you need to have .json file of you lottie animations in assets forlder of you app module

![](asset_folder.png)

```kotlin
    ssPullRefresh.setLottieAnimation("lottie_isometric-plane.json")
```
* To customize repeadtMode and repeatCount of animation.
```kotlin
    ssPullRefresh.setRepeatMode(SSPullToRefreshLayout.RepeatMode.REPEAT)
    ssPullRefresh.setRepeatCount(SSPullToRefreshLayout.RepeatCount.INFINITE)
```
* To change refresh style.
```kotlin
    ssPullRefresh.setRefreshStyle(SSPullToRefreshLayout.RefreshStyle.NORMAL)
```
* To customize the whole refresh view you need to inherit SSAnimationView for your custom class and override the methods needed
```kotlin
    class MytAnimationView(context: Context): SSAnimationView(context) {

        override fun reset() {
            cancelAnimation()
            playAnimation()
        }

        override fun refreshing() {
        }

        override fun refreshComplete() {
            cancelAnimation()
        }

        override fun pullToRefresh() {
            playAnimation()
        }

        override fun releaseToRefresh() {
        }

        override fun pullProgress(pullDistance: Float, pullProgress: Float) {
        }
}
```
* Provide you CustomView by setRefreshView() method
```kotlin
        ssPullRefresh.setRefreshView(
                MytAnimationView(this),
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300)
        )
```
* You can aslo provide only view without parameters, in that case refreshView will take MATCH_PARENT,70 width,hight respectively
```kotlin
        ssPullRefresh.setRefreshView(MytAnimationView(this))
```

# Other Library used:
* [Lottie][lottie-repo-url]

### Credits:
- This library was inspired by __[RecyclerRefreshLayout]__

## Find this library useful? ❤️
Support it by joining __[stargazers]__ for this repository.⭐

## 🤝 How to Contribute

Whether you're helping us fix bugs, improve the docs, or a feature request, we'd love to have you! 💪

Check out our __[Contributing Guide]__ for ideas on contributing.

## Bugs and Feedback

For bugs, feature requests, and discussion please use __[GitHub Issues]__.

## License

```
Copyright 2021 Simform Solutions

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and limitations under the License.
```

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [git-repo-url]: <https://github.com/SimformSolutionsPvtLtd/SSPullToRefresh.git>
   [lottie-repo-url]: <https://github.com/airbnb/lottie-android.git>
   [stargazers]: <https://github.com/SimformSolutionsPvtLtd/SSPullToRefresh/stargazers>
   [Contributing Guide]: <https://github.com/SimformSolutionsPvtLtd/SSPullToRefresh/blob/main/CONTRIBUTING.md>
   [GitHub Issues]: <https://github.com/SimformSolutionsPvtLtd/SSPullToRefresh/issues>
   [RecyclerRefreshLayout]: <https://github.com/dinuscxj/RecyclerRefreshLayout?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=3383>
