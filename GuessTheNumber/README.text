# Bulls and Cows Number Guessing Game - Round Service README

This README provides an in-depth overview of my individual contribution to the Bulls and Cows Number Guessing Game project. I was responsible for designing and implementing the Round Service functionality, which handles the management of rounds in the game, including guessing, result calculation, and round history.

## Feature Overview - Round Service

The Round Service component enhances the Bulls and Cows Number Guessing Game by managing the core gameplay logic related to rounds. This includes:

1. **Round Management:**
   - Creation of rounds for each user guess, capturing the guess itself and the timestamp.

2. **Result Calculation:**
   - Calculation of exact and partial matches for each user guess compared to the game's answer.
   - Formatting the result in the "e:X:p:Y" format, where "X" represents exact matches and "Y" represents partial matches.

3. **Game Progression:**
   - Monitoring and updating the game's status based on user guesses, marking the game as finished when the correct answer is guessed.

4. **Round History:**
   - Providing the ability to retrieve a list of rounds for a specific game, sorted by timestamp.


## REST Endpoints Developed

The Round Service functionality is exposed through the following REST endpoints:

1. **POST /guess - Make a Guess:**
   - Allows users to make a guess by providing a guess JSON object.
   - Calculates the results of the guess and returns a Round object with the calculated results.
   - Endpoint: `POST /guess`
   - Consumes: `application/json`
   - Produces: `application/json`
   - Example Request Body:
     ```json
     {
       "gameId": 123,
       "guess": "1234"
     }
     ```
   - Example Response:
     ```json
     {
       "id": 1,
       "timestamp": "2023-08-07T15:30:00",
       "result": "e:1:p:2"
     }
     ```

2. **GET /rounds/{gameid} - Round History:**
   - Retrieves a list of rounds for the specified game, sorted by timestamp.
   - Enables users to review their guessing history and the corresponding results.
   - Endpoint: `GET /rounds/{gameid}`
   - Produces: `application/json`
   - Example Response (Multiple Rounds):
     ```json
     {
       "rounds": [
         {
           "id": 1,
           "timestamp": "2023-08-07T15:30:00",
           "result": "e:1:p:2"
         },
         {
           "id": 2,
           "timestamp": "2023-08-07T16:15:00",
           "result": "e:4:p:0"
         }
       ]
     }
     ```
   
3. **GET /round/{roundid} - Single Round Details:**
   - Retrieves details of a specific round based on the provided round ID.
   - Provides information about the round's timestamp and result.
   - Endpoint: `GET /round/{roundid}`
   - Produces: `application/json`
   - Example Response (Round Details):
     ```json
     {
       "id": 1,
       "timestamp": "2023-08-07T15:30:00",
       "result": "e:1:p:2"
     }
     ```

4. **POST /newround - Create a Round:**
   - Allows the creation of a new round, typically used for initializing rounds.
   - Accepts a Round object and returns the created Round with an ID.
   - Endpoint: `POST /newround`
   - Consumes: `application/json`
   - Produces: `application/json`
   - Example Request Body:
     ```json
     {
       "timestamp": "2023-08-07T15:30:00",
       "result": "e:1:p:2"
     }
     ```
   - Example Response:
     ```json
     {
       "id": 1,
       "timestamp": "2023-08-07T15:30:00",
       "result": "e:1:p:2"
     }
     ```


## Components Developed

1. **Round Service Implementation:**
   - Designed and implemented the Round Service, adhering to the game's business rules and logic.
   - Managed the creation of rounds, calculation of results, and game status updates.

2. **Integration with Game Service:**
   - Collaborated with the Game Service component to seamlessly integrate round management with the overall game flow.

## How to Access and Verify

To experience the Round Service functionality and verify its behavior:

1. **Set Up the Environment:**
   - Ensure that the Bulls and Cows Number Guessing Game project is set up and running.

2. **Using Postman:**
   - Utilize Postman to interact with the provided REST endpoints.
   - Use the POST endpoint to make guesses for a specific game and retrieve the corresponding Round results.
   - Use the GET endpoint to retrieve the round history for a specific game.

