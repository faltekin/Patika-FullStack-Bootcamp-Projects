

![d1.png](images%2Fd1.png)


* 10 - Proje isterlerine göre hayvan sahibi kaydediliyor mu ?
  http://localhost:8080/v1/customers/save

![10.png](images%2F10.png)

* 11 - Hayvan sahipleri isme göre filtreleniyor mu ?
  http://localhost:8080/v1/customers/name/Fuat

![11.png](images%2F11.png)


* 12 - Proje isterlerine göre hayvan sahibi kaydediliyor mu ?
 http://localhost:8080/v1/animals/save

![12.png](images%2F12.png)

* 13 - Hayvanlar isme göre filtreleniyor mu ?
  http://localhost:8080/v1/animals/name/mini

![13.png](images%2F13.png)

* 14 - Girilen hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntüleme (sadece bir kişiye ait hayvanları görüntüle işlemi) başarılı bir şekilde çalışıyor mu ?
  http://localhost:8080/v1/animals/customer/1

![14.png](images%2F14.png)

* 15 - Proje isterlerine göre doktor kaydediliyor mu ?
  http://localhost:8080/v1/doctors/save

![15.png](images%2F15.png)

* 16 - Proje isterlerine göre doktor müsait günü kaydediliyor mu ?
  http://localhost:8080/v1/available-dates/save

![16.png](images%2F16.png)

* 17 - Proje isterlerine göre randevu kaydediliyor mu ?
* 18 - Randevu oluşturulurken, doktorun o saatte başka bir randevusu var mı, doktorun müsait günü var mı  kontrolü yapılıyor mu ?
  http://localhost:8080/v1/appointments/save

![17-1.png](images%2F17-1.png)
![17-2.png](images%2F17-2.png)
![17-3.png](images%2F17-3.png)
![17-4.png](images%2F17-4.png)

* 19 - Randevular kullanıcı tarafından girilen tarih aralığına ve hayvana göre filtreleniyor mu ?
  http://localhost:8080/v1/appointments/filter/animal/1/2030-01-01T10:00:00/2030-12-01T11:00:00

![19.png](images%2F19.png)

* 20 - Randevular kullanıcı tarafından girilen tarih aralığına ve doktora göre filtreleniyor mu ?
  http://localhost:8080/v1/appointments/filter/doctor/1/2030-01-01T10:00:00/2030-12-01T11:00:00

![20.png](images%2F20.png)

* 21 - Proje isterlerine göre hayvana ait aşı kaydediliyor mu ?
* 22 - Yeni aşı kaydetme işleminde koruyuculuk bitiş tarihi kontrolü yapılmış mı ?
  http://localhost:8080/v1/vaccines/save

![21-1.png](images%2F21-1.png)
![21-2.png](images%2F21-2.png)

* 23 - Hayvanların aşı kayıtları, girilen tarih aralığına göre doğru şekilde listeleniyor mu ?
  http://localhost:8080/v1/vaccines/filter/animal/2020-02-01/2024-03-10

![23.png](images%2F23.png)

* 24 - Belirli bir hayvana ait tüm aşı kayıtları (sadece bir hayvanın tüm aşı kayıtları) listelenebiliyor mu ?
  http://localhost:8080/v1/vaccines/filter/animal/2/2020-02-01/2024-03-10

![24.png](images%2F24.png)


   Customer
- http://localhost:8080/v1/customers/8 (id ile customer getirme)
- http://localhost:8080/v1/customers/save (customer save)
- http://localhost:8080/v1/customers/name/Fuat (name ile customer getirme)
- http://localhost:8080/v1/customers/update (customer update)
- http://localhost:8080/v1/customers/3 (customer delete)

   Animal
- http://localhost:8080/v1/animals/5 (id ile animal getirme)
- http://localhost:8080/v1/animals/save (animal save)
- http://localhost:8080/v1/animals/update (animal update)
- http://localhost:8080/v1/animals/9 (animal delete)

  Doctor
- http://localhost:8080/v1/doctors/10 (id ile doktor getirme)
- http://localhost:8080/v1/doctors/save (doctor save)
- http://localhost:8080/v1/doctors/update (doctor update)
- http://localhost:8080/v1/doctors/3 (doctor delete)

  Vaccine
- http://localhost:8080/v1/vaccines/3 (id ile aşı getirme)
- http://localhost:8080/v1/vaccines/save (vaccine save)
- http://localhost:8080/v1/vaccines/update (vaccine update)
- http://localhost:8080/v1/vaccines/400 (vaccine delete)

  Appointment
- http://localhost:8080/v1/appointments/3 (id ile appointment getirme)
- http://localhost:8080/v1/appointments/save (appointment save)
- http://localhost:8080/v1/appointments/update (appointment update)
- http://localhost:8080/v1/appointments/700 (appointment delete)

  Available Date
- http://localhost:8080/v1/available-dates/5 (id ile date getirme)
- http://localhost:8080/v1/available-dates/save (date save)
- http://localhost:8080/v1/available-dates/update (date update)
- http://localhost:8080/v1/available-dates/200 (date delete)












