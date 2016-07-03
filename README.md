# "Movie Time" Android app

This is an example application of [Uncle Bob's Clean Architecture](https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html) design.

## About the app

This application can show the most popular movies, TV shows, and people to the user.
The data provider is [this movie database](https://www.themoviedb.org/).
It also lets the user to search, and navigate back and forth between items.

### Features

#### Home screen

The home screen with a TabLayout and a ViewPager looks like this:

<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/screen9.png" width="200">
<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/screen8.png" width="200">
<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/screen7.png" width="200">

#### Search

The app handle the search with the Android's built-in SearchView, so you just have to press the magnify icon at the top right corner.
You can see it in action here:

<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/screen1.png" width="200">
<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/screen2.png" width="200">

#### Proper error management

Do you hate it when there is only a toast about a "something went wrong" message and you have no idea what to do with the app? 
Me too. In this app there are different error types, and the UI react to them differently. You can see for example a no 
internet connection error in the following image:

<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/screen3.png" width="200">

#### Movie details

If you click on a movie or a TV show you'll see detailed information about it. It looks like this:

<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/screen6.png" width="200">
<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/screen5.png" width="200">

Of course you can do the same with a person:

<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/screen4.png" width="200">

#### Animation and design

I followed the [Material Design Guidelines](https://design.google.com/) where I could, and the app also contains some great animation. Unfortunately most of the only visible on Android 5.0 or above.

## About the Architecture

At the first look, the code could look a bit overcomplicated, but please let me explain the basics of this
clean architecture interpretation.

The main reason what I followed is the ["Separation of concerns"](https://en.wikipedia.org/wiki/Separation_of_concerns).
The application define two, well separated module. One for the business logic (this is the "logic" java module), 
and one for the Android specific classes, such as UI elements, Adapters, Database storeage classes...
The business logic doesn't care about the Android, for them it's just the "delivery mechanism". Because of this there is
no need to see the classes there. The business logic contains only the actual logic, and it has no connection with the 
Android classes whatsoever.

An image worth a thousand word, so please see the following diagrams:

<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/arch1.png" width="1000">

<img src="https://raw.githubusercontent.com/gabornovak/MovieApp/master/doc/arch2.png" width="1000">

### Plugin

The Plugin is responsible to solve a (usually) simple problem. This problem is always a system dependent problem.

___

An example:

**A Plugin creates a notification.**

This is a system dependent problem, because the notification creation is the delivery mechanism's job. However the logic will ask for it but it has nothing to do with the implementation itself. An other example is a [ConnectionPlugin](https://raw.githubusercontent.com/gabornovak/MovieApp/master/logic/src/main/java/hu/gabornovak/movieapp/logic/plugin/ConnectionPlugin.java)
to decide, there is a valid internet connection or not.

### Gateway

The Gateway usually solve a data gathering issue. **It can use Plugins to do so!**.

___

An example:

PersonGateway which uses the RestPlugin to download the Person information, and uses the JsonParserPlugin to parse the result. 
It can cache the result locally, or do whatever it likes to make its life easier, but from the business logic's point of view it doesn't matter. The only important thing is that a gateway provide the requested information somehow.

### Interactor

The interactor contains the actual business logic. It solve the issues, calculate the result, composing the whole application logic.

___

An example:

A Movie interactor should be able to use the Movie gateways to search for Movies, or to load the popular Movies.

### The Real Power

The real power lays in the separated business logic, and the system dependent implementation. If we have to change something we
always know where to look. The code is well separated for testing. And if we have to change something, we won't be doomed, 
because it's not in the heart of the application, it will be on the "side", in the Android app where we only need to implement a new Plugin / Gateway for the new method and replace the old one.

### Limitations

There are no black and white application in the real world. Not every class, or method can be intergrated into this 3 category
(4 with **Entities**, but it's not even worth mentioning), but I do my best to show you that this is a good architecture to 
create a maintainable code.

## Developed on

The app was developed on a Nexus 6 (Android 6.0) device.
