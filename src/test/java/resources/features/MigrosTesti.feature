Feature: US01 Migros Testi


  Scenario: Migros sayfasinda alisveris yapar

  Given Kullanicidan alisveris limiti belirlemesi istenir.
  When Kullanici https://www.migros.com.tr/ sitesine gider
  When Kullanici pop-up ve cerezleri kapatir.
  Then Adres belirleme islemi gerceklestirilir
  When Kullanici sitenin dogru oldugunu kontrol eder.
  Then Kullanici Migros Hemen sekmesini secer.
  When Kullanici kategoriler kismindan Temel Gida'yi secer.
  When Temel gidadan alt kategori random olarak secilir
  And kullanicidan alinan tutar kadar random urun sepete atilir.
  And Kullanici sepete gider tutarin belirlenen tutardan yuksek olmadigini teyid eder