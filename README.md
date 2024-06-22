# Table of contents

---

## 1. [How to run](#How-to-run)

## 2. [Structure Definitions](#Structure-Definitions)

## 3. [Task Definitions](#Task-Definitions)

---

# How to run

- ### First you need to clone the repository.
- ### Then you need to have Java installed on your computer.
- ### After that to run simply run main function in App/VisualApp.java file. You can also run App/App.java file to see how to use deck and hand classes.

---

# Structure Definitions

## Packages:

- ### [App](#App)
- ### [Cards](#Cards)
- ### [CustomComponents](#CustomComponents)
- ### [CustomEnums](#CustomEnums)
- ### [Predictions](#Predictions)
- ### [ProgressEngine](#ProgressEngine)
- ### [VisualEngine](#VisualEngine)

---

## App

### Contains main class for running the application.

- ### VisualApp.java
  Main class for running the application.
- ### App.java
  First prototype of the app used for debugging. There you can understand how to use deck and hand classes.
- ### Settings.java
  Class for storing settings of the app.

---

## Cards

### Contains classes for cards. Such as Deck, Cards and Hand.

- ### Card.java
  Class for storing data about card including value, suit and image. Also contains method for serialization.
- ### Deck.java
  Class for storing data about deck of cards. It is used for storing cards, shuffling them and drawing them to hands.
  Also, it contains logic for creating cards and adding images to them.
- ### Hand.java
  Class for storing player's hand. It is used for storing cards, drawing them and checking if hand is empty.

---

## CustomComponents

### Contains custom components for the app.

- ### CardCanvas.java
  Class for drawing cards on screen.

---

## CustomEnums

### Contains custom enums for the app. Such as Suit.

- ### Suit.java
  Enum for storing suits of cards.

---

## Predictions

### Contains classes for prediction mechanism. Such as Prediction and Predictor.

- ### Prediction.java
  Class for storing data about prediction including player and amount of steps till win.
- ### Predictor.java
  Class for predicting player's win. It is used for predicting player's win based on his hand, and hand of his opponent.

---

## ProgressEngine

### Contains classes for saving and loading game progress.

- ### ProgressEngine.java
  Class for saving and loading game progress. It is used for saving and loading game progress.

---

## VisualEngine

### Contains classes for visual representation of the app. Mainly it's responsible for getting cards images from one image.

- ### VisualEngine.java
  Class for getting cards images from one image.

---

# Task Definitions

- [X] Create Hand class (Danila)
- [X] Create Deck class (Danila)
- [X] Create Images system for drawing cards (Danila)
- [X] Create system for drawing cards on screen (Danila)
- [X] Create system for game process (Danila)
- [X] Create Predictions system (Danila)
- [X] Create Spoiler system (Danila)
- [X] Create menu buttons (Danila)
- [X] Create Restart button (Danila)
- [X] Create Save system (Danila)
