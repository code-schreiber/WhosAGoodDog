[![Build Status](https://travis-ci.org/code-schreiber/WhosAGoodDog.svg?branch=master)](https://travis-ci.org/code-schreiber/WhosAGoodDog)
[![Author](https://img.shields.io/badge/Author-code--schreiber-1A237E.svg)](https://github.com/code-schreiber)
[![Android API](https://img.shields.io/badge/Android_API-21%2B-A4C639.svg)](https://github.com/code-schreiber/WhosAGoodDog/blob/master/buildSrc/src/main/java/Dependencies.kt)
[![Open Source Love svg3](https://badges.frapsoft.com/os/v3/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)  

<p align="center">
 <b>🐾 Who's A Good Dog! 🐶</b>
 <br>
 <img src='https://github.com/code-schreiber/WhosAGoodDog/raw/master/app/src/main/ic_launcher-web.png' width='100' height='100'/>
 <br>
</p>
  
This project provides an demo app that interacts with the [dog.ceo API](https://dog.ceo/dog-api/).  

The app consists of
- A one-time dummy login screen
- A main view with 2 tabs: Home and MyDog
- On the MyDog tab a random dog image and it's breed's name will be shown, to show another image use the "That's not my dog!" button
  
###### Technical details and used technologies
This project is written in Kotlin and uses Model-View-Presenter for better testability.

The app is built and unit tested with the help of Travis CI.

Unit tests cover the important parts of the project like business logic, error handling and presenter-view interaction
