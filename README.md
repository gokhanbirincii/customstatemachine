http://localhost:8080/api/start

adresine request attığımızda response olarak:

`{
stateMachineId: "3a0b9dc8-50a5-43e5-9f0a-18e46371801a"
}`

dönecektir, daha sonra bu `UUID` ile state machine'i persist edip tekrar re-build edebilirsiniz.

Bu kısımlar implement edilmemiştir. [Şu](https://docs.spring.io/autorepo/docs/spring-statemachine/2.0.0.M1/reference/html/sm-persist.html) dökümanı inceleyebilirsiniz.

http://localhost:8080/api/execute?event=GECILMEK ISTENEN STATE EVENTI

Yine örnek üzerinden ilerleyecek olursak.

1. http://localhost:8080/api/start
2. http://localhost:8080/api/execute?event=GET_SHIPMENT_INFO

şeklinde sırasıyla apiye call yaparsak: 

`{
currentState: "SHIPMENT_INFO",
currentStateValue: "ShipmentInfo"
}`

bu durumda yukarıdaki responsu alırız state bilgisi memoryde durduğu için:

`StateMachine<StateMachineState, StateMachineEvent> stateMachine;  
`

beani bizim için state geçişini işletir.

state machine'i rediste tuttuğumuz zaman birden fazla makinede çalıştırılabilir durumda olmuş olacak bunun içinde yukarıda bahsettiğim linki takip edebilirsiniz.
