package io.github.lost2705.fintrack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(FinTrackBot finTrackBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(finTrackBot);
        } catch (TelegramApiException e) {
            // если ошибка только про "Error removing old webhook" / 404 — логируем и продолжаем
            if (e.getMessage() != null && e.getMessage().contains("Error removing old webhook")) {
                System.out.println("Webhook уже не настроен, продолжаем как long polling бот");
            } else {
                throw e;
            }
        }
        return botsApi;
    }
}
