# Project Phase 2

### JavaFX setup
Get JavaFX 11 SDK for your respective OS from [JavaFX Download](https://gluonhq.com/products/javafx/) and unzip it to a desired location. Then go to `File -> Project Structure -> Libraries` and ad the JavaFX 11 SDK as a library to the project. Make sure it points to the `lib` folder. Then go to `Run -> Edit Configurations` and add the following line `--module-path "/path/to/javafx-sdk-11.0.2/lib" --add-modules javafx.controls,javafx.fxml` to the VM options located under the `Application -> Main -> Configuration` tab. **Note that "/path/to/javafx-sdk-11.0.2/lib" needs to be replaced with where ever you unzipped your JavaFX SDK lib folder**. Click `Apply` and close the dialog. Now the presentation module will recognise all the javafx.* related code and can be run properly.

For more detailed instructions, go to the [JavaFX Setup](https://openjfx.io/openjfx-docs/#install-javafx "JavaFX Getting Started") and clicking the `JavaFX and IntelliJ -> Non-modular from IDE` link.

### Data Persistence
Remember to fully log out of the program after you are done using it. Do not stop or exit the program while it is still running, as it will cause issues with saving and information persistence. If you come across an unexplainable exception (most commonly null-pointer), the quickest way to fix this is to clear all the data from the *.ser* files and start over.