# Mixture

[ ![Download](https://api.bintray.com/packages/vershininds/maven/Mixture/images/download.svg) ](https://bintray.com/vershininds/maven/Mixture/_latestVersion)

Base classes for create VIPER architecture in android project

![diagram](https://raw.githubusercontent.com/vershininds/mixture/master/img/viper_diagram.jpg)

- *View* - responsible for displaying data on the screen and notifies the *Presenter* of the user's actions. But *View* never asks for data. It just gets them from the ViewModel(in clean VIPER from presenter).

- *Presenter* - receives information about the user's actions from View and transforms it into requests to *Interactor*, *Router*, *ViewModel*. Receives data from Interactor, prepares them and set in ViewModel(in clean VIPER data sends in View). Then ViewModel post data in view to display.

- *ViewModel* - intermediary between the *Presenter* and *View*. Simplifies the work of updating data on the view and saves the state when view was destroyed

- *Interactor(other name UseCase)* - working with business logic. All business logic calls are made through Services. You can use *Repository* pattern in this class for load data from different sources.

- *Service* - data provider, receives data from the specified source.

- *Router* - responsible for the navigation between the modules(in simple terms navigation between screens).


## Setup
```groovy
// root build.gradle file

allprojects {
    repositories {
        jcenter()
    }
}
```
core 
```groovy
implementation 'com.github.vershininds:mixture:x.y.z'
```

Interactor on Rx [ ![Download](https://api.bintray.com/packages/vershininds/maven/RxInteractor/images/download.svg) ](https://bintray.com/vershininds/maven/RxInteractor/_latestVersion)
```groovy
implementation 'com.github.vershininds:mixture-rxinteractor:x.y.z'
```

Interactor on Rx2 [ ![Download](https://api.bintray.com/packages/vershininds/maven/Rx2Interactor/images/download.svg) ](https://bintray.com/vershininds/maven/Rx2Interactor/_latestVersion)
```groovy
implementation 'com.github.vershininds:mixture-rx2interactor:x.y.z'
```


License
=======

    Copyright 2017 Dmitriy Vershinin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
