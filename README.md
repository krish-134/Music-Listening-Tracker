# Music-Listening Tracker

## Proposal
This application will track the statistics for 
someone's *music-listening history* and will show graphs with this 
information. Music listeners who are interested in 
understanding their listening habits will enjoy using this application.
I myself decided upon this project because I love 
listening to music, and it seemed like this idea would pair nicely 
with programming.


*User Stories*
- As a user, I want to be able to *add* an **artist/band** and the **songs**
I heard to my listening history list
- As a user, I want to be able to *add* the **amount of time** I've spent listening
to a given song
- As a user, I want to be able to *view* the list of **artists/bands** on my listening history
- As a user, I want to be able to *view* the **amount of time** that I have spent
listening to a given **artist/band** or **song** from my listening history

*Phase Two User Stories*:
- As a user, I want to be able to *save* my **list of musicians** to file
- As a user, I want to be able ot *load* my **list of musicians** from file
- As a user, I want to be *reminded* about **saving my file** when I quit the program


# Instructions for Grader
- To add multiple Xs (Musicians/Songs) to a Y (music library or musician) click the 'add music' button in GUI, 
the prompts given by the program
- To add more music to the library, simply hit the 'add music' button again
- You can locate my visual component by clicking the "view statistics" button, but be sure to 
have some musicians in the library (either by loading or manually adding) in order to see a bar plot!
- There are two types of plots, the initial plot shown displays all the musicians in the 
library and the amount of time spent listening to each, but click the button saying 'find musician' to
display a plot of a particular musician in the library that shows their songs
- Save the state of the application by clicking the 'save current library' button in the main menu
- Reload the state of the application by clicking the 'load library' button from the main menu

# Phase 4: Task 2
Tue Apr 02 19:35:20 PDT 2024 \
Added musician: Kendrick Lamar \
Tue Apr 02 19:35:51 PDT 2024  \
Added musician: Tame Impala \
Tue Apr 02 19:36:08 PDT 2024 \
Added song, "Nangs", to musician: Tame Impala \
Tue Apr 02 19:36:22 PDT 2024 \
Played song, "The Recipe", 3 more times. 


# Phase 4: Task 3

Upon looking at my UML diagram, I don't think that it is so awful to understand, but there is definitely room to 
the design of the application. I think a notable feature that I would change about my program's implementation 
altogether is that instead of using array lists, I would use a some type of Map data structure (like HashMap). 
The reason for this is that right now, a lot of iterating and searching needs to be done if there is a large list
of musicians in the music library with lots songs (performance could potentially be slow). I think that if I were 
to use a HashMap, I could make each musician's name the key in the map, and then the list of songs would be the
corresponding value. This way, I can potentially make the program's execution more efficient, and my 
implementation for finding a particular musician's song would be more readable and I would be able to keep most
if not all of my functionality from beforehand with slightly modified implementation.