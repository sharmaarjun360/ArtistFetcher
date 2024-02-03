# ArtistFetcher
Application to fetch Artist data from iTunes. This application fetches and displays Artist data from the following iTunes url:
https://itunes.apple.com/search?term={any ArtistName}

Key purpose of the application is to showcase Integration testing and Unit testing capabilites.

#1 Flavours               : Application has multiple flavours(Environments) like Prod and QA. It has restrictions and privilages with respect to the same.

#2 Mock data              : It has Mock data to verify parsing of data for model classes and Junit testing.

#3 Custom Interceptor     : Application has Custom Interceptor in place which Intercepts the web API calls in QA mode and provides controled testing when required

#4 Unit testing           : JUnit 4 is being used to perform Unit testing 

#5 Instrumentation testing: Espresso is being used to perform extensive UI testing on all components and web api calls as well by using "Idling resources" feature.

#6 Retrofit               : Web api calls are being managed by Retrofit

#7 GSON & JSON            : Parsing of data though verified by unit testing however is performed by GSON and JSON libraries.

#8 Glide                  : Image processing is done by using glid library

#9 Callbacks              : Notification to update adapter as well as UI data post web API response is managed using Retrofit and custom callbacks(Interface).
