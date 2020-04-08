# TextStats
Server Project to get statistics like counts, frequencies, most common words etc.. for the uploaded files.

How to :


  - Build the project :
    
    Go to server directory under the main TextStats dir from a terminal
    
    run ->  ./gradlew clean build
    This will clean and build the TextStats server project
    
  - Run the application
  
    Go to server directory under the main TextStats dir from a terminal
  
    run-> ./gradlew bootRun
    
    Server will start at port 8080
    
    Upload a file:
    
    To upload a file use Postman:
    
    - URL:  http://localhost:8080/api/upload
    	- Choose form-data under Body
    	- select the file using "Select Files" option
      
      Note : Server allows to  upload one file at a time
             You can use the same POST URl to resubmit multiple file uploads
             
    Get the Text Stats
       -URL: http://localhost:8080/api/stats
       This will return the stats and for the most frequent stats will default to 3 entries along with the total counts
       
       - URL : http://localhost:8080/api/stats?limit=5
       This will return the stats and will return 5 most frequent stats along with total counts
       API will return the file names and their respective stats like below
       
       Sample Output :
       
       "sample.txt": {
        "wordCount": 25,
        "characterCount": 83,
        "symbolCount": 5,
        "frequentlyUsedWords": [
            "a",
            "file",
            "this"
        ],
        "frequentlyUsedCharacters": [
            "s",
            "i",
            "e"
        ],
        "frequentlyUsedSymbols": [
            "$",
            "%",
            "#"
        ]
    }

    
  
    
