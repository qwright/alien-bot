# alien-bot
Alien-bot is a project for COSC 310 where you can talk to a friendly alien named E.T. 
If you're nervous, type help to get a list of commands!

## Contributors
```
Alex Green
Nick Mcgee
Paige Latimer
Quinn Wright
```

[Check out the repo on GitHub] (https://github.com/qfwright/alien-bot)

# Installation

1. Clone this respository

`git clone https://github.com/qfwright/alien-bot.git`

2. Download and add RiTa jar to your classpath via 

`https://rednoise.org/rita/download.php`


3. Do a Maven update and install for the rest of the dependancies
4. Launch with your IDE of choice or compile locally.

# Class Structure
```
.
+-- Main
|   +--Chatroom 
|   +--Alien
|   |  +--SentimentAnaylzer
|   |  +--POSTagging
|   |  +--Stemmer
```

#Features

**Graphical Interface**

The chat-bot uses a beautiful and easy on the eyes graphical interface made with JavaFX for the user to interact with. As well as a dynamic alien to give visual feedback!

![alt text](https://github.com/qwright/alien-bot/readme_res/gui_example.jpg)

**Sentiment Analysis**

Utilizing Stanford CoreNLP APIs, this chat-bot reads from a set of pre-programmed conversational phrases and gets an evaluation of attitude from a scale of 0-4. In this case, the alien class is given a mood every response which triggers a corresponding image in the GUI for a more visceral experience.

![alt text](https://github.com/qwright/alien-bot/readme_res/sentiment_frames.jpg)

**POSTagging**

RiTa library allows part-of-speech (POS) tagging for grammatical accuracy in the response given by the Alien. This implementation focused on sentence tense.

![alt text](https://github.com/qwright/alien-bot/readme_res/pos_example.png)

**Stemmer**

The word-stemmer is used in this project to more easily implement pattern matching from a very wide range of possible sentence structures. i.e. The alien can pick out keywords easily to give better responses than a null entry.

![alt text](https://github.com/qwright/alien-bot/readme_res/stemmer_example.jpg)



