Mediator
-----------

The mediator pattern defines an object that encapsulates how a set of objects interact. This pattern is considered to be a behavioral pattern due to the way it can alter the program's running behavior.

Usually a program is made up of a large number of classes. So the logic and computation is distributed among these classes. However, as more classes are developed in a program, especially during maintenance and/or refactoring, the problem of communication between these classes may become more complex. This makes the program harder to read and maintain. Furthermore, it can become difficult to change the program, since any change may affect code in several other classes.

With the mediator pattern, communication between objects is encapsulated with a mediator object. Objects no longer communicate directly with each other, but instead communicate through the mediator. This reduces the dependencies between communicating objects, thereby lowering the coupling.

The entry point of Mediator is Singletone - DataControler class.

Google Palette
--------------

One of the defining features of material design is the use of color to compliment and emphasize content on the screen. Using the Palette class, developers can extract prominent colors from a bitmap for use in their apps to customize user interface elements.

In this article, you will learn how to create a Palette object from a bitmap. Contained within each Palette is a set of Swatch objects that will allow you to work with specific color profiles and a list of visible colors from the image.

To get started, you will need to import the palette support library into your project by including the following line in the dependencies node of your project's build.gradle file. Since this is a v7 support library, the Palette related classes are available back to Android API

```groovy
compile 'com.android.support:palette-v7:+'
```

After running a gradle sync on your project, you are able to generate a Palette from a bitmap. This can be done using the Palette.Builder either synchronously by calling the generate() method without any parameters, or asynchronously by calling generate(Palette.PaletteAsyncListener listener). Because it can take time to create the Palette, it is recommended that the synchronous method only be called from a background thread. In addition to the two generate methods, the Palette.Builder class has a couple of other useful methods that come with their own trade-offs:

maximumColorCount(int numOfSwatches) allows you to change how many Swatch objects should be generated from the bitmap. The default for the builder is 16. The more Swatch objects you generate, the longer it will take to generate the Palette.
resizeBitmapSize(int maxDimension) resizes the bitmap so that its largest dimension will only be as large as the passed value of this method. The larger your bitmap, the longer it will take to generate a Palette. Likewise, smaller bitmaps will process faster, but you will lose out on color precision.
The following code snippet shows how to create a bitmap from a local resource and asynchronously create a Palette object.


```java
Bitmap bitmap = BitmapFactory.decodeResource( getResources(), R.drawable.union_station );
Palette.from( bitmap ).generate( new Palette.PaletteAsyncListener() {
    @Override
    public void onGenerated( Palette palette ) {
        //work with the palette here
    }
});
```