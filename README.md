Created a microservice architecture for a libraray-management system. In which services are communicating with one another using RestTemplate. Also implemented API gateway as well as Eureka server service registry.

There are basically three services library-service, book-service, worker-service. All these services are responsible for particular tasks for instance library-service is soley responsible for managing the inventory of books and keeping track of issued as well as available books.

customer-service lets you create customers which can then get the books issued.

worker-service also issues books and can generated list of customers in the library and their details.

All these services are communicating with one another synchronously using restTemplate.
