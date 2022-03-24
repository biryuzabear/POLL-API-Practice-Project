package com.fedorchenko.testTask.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(description = "С помощью данного API можно работать с опросами. Есть две роли, админа и пользователя. \n" +
        "\n" +
        "Админ получает доступ ко всем сущностям напрямую, в виде которых хранится информация об опросе и пользователях. \n" +
        "\n" +
        "Пользователь взаимодействует с опросами посредством ряда DTO, имеет возможность получать список всех проходящих на данный момент опросов, просматривать вопросы в каждом из опросов и имеет возможность отвечать на вопросы.\n" +
        "\n" +
        "Опросы, которые пользователь прошел, можно посмотреть еще раз, увидев всю детализацию по ответам.  " +
        "\n" +
        "\n" +
        "ВНИМАНИЕ: API расположен по мэппингу \"/api\", так что этот префикc должен быть перед каждым запросом. " +
        "Это обозначено в сервере на странице ниже, но это легко можно не заметить.",
        contact = @Contact(name = "Vladimir Fedorchenko", email = "fedorchenko.v.a@yandex.ru"),
        title = "API для работы с опросами",
        version = "0.1.0"))
public class SwaggerConfig {

}
