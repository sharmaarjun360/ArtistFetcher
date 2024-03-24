# ArtistFetcher
Application to fetch Artist data from iTunes. This application fetches and displays Artist data from the following iTunes URL:
https://itunes.apple.com/search?term={any ArtistName}

Check out executed UI tests here: https://github.com/sharmaarjun360/ArtistFetcher/wiki

The key purpose of the application is to showcase Integration testing and Unit testing capabilities.

#1 Flavours               : Application has multiple flavours(Environments) like Prod and QA. It has restrictions and privileges for the same.

#2 Mock data: It has Mock data to verify the parsing of data for model classes and Junit testing.

#3 Custom Interceptor: The application has custom Interceptor in place which Intercepts the web API calls in QA mode and provides controlled testing when required

#4 Unit testing: JUnit 4 is being used to perform Unit testing 

#5 Instrumentation testing: Espresso is being used to perform extensive UI testing on all components and web API calls as well by using the "Idling resources" feature.

#6 Retrofit: Web API calls are being managed by Retrofit

#7 GSON & JSON: Parsing of data though verified by unit testing however is performed by GSON and JSON libraries.

#8 Glide: Image processing is done by using the glid library

#9 Callbacks: Notification to update adapter as well as UI data post web API response is managed using Retrofit and custom callbacks(Interface).
