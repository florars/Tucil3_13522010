# Word Ladder solver - made for Tucil 3 IF2211 Strategi Algoritma, 2024
by Maria Flora Renata S. - 13522010

## Description
This program is a word ladder solver. A word ladder is a game where you have to transform a word into another word while only changing one letter at a time.

## Requirements
Java

## How to run
```
javac Main.java
java Main
```

The program will open a window. If there appears to be no answer shown, try making the window fullscreen.

There are 3 algorithms to choose from, Uniform Cost Search, Greedy Best First Search, and A*. Enter the starting word and goal in their respective fields, then press the button of the algorithm you want to use.

The dictionary used for this solver is located in ```src/Dictionary.txt```. You can change it to another dictionary as long as the filename and extension is the same and each word is in its own line.

An alternative version that uses CLI instead of GUI exists in Main2. To run, do
```
javac Main2.java
java Main2
```

