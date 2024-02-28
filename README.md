# Couchthread - Manage your messages with Couchbase 
[![Generic badge](https://img.shields.io/badge/Version-1.0-<COLOR>.svg)](https://shields.io/)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity)
![Maintainer](https://img.shields.io/badge/maintainer-raphael.chir@gmail.com-blue)
## Use case
### Mobile application purpose
This is a Group Messaging application able to work offline and to resynchronize messages when network is available. Version 1.0 starts with a simple Basic authentication to access to a dashboard to view discussion topics with the rights associated.
### Description
As a user, I authenticate on the app and I access to my personal dashboard. I see my user profile and all the threads I have access with the level of right I have on each of them. (In this version, the right will be grant directly on database level precisely on the user profile json document)
## Architecture overview
![Couchbase](https://docs.couchbase.com/sync-gateway/current/_images/svr-sgw-cbl.png)

**Couchbase Capella** (trial version) will be used to get a Couchbase cluster and a Sync Gateway (provide by Capella App Services). https://cloud.couchbase.com/

Two parts are considered in this architecture :
- **The local part** composed by mobile application developed with Kotlin/Jetpack Compose. This mobile application embeds a Couchbase Lite local database (application dependencies). Couchbase Lite local database establishes a websocket to communicate with Couchbase Sync Gateway in dual mode (push/pull replication).

- **The cloud part** composed by Couchbase Sync Gateway, in charge to route data to mobile device from Couchbase Server through a RBAC logic to implement.
Couchbase Sync Gateway store data to Couchbase server. These data comes from mobile device and are controlled according to a RBAC logic to implement.

Note that conflict resolution might occurred and it exists 2 cases :
- **Pull** : Conflicts are directly handled on mobile application side thanks to Couchbase Lite capabilities
- **Push** : If a conflict is detected, synchronisation is rejected to be handled on mobile application side (409 error)

## Kotlin Architecture
For separation of concerns and to keep it simple (stupid). 2 main packages are separated : 
- **ui** that contains 
    - **themes** with generic jetpack compose components ready to use
    - **utils** as it is described
    - __*folder*__ *(to be renamed)* corresponding to a screen. Each time we introduce a screen we create a new folder related to it. Considering as a template this folder contains 4 kotlin files :
      - **xxxScreen.kt** contains @Composable screen components
      - **xxxViewModel** manages events and view model bindings
      - **xxxViewState** the ui model bean keep the state
      - **xxxViewEvent** event declaration
- **data** is the package which ui depends. It contains services and infrastructure interface and implementations.