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
Create an app called *My First Poq App* using Template Client. You can use [this app icon](https://drive.google.com/file/d/1POfhAwLQ8ViLxlChTS15z1w3JXoouTLG/view?usp=sharing).

## 2. Country config
We use the file *country_config.json* to set up configuration per country. Template Client has 1country config per flavour. Each item in the json file defines 1 country configuration in the app.

To identify the catalogue for the country we use appId and appIdentifer. [Mighty Bot](https://developer-uat.poq.io/) is an internal tool where you can change at runtime values. Android only use a smallnumber of MightyBot settings. To identify the version of MightyBot to use, we use versionCodeinside country config.

### Challenge
Set *My First Poq App* country_config.xml with versionCode 12.0 and this configuration:

  property | UAT | STAGING | PRODUCTION 
 --------- | --- | ------- | ----------
appId | 173 | 167 | 167 
appIdentifier | ca315772-4803-4b48-ae99-5683133770e6 | fb61abc6-aa85-4a7f-b032-8c3d5cf31d29 | fb61abc6-aa85-4a7f-b032-8c3d5cf31d29

**You should be able to run your completely functional app!

## 3.- App styling
We have [app styler](https://appmanager.poq.io/style) which is where all the styles are configured. App styler will provide a JSON and a zip file with resources.

* For the JSON file, you will need to use our Android Studio plugin.
* The zip file contains icons and fonts
  * For the icons, you can use Resource Manager from Android Studio to import them;
  * Resource Manager -> + -> import drawables -> select the folders android/iconand android/images;
  * For the fonts, create a new resource folder called font and just drag and drop thefonts;
  
  For more information about how to obtain the JSON file and the Android Studio plugin visit [click here](https://docs.poqcommerce.com/guides/app-styler/publishing-your-changes.html)

### Challenge
Use this [JSON](https://drive.google.com/file/d/1PFyAPsNt__TM9m-WXbsdXhQ9LPf3GqhG/view?usp=sharing) and this [zip file](https://drive.google.com/file/d/1ziJzRXZd-XKS6tLAhp8hZ8nFa1J5k6ND/view?usp=sharing) to style *My First Poq App*

## 4. app_config and feature flags
We use *app_config.xml* files to set feature flags and configuration parameters. To override these settings in your app, you just need to create an XML property with the same name.

### Challenge
One of these parameters is *aspect_ratio_image_view* which by default is set to 1. This parameter configures the aspect ratio of the images in the app. Change this parameter in your app to adjust the aspect ratio of your images;

## 5. Replace a layout
If you need to modify how a part of your app looks like, you can override the layout and create your own. To replace a layout:
* The layout has a layout alias:
  * Create your new layout file avoiding the layout name from platform
  * Override the layout alias xml property pointing to your new layout
* The layout does not have a layout alias:
  * Create your new layout file matching the layout name from platform
  
### Challenge
Modify the cart items to show a new message under the product title “Hello World!” and style your new text using app styling style *label_body*.
  
## 6. Dependency injection
We use Dagger and Koin as dependency injection frameworks. We are currently moving from Dagger to Koin. Sometimes, when you need to modify the behaviour of the app, you will need to swap platform instances with your own implementations. There are 3 options depending on thescope and the framework we use to inject that dependency

### 6.1. Replace Dagger singleton dependency
We have our custom solution to replace Dagger singleton dependencies. To override asingleton dependency you need to do:
* Create a new interface and annotate it with *@InjectionOverride*
* Create a new method, as you would do to provide a dependecy with Dagger, andannotate it with *@PoqProvision*.  There are 2 types of PoqProvision:
  * Implementation: You are injecting all its dependencies and Dagger will call the constructor
  * Provider: You are not injecting all its dependencies or the constructor is notannotate it with *@Inject*. You need to create a PoqProvider and call the constructor yourself in the get method.

```kotlin
@InjectionOverride
interface OverriddenInjections {    

    @PoqProvision(ProvisionType.IMPLEMENTATION)    
    fun provideMyCustomImplementation(myImplementation: MyImplementation): InterfaceToReplace    
  
    @PoqProvision(ProvisionType.PROVIDER)
    fun provideMyCustomImplementation(myImplementationProvider: MyImplementationProvider): InterfaceToReplace
}

class MyImplementationProvider @Inject constructor(
    private val extraDependency: ExtraDependency
) : PoqProvider<LinksDependencyProvider.Builder> {

  override fun get(): LinksDependencyProvider.Builder {
      return MyImplementationProvider(extraDependency)
  }
}
```
## 6.2. Replace Dagger no-singleton dependency
To replace no-singleton dependencies that are injected with Dagger you need to:

* Identify in which platform Dagger module your dependency is being provided. You canuse cmd+shit+f  -> scope and search for *provide{Dependency}*;

* Copy the content of that module to a new file and replace the dependency;

* Create a new ActivityBuilder module that links the activity with your new module. To keep this maintainable, we create a file called BaseActivityBuilder that contains all thenon-modified Activity injections and another file called {NameOfTheClient}ActivityBuilderwhere are all the modified injection are;

* In your application, in @PoqComponent(extraModules = [...]) remove, if you have it, platform ActivityBuilder and add your 2 new modules;
