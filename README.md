# Player Stats

Player Stats is an Android project for personal and non-profit use, where players can consult their statistics and, using an algorithm, generate some statuses for the players based on an average of their KDAs.
The APIs we are using are: champion, summoner, account and match.

This software are a native Android app develop with kotlin language and MVVM architecture.

### Technologies:
* [Navigation](https://developer.android.com/guide/navigation)
* [Preferences Data Store](https://developer.android.com/codelabs/android-preferences-datastore#5)
* [View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Retrofit 2](https://square.github.io/retrofit/)
* [Room](https://developer.android.com/training/data-storage/room)
* [Firebase](https://firebase.google.com/?hl=pt)
* [Material Design 3](https://m3.material.io/)
* [Riot API](https://developer.riotgames.com/docs/portal)

This app has a single activity with an bottom navigation and one container view to host all app fragments.
[App color pallet](https://brand.riotgames.com/en-us/league-of-legends/color)

___

### Database

Currently the only offline functionality is the list of recent summoner searches.
When a summoner is successfully found, its information is saved in the database and displayed in descending order from the last update date, this way, the last summoner found will be the first in the list.
By default, all insertions and updates adopt replacement as a data conflict strategy.

___

### Repositories

Repositories are created by context and fetch the data (internal or external) requested by the view model. This is a good way to avoid duplication functions and repository classes.

___

### Data flow

The data flow must occur as follows:

[View] >> [ViewModel] >> [Repository]
[View] << [ViewModel] << [Repository]

In this case, the view makes a call to the view model, which in turn requests the data from the repository.
It is important to mention that there must be no logic in the view (everything must be exported to the view model) to facilitate the implementation of unit tests in the future.

___

### Future Implementation

I will list some features that I intend to add in the future to complement the app and carry out a first production release on the Google Play Console:

- Add graph statistic data of user match historic;
- Modified app design;
- Create private backend service to return champions data (principally images)
- Add screen page;
- Add home page (free week);


Last Update: 03/12/2023