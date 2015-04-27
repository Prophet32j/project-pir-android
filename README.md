project-pir-android
===================

Android native application

The Volley framework is used for all network requests. The global request queue is maintained within
ApplicationController which must be used for all requests being made.

Use JSONAuthenticatedRequest when making an authenticated request, passing the token in the request header.


All Fragments and Activities must extend RMBaseFragment and RMBaseActivity respectively.
