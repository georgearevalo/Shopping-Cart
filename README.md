# Shopping-Cart
This project is a Spring Boot application that provides Series of microservices to manage functionality for a Shopping Cart.

## Prerequisites

Before getting started, ensure you have the following prerequisites installed:

* Java 17 or later
* Maven 3.6 or later
* Git (optional, for cloning the repository)

## Installation Steps

1.  **Clone the Repository (Optional):**

    If you're using Git, clone the repository to your local machine:

    ```bash
    git clone [repository URL]
    cd [project name]
    ```

2.  **Build the Project:**

    Use Maven to build the project:

    ```bash
    mvn clean install
    ```

3.  **Run the Application:**

    * **From the JAR:**

        Navigate to the `target` directory and run the JAR file:

        ```bash
        java -jar [project name].jar

## Configuration

The application is configured through the `application.properties` file. Here are some important properties:

* `server.port`: Port on which the application runs.

You can modify these properties according to your needs.

## Resources
Projects consist of the following 5 main APIs:

- [Products-services](http://localhost:8080/products-services/v1/swagger-ui/index.html)

The application exposes the following endpoints:

* `GET /products`: Retrieves product.
* `POST /products`: Creates a new product.
* `PUT /products`: Update a product.
* `DELETE /products`: Delete a product.

- [Customers-services](http://localhost:8081/customers-services/v1/swagger-ui/index.html)

The application exposes the following endpoints:

* `GET /customers`: Retrieve customer.
* `POST /customers`: Creates a new customer.
* `PUT /customers`: Update a customer.
* `DELETE /customers`: Delete a customer.

- [Orders-services](http://localhost:8082/orders-services/v1/swagger-ui/index.html)

The application exposes the following endpoints:

* `GET /orders`: Retrieve order.
* `POST /orders`: Creates a new order.
* `PUT /orders`: Update a order.
* `DELETE /orders`: Delete a order.

- [Details-services](http://localhost:8083/details-services/v1/swagger-ui/index.html)

The application exposes the following endpoints:

* `GET /details`: Retrieve detail.
* `POST /details`: Creates a new detail.
* `PUT /details`: Update a detail.
* `DELETE /details`: Delete a detail.

- [Pays-services](http://localhost:8084/pays-services/v1/swagger-ui/index.html)

The application exposes the following endpoints:

* `GET /pays`: Retrieve pay.
* `POST /pays`: Creates a new pay.


## Dependencies

The project uses the following main dependencies:

* Spring Boot
* Spring Security
* Feign

## Contributing

If you'd like to contribute to this project, please follow these steps:

1.  Fork the repository.
2.  Create a feature branch (`git checkout -b feature/NewFeature`).
3.  Make your changes.
4.  Commit your changes (`git commit -am 'Add new feature'`).
5.  Push to the branch (`git push origin feature/NewFeature`).
6.  Open a pull request.