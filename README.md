<a id="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Movies app</h3>

  <p align="center">
    Application for managing collections of movies
    <br />
<!--     <a href="https://github.com/othneildrew/Best-README-Template"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a>
    &middot;
    <a href="https://github.com/othneildrew/Best-README-Template/issues/new?labels=bug&template=bug-report---.md">Report Bug</a>
    &middot;
    <a href="https://github.com/othneildrew/Best-README-Template/issues/new?labels=enhancement&template=feature-request---.md">Request Feature</a> -->
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>






<!-- ABOUT THE PROJECT -->
## About The Project

### Architecture design
```mermaid
  graph TD;

    Angular_nginx-->Api_Gateway;
    Api_Gateway-->Eureka_Discovery_Service;
    Movies_instance_1-->Eureka_Discovery_Service;
    Movies_instance_2-->Eureka_Discovery_Service;
    Eureka_Discovery_Service-->Api_Gateway;
    Genres-->Eureka_Discovery_Service;

    Config_Server-->Api_Gateway;

    Config_Server-->Genres;
    Genres-->Postgres_genres;


    Config_Server-->Movies_instance_1;
    Movies_instance_1-->Postgres_movies;

    Config_Server-->Movies_instance_2;

    Movies_instance_2-->Postgres_movies;

```

The project is built on a microservices architecture, where each microservice has its own dedicated database. A reverse proxy is used to route client requests to the application gateway, ensuring seamless communication. Configuration properties for each microservice are managed centrally through a config server, while Eureka Discovery Service is employed to monitor and track the location of each service. All services are containerized using Docker, with Docker Compose orchestrating the containers' lifecycle. Microservices interact via REST APIs, which will soon transition to Apache Kafka for more robust communication. Authorization and authentication are securely handled using JWT tokens.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Built With
<p> <a href="https://angular.io"><img src="https://skillicons.dev/icons?i=angular" /></a> The front-end of the application is built with the Angular framework, utilizing NgRx Store for state management and Angular Material for theming and reusable components. </p> 

<p> <a href="https://spring.io"><img src="https://skillicons.dev/icons?i=spring" /></a> Spring MVC is used to develop RESTful APIs for the application gateway and microservices. Spring Security provides robust authorization and authentication mechanisms, while Spring Cloud Netflix Eureka Server handles service discovery. </p>

<p> <a href="https://docker.com"><img src="https://skillicons.dev/icons?i=docker" /></a> Docker ensures the containerization of the entire application, with each microservice running in its own container. Docker Compose manages the container network, and volumes are created for PostgreSQL data persistence to prevent data loss. </p>

<p><a href="https://cloud.spring.io/spring-cloud-netflix/reference/html/">Spring Cloud Netflix - Eureka</a> is crucial for tracking the location and availability of microservices.</p>

<p> <a href="https://www.liquibase.com/">Liquibase</a> is used for creating database migrations. It allows version control for database changes. 
</p>
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started


1. Clone the repo
   ```sh
   git clone https://github.com/github_username/repo_name.git
   ```
2. Install NPM packages inside the frontend folder
   ```sh
   npm install
   ```
3. Add .env file based on example .env.sample 
4. Run script for building all services
   ```sh
    ./building_script.sh
   ```
5. Run application
   ```sh 
    sudo docker compose up --build -d
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ROADMAP -->
## Further roadmap

- [ ] Add Apache Kafka for communication between microservices
- [ ] Improve styles of the app 

See the [open issues](https://github.com/othneildrew/Best-README-Template/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- CONTACT -->
## Contact

Vitalii Shapovalo - [@LinkedIn](https://www.linkedin.com/in/vitalii-shapovalov-6670ba26a/) - shapovalovvit0@gmail.com

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
