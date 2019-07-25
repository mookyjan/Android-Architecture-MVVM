# SpaceX

## Android SpaceX API client.

You task is to prepare application that will fetch information about launch platforms from SpaceX REST API and display it to the user.

API documentation can be found [here](https://docs.spacexdata.com/)

You can use any 3rd party libraries you want. Target SDK 28, min SDK 15.
Visual design & UX of your choice.

### Basic version:

* The application should display information about launch platforms used by SpaceX (all of them).
* Data should be displayed as a list containing information about name, status, and thumbnail photo.
* Clicking on each element of the list should display details about each platform (name, status, location, detailed description, etc).

### Optional: (If you have spare time, its not super important or anything)

* Add possibility to view launch site location on the map, for this use https://developers.google.com/maps/documentation/android-sdk.
* Add an option to view a list of vehicles launched from a certain location (Including rocket details, you will find information about that in API documentation).


### Structure of the code ###
this task is based on MVVM architecture pattern
and the libraries used here are
Retrofit,Rxjava2, Dagger2 and Glide and Android Architecture component.
and for the simplicity of this project many things have been kept sample
like ErrorHandling , ViewDetails or RocketList Screen and its detail.
comments are written with the function that what it will do.
also TODO are given in the area which we can improve more.
First screen will show the list of LunchPads and by clicking on its item
it will go to the Detail screen and on detail screen by clicking on "LunchPad Vehicle"
will get list of LucnhPad for the current LunchPad Location with its Rocket Properties.
and also by clicking on "ViewMap" will open Map location of current LunchPad.
