project-pir-android
===================

Android native application

The Volley framework is used for all network requests. The global request queue is maintained within
ApplicationController which must be used for all requests being made.

Executor classes making requests to the API must extend the PIRRequestExecutor class.

Executors must also create a createRequest method with return type JsonObjectRequest or JsonArrayRequest.

<b>Current structure for screens requiring API call(s):</b>

Activity --> Fragment --> Loader --> RequestExecutor

