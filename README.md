# Order Portal

For Enoca JAVA CHALLENGE
## Proje Bağımlılıkları
- Spring Boot
- Spring Boot Starter Web
- Spring Data JPA
- Maven
- H2 Database http://localhost:8080/h2-console/  username=sa  
- Swagger UI  http://localhost:8080/swagger-ui.html
- Lombok

## API Kullanımı


```http
  GET /api/product/all
   
```
![image](https://github.com/Theoguz/OrderPortal/assets/73759725/893af6e0-4016-4910-8c7b-d77d69156843)


```http
  PUT /api/product/update/Elma
  
```
![image](https://github.com/Theoguz/OrderPortal/assets/73759725/4c63886d-77a0-4668-82ee-be4b1b29db16)
![image](https://github.com/Theoguz/OrderPortal/assets/73759725/6e509bbf-4cb4-4dfc-b5d7-29f9b2458acf)


```http
  POST /api/customer/add
  
```
![image](https://github.com/Theoguz/OrderPortal/assets/73759725/6271e088-45dc-4621-99b6-3ced1374b29a)


```http
  POST /api/cart/add
  
```
## Ek
Kullanıcı adı, ürün fiyatı yanlış yazıldığında yada stoğumuz bittiğinde kodumuz ERROR loguyla detayını atmaktadır.
![image](https://github.com/Theoguz/OrderPortal/assets/73759725/6b8e7921-cf84-495f-a2ba-dae555e96b89)


```http
  POST /api/order/place/{cartId}
  
```
![image](https://github.com/Theoguz/OrderPortal/assets/73759725/871de8d8-5b8c-416e-8d6c-30bd547b8438)


```http
  GET /api/order/getAll
   
```
![image](https://github.com/Theoguz/OrderPortal/assets/73759725/18165e33-1817-4381-b2b9-3c0d95c73d9a)


## Swagger
![image](https://github.com/Theoguz/OrderPortal/assets/73759725/09ea61e9-86fe-4252-bf9e-b253068f6674)










