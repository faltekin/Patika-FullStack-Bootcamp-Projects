
Patika+ FullStack Web Developer Veteriner Yönetim Sistemi Bitirme Projesi
- 

![d1.png](images%2Fd1.png)





Projede Bulunan Entityler
- 
  * Animal
  * Customer
  * Vaccine
  * Doctor
  * AvailableDate
  * Appointment

Customer ve Animal İsterler
- 
  * kaydetme, güncelleme, görüntüleme ve silme
  *  isme göre filtreleme
  * Hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntülenmesi

Vaccine İsterler
- 
* kaydetme, güncelleme, görüntüleme ve silme
* Eğer hastaya ait aynı tip aşının (adı ve kodu aynı olan aşı) aşı koruyuculuk bitiş tarihi daha gelmemiş ise sisteme yeni aşı girilememelidir.
* Hayvan id’sine göre belirli bir hayvana ait tüm aşı kayıtlarını listelemek
* Kullanıcının aşı koruyuculuk bitiş tarihi yaklaşan hayvanları listeleyebilmesi için gireceği başlangıç ve bitiş tarihlerine göre aşı koruyuculuk bitiş tarihi bu aralıkta olan aşıları hayvan bilgileriyle birlikte listesini geri döndüren API end point'ini oluşturmak.

Customer ve Animal İsterler
- 
* kaydetme, güncelleme, görüntüleme ve silme
*  isme göre filtreleme
* Randevular sisteme tarih ve saat içerecek şekilde kaydedilmelidir.
* Randevu kaydı oluştururken doktorun girilen tarihte müsait günü olup olmadığı eğer ki müsait günü varsa randevu kayıtlarında girilen saatte başka bir randevusu olup olmadığı kontrol edilmelidir.


Doctor İsterler
- 
* kaydetme, güncelleme, görüntüleme ve silme

AvailableDate İsterler
- 
* kaydetme, güncelleme, görüntüleme ve silme

Dikkat Edilecekler
- 
* Request ve Response DTO’lar kullanılacak
* Projede belirtilen entity (varlık) sınıflarını ve bunların arasındaki ilişkiler belirlenmelidir.
* Exception kullanılmalı
* Tüm yeni veri kaydetme işlemlerinde zaten var olan bir verinin kaydedilmediği kontrol edilmelidir.




Kullandıklarım
-
* postgresql
* projectlombok
* modelmapper
* postman - pgAdmin





   Customer
- http://localhost:8080/v1/customers/save (customer save)
- http://localhost:8080/v1/customers/update (customer update)
- http://localhost:8080/v1/customers/name/name/fuat (değerlendirme formu 11 - Hayvan sahipleri isme göre filtreleme)
- http://localhost:8080/v1/customers/14 (customer delete)
- http://localhost:8080/v1/customers/getAll (customer find all)

   Animal
- http://localhost:8080/v1/animals/save (animal save)
- http://localhost:8080/v1/animals/update (animal update)
- http://localhost:8080/v1/animals/14 (animal delete)
- http://localhost:8080/v1/animals/getAll (animal find all)
- http://localhost:8080/v1/animals/name/name/Yastık (değerlendirme formu 13 - Hayvanları isme göre filtreleme)
- http://localhost:8080/v1/animals/customer/customer/2 (değerlendirme formu 14 - Müşterinin sistemdeki tüm hayvanlarını görme)

  Doctor
- http://localhost:8080/v1/doctors/save (doctor save)
- http://localhost:8080/v1/doctors/update (doctor update)
- http://localhost:8080/v1/doctors/3 (id ile doctor getirme - delete)
- http://localhost:8080/v1/doctors/getAll (doctor find all)

  Vaccine
- http://localhost:8080/v1/vaccines/save (vaccine save)
- http://localhost:8080/v1/vaccines/update (vaccine update)
- http://localhost:8080/v1/vaccines/400 (vaccine delete - id ile vaccine getirme)
- http://localhost:8080/v1/vaccines/filter/filter/animal/13 (değerlendirme formu 24 - Belirli bir hayvana ait tüm aşı kayıtları (sadece bir hayvanın tüm aşı kayıtları))
- http://localhost:8080/v1/vaccines/filter/filter/animal/2099-04-25/2099-05-28 (değerlendirme formu 23 - Hayvanların aşı kayıtları, girilen tarih aralığına göre doğru şekilde listeleniyor mu ?)
- http://localhost:8080/v1/vaccines/getAll (vaccine find all)

  Appointment
- http://localhost:8080/v1/appointments/save (appointment save)
- http://localhost:8080/v1/appointments/update (appointment update)
- http://localhost:8080/v1/appointments/getAll (appointment find all) 
- http://localhost:8080/v1/appointments/7 (appointment delete - id ile appointment getirme)
- http://localhost:8080/v1/appointments/filter/doctor/doctor3/1/2020-01-01/2033-12-01 (değerlendirme formu 20 - Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtreleniyor mu ?)
- http://localhost:8080/v1/appointments/filter/animal/animal3/10/2020-01-01/2033-12-01 (değerlendirme formu 19 - Randevular kullanıcı tarafından girilen tarih aralığına ve hayvana göre filtreleniyor mu ?)

  Available Date
- http://localhost:8080/v1/available-dates/save (date save)
- http://localhost:8080/v1/available-dates/update (date update)
- http://localhost:8080/v1/available-dates/getAll (date find all) 
- http://localhost:8080/v1/available-dates/11 (date delete - id ile date getirme)












