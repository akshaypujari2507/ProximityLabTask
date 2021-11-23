# ProximityLabTask


Built with 🛠
Kotlin - First class and official programming language for Android development.
Coroutines(viewmodel scope) - For network call and asynchronous
LiveData - Data objects that notify views when the underlying database changes.
ViewModel - Stores UI-related data that isn't destroyed on UI changes.
Material design guideline.

# The app has following packages:


# data: It contains all the data accessing and manipulating components.
	I. remote
		1. socket provider services
		2. models for api
	II. repository
		1. Repository for remote data.
# ui: view and viewholders along with their corresponding Components
	1. adapters 
	2. interface
	3. view
		i. viewholder
	4. viewmodel
		i. factory
# util: Util class


## Description
App loads the data of AQI from the "ws://city-ws.herokuapp.com/" service .
Records is displace in list form. 
Sample data load:
[{ city:"Mumbai", aqi:"150.23145"}, { city:"Delhi", aqi:"250.23145} , . . . ]

