# hackatonNetris

## Задача
1. Принимать post запросы c бинарником в теле запроса
   {
   client_id: String;
   video_id: String; // (UUID)
   chunk_number: String;
   complete: Boolean;
   }
2. Дробить бинарник на чанки по 1 мб
3. Отправлять чанки в модель с помощью post запроса c бинарником в теле запроса
   {
   client_id: Long;
   video_id: String; // (UUID)
   chunk_number: Long;
   complete: Boolean;
   }