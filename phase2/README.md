# Project Phase 2

### JavaFX setup
Get JavaFX 11 SDK for your respective OS from [JavaFX Download](https://gluonhq.com/products/javafx/) and unzip it to a desired location. Make sure that the project itself is running a Java SDK greater than or equal to version 11. Then go to `File -> Project Structure -> Libraries` and add the JavaFX 11 SDK as a library to the project, specifically the presentation module. Make sure it points to the `lib` folder. Then go to `Run -> Edit Configurations` and add the following line `--module-path "/path/to/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml` to the VM options located under the `Application -> Main -> Configuration` tab. **Note that "/path/to/javafx-sdk-11.0.2/lib" needs to be replaced with where ever you unzipped your JavaFX SDK lib folder**. Click `Apply` and close the dialog. Mark the res folder and the src folder as a resource root and source root respectively inside the presentation module. Now the module will recognise all the javafx.* related code and can be run properly.

For more detailed instructions, go to the [JavaFX Setup](https://openjfx.io/openjfx-docs/#install-javafx "JavaFX Getting Started") and clicking the `JavaFX and IntelliJ -> Non-modular from IDE` link.

### JSON setup
Get JSON simple (1.1.1.jar) from [json-simple Download](https://code.google.com/archive/p/json-simple/downloads) and save it to a desired location. Then go to `File -> Project Structure -> Libraries` and add the library to **both the core and presentation modules**. Make sure it points to the .jar file itself. Click `Apply` and close the window. Now when importing `org.json.simple.*`, there should not be any import errors.

### Communication between back-end and front-end
All controller methods will now return a JSON object similar to the one outlined below:
```
{
    "status": one of (success), (warning) or (error),
    "result": statement describing the status,
    "data": [{ list of JSON formatted entities }]
}
```
Any data passed to the front-end will be transformed into front-end models by the adapters. For complex functionality, such as creation of entities, the UI's presenters will pass a JSON object with all the necessary attributes.

### Data Preservation
The back-end will be storing all the information in `.ser` files, and each time a Controller is instantiated, the necessary Gateway classes are called to deserialize the associated files. Moreover, each time data within a Use Case class is manipulated, it will save the changes by serializing the associated files.

### DateTime Picker plugin
Due to a bug in the DateTimePicker utility plugin, when inputting a date/time, the user must press `RETURN` or `ENTER` after making any changes for the field to save your changes. This is especially pertinent when creating and rescheduling events. 

### Messaging
Messaging is now a hybrid between email and direct messages found of social media platforms. Users will have three different types of inboxes containing message threads, but the actual content will be displayed as a scrollable conversation history.

## Extra Features
We have implemented the following features in Phase 2:

    1) Two new user types: VIPs that can attend VIP events; and administrators that can delete message threads, unlock accounts, remove events and change attendees' VIP status
    2) Instead of having an organizer creating accounts, an administrator now has that responsibility
    3) Each event has a maximum capacity and VIP boolean, and are able to be changed later
    4) Enhanced messaging features, including transferring threads between inboxes
    5) Additional scheduling constraints and suggested list of rooms when creating an event
    6) Incorporated constraints in (5) into creating a room
    7) GUI using JavaFX which follows MVP
    8) Enhanced login features, such as account registration, security questions, resetting of passwords, login logs, locking of accounts after multiple failed login attempts
    9) Users now have a friends' list, can add/remove friends and see common events with friends
    10) Variable number of speakers at events with variable duration measured in minutes
    11) Events can be cancelled/rescheduled by all organizers