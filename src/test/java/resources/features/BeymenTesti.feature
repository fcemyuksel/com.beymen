

Feature: US01 Beymen Testi

  @wip
  Scenario: Kullanici beymen sayfasinda arama yapabilmeli

  Given beymen sitesi açılır.
  Then Ana sayfanın açıldığı kontrol edilir.
  When Arama kutucuğuna “şort” kelimesi girilir.
  Then Arama kutucuğuna girilen “şort” kelimesi silinir.
  Then Arama kutucuğuna “gömlek” kelimesi girilir.
  Then Klavye üzerinden “enter” tuşuna bastırılır
  And Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
  Then Seçilen ürünün ürün bilgisi ve tutar bilgisi txt dosyasına yazılır.
  And Seçilen ürün sepete eklenir.
  Then Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.
  Then Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.
  And Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.