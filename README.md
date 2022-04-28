# Example AddONe - plugin for ONe Ecommerce backoffice system

This AddONE example contains frontend (vue3) and backend (spring boot) applications that show how the plugin works and how to authorize it.

An instruction of creating AddONes and registering plugins in ONe Backoffice is available here: https://plugins-docs.one.unity.pl/

## Requirements
Frontend part of plugin requires `@one-commerce/sdk-backoffice-vue3` package, version `1.14.0` or higher. 
SDK has an authorization mechanism so that the AddONe developer does not have to create a separate login system.

## How it works 
Frontend app asks ONe Backoffice for an authorizatioin token in `$one!.authorization.getAuthToken()` method.
Then backend part of AddONe can send request to Backoofice API to validate token, if valid it means that the user is authorized.
The token is cached in the browser until it is not close to expiry (1 minute to expire).
