# Multithreaded Web Crawler (Java)

A high-performance, multithreaded web crawler built using **Java**, **JSoup**, and **ExecutorService**. This crawler can recursively fetch and display links from any given website up to a user-defined depth using multiple worker threads.
<img width="735" height="540" alt="image" src="https://github.com/user-attachments/assets/1bcd4f4c-9919-44fa-b731-2eb12eee00e0" />
## Tech Stack
- Java 23
- JSoup (for HTML parsing)
- Multithreading (ExecutorService)
- Phaser (for thread coordination)
- IntelliJ IDEA
- Maven
## Features
- Accepts a URL and crawling depth from user
- Uses multiple threads for faster crawling
- Parses HTML and extracts hyperlinks using JSoup
- Avoids duplicate crawling via thread-safe structures (ConcurrentHashMap)
- Efficient synchronization via Phaser
- Prints visited links and page text
 ##  Sample Output
<img width="1836" height="776" alt="image" src="https://github.com/user-attachments/assets/fca1ace1-bb7e-453d-aa4d-eb6e70c7de4a" />
 <img width="1717" height="641" alt="image" src="https://github.com/user-attachments/assets/899f444f-1fad-4240-8ccf-88564fc70162" />
 
