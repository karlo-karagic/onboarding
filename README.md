# Android-Developer-Onboarding

This doc will guide you through your onboarding, helping you to create a new app and showingto customise the most common parts of the app. We have [PoqDocs](https://docs.poqcommerce.com/index.html) that contains more detaildocumentation and the [Android playbook](https://github.com/poqcommerce/Android-Developer-Playbook) with useful information and resources.You can find the solution of the challenges, commit by commit, in this [repository](https://github.com/poqcommerce/Poq.Android.MyFirstPoqApp).

## 1. Template Client
To create a new app, we use [TemplateClient:](https://github.com/poqcommerce/Poq.Android.TemplateClient)
* Download the repository
* Edit the file [renameTemplateScript](https://github.com/poqcommerce/Poq.Android.TemplateClient/blob/dev/renameTemplateScript) in your local copy renaming the fields“NEW_NAME-*” for the name of your app with the correct format in each field
* Execute the script
* Create git and a new git tag for your current branch. Template client comespreconfigured to create the app release name using the closest git tag.

**Before opening** your new project, you will need to add a user name and password to be able todownload the Poq platform as a Gradle dependency. To do so, visit the [development section](https://github.com/poqcommerce/Android-Developer-Playbook/blob/master/Tools/Development.md) in the android playbook and go to ***Gradle - Nexus User***.

* Open your new project with Android Studio choosing the folder *Poq.Android.TemplateClient-dev/Poq.Android.{ClientName}*. You can rename theen closing folder from *Poq.Android.TemplateClient-dev* to your need.
* Add your app icon as you would do for a regular app.

### Challenge 
Create an app called ​My First Poq App ​using Template Client. You can use [this app icon](https://drive.google.com/file/d/1POfhAwLQ8ViLxlChTS15z1w3JXoouTLG/view?usp=sharing).
