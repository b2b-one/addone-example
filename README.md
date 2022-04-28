# Example AddONe - plugin for ONe Ecommerce backoffice system

This AddONe example contains frontend (vue3) and backend (spring boot) applications that show how the plugin works and how to authorize it.

An instruction of creating AddONes and registering plugins in ONe Backoffice is available here: https://plugins-docs.one.unity.pl/

## Requirements
Frontend part of plugin requires `@one-commerce/sdk-backoffice-vue3` package, version `1.14.0` or higher. 
SDK package via `$one` gives access to data and methods that have been made available to him in the backoffice, e.g. to information about the operator, the ability to download product data, etc.
SDK has also an authorization mechanism so that the AddONe developer does not have to create a separate login system.

The AddONe plugin does not require a backend part to work. In this example, the backend part is used to present the plugin authorization in the backoffice.

## How authorization works 
Frontend app asks ONe Backoffice for an authorizatioin token in `$one!.authorization.getAuthToken()` method.
Then backend part of AddONe can send request to Backoofice API to validate token, if valid it means that the user is authorized.
The token is cached in the browser until it is not close to expiry (1 minute to expire).
