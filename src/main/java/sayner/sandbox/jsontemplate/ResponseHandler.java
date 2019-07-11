package sayner.sandbox.jsontemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {


    /**
     * Формирует правильную форму ответа моего сервиса
     *
     * @param status
     * @param isSuccess
     * @param message
     * @param responseObj
     * @return
     */
    public ResponseEntity<Object> generateResponse(HttpStatus status, boolean isSuccess, String message, Object responseObj) {

        /**
         * Карта состоит из описания сущности и, собственно, того самого объекта
         *  Object используется потому, что ответный json будет содержать сборную солянку всевозможных типов
         */
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("timestamp", new Date());
            map.put("http status", status.value());
            map.put("Успешность выполнения операции", isSuccess); // Ошибка может быть на уровне сервиса, а не сервера
            map.put("message", message);
            map.put("data", responseObj);

            return new ResponseEntity<>(map, status);

        } catch (Exception e) {
            map.clear(); // Ошибка могла появиться на любой строчке, лучше бы стереть предыдущие
            map.put("timestamp", new Date());
            map.put("http status", HttpStatus.INTERNAL_SERVER_ERROR.toString()); // Форма toString() самая удобная для использования
            map.put("Успешность выполнения операции", false); // 99.99% ошибка
            map.put("message", e.getMessage());
            map.put("data", null); // Пока так оставлю

            return new ResponseEntity<>(map, status);

        }

    }
}
