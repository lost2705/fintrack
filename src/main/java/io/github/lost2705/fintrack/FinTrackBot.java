package io.github.lost2705.fintrack;

import io.github.lost2705.fintrack.dto.TransactionDto;
import io.github.lost2705.fintrack.dto.TransactionRequest;
import io.github.lost2705.fintrack.model.Transaction;
import io.github.lost2705.fintrack.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class FinTrackBot extends TelegramLongPollingBot {

    private final TransactionService transactionService;

    public FinTrackBot(
            TransactionService transactionService
    ) {
        this.transactionService = transactionService;
    }

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        String messageText = update.getMessage().getText().trim();
        Long chatId = update.getMessage().getChatId();

        System.out.println("Update: " + messageText + " from chat " + chatId);

        if (messageText.startsWith("/start")) {
            handleStart(chatId);
        } else if (messageText.startsWith("/help")) {
            handleHelp(chatId);
        } else if (messageText.startsWith("/add")) {
            handleAdd(chatId, messageText);
        } else if (messageText.startsWith("/list")) {
            handleList(chatId);
        } else {
            handleUnknown(chatId);
        }

    }

    private void handleStart(Long chatId) {
        String text = """
            Hi! I am FinTrack bot.
            I can help you track your expenses and incomes right from Telegram.
            Use /help to see available commands.
            """;

        SendMessage message = new SendMessage(chatId.toString(), text);
        send(message);
    }

    private void handleHelp(Long chatId) {
        String text = """
            Available commands:
/start - short introduction
/help - this help message
/add <amount> categoryId> [description] - add a new transaction
/list - show last 5 transactions
            """;

        SendMessage message = new SendMessage(chatId.toString(), text);
        send(message);
    }


    private void handleUnknown(Long chatId) {
        String text = "Unknown command. Use /help to see available commands.";
        SendMessage message = new SendMessage(chatId.toString(), text);
        send(message);
    }

    private void send(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handleAdd(Long chatId, String messageText) {
        String[] parts = messageText.split("\\s+", 4);

        if (parts.length < 3) {
            String text = """
                Usage:
/add <amount> ategoryId> [description]

Examples:
/add 350 1 coffee at Starbucks
/add 100 2 taxi home
                """;
            send(new SendMessage(chatId.toString(), text));
            return;
        }

        String amountStr = parts[1];
        String categoryIdStr = parts[2];
        String description = parts.length == 4 ? parts[3] : "No description";

        BigDecimal amount;
        long categoryId;
        try {
            amount = new BigDecimal(amountStr);
        } catch (NumberFormatException e) {
            String text = "Amount must be a number. Example: /add 350 1 coffee at Starbucks";
            send(new SendMessage(chatId.toString(), text));
            return;
        }

        try {
            categoryId = Long.parseLong(categoryIdStr);
        } catch (NumberFormatException e) {
            String text = "CategoryId must be a number. Example: /add 350 1 coffee at Starbucks";
            send(new SendMessage(chatId.toString(), text));
            return;
        }

        TransactionDto dto = new TransactionDto();
        dto.setId(null);
        dto.setAmount(amount);
        dto.setCategoryId(categoryId);
        dto.setDescription(description);
        dto.setDate(LocalDate.now());

        try {
            Transaction created = transactionService.create(dto);

            String text = String.format(
                    "Transaction created:\nId: %d\nAmount: %s\nCategoryId: %d\nDescription: %s\nDate: %s",
                    created.getId(),
                    created.getAmount().toPlainString(),
                    created.getCategory().getId(),
                    created.getDescription(),
                    created.getDate()
            );

            send(new SendMessage(chatId.toString(), text));
        } catch (Exception e) {
            String text = "Failed to create transaction: " + e.getMessage();
            send(new SendMessage(chatId.toString(), text));
        }
    }

    private void handleList(Long chatId) {
        var all = transactionService.findAll();

        if (all.isEmpty()) {
            send(new SendMessage(chatId.toString(), "No transactions yet."));
            return;
        }

        int limit = Math.min(5, all.size());

        StringBuilder sb = new StringBuilder("Last " + limit + " transactions:\n");
        all.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .limit(limit)
                .forEach(tx -> sb.append(String.format(
                        "- %s: %s (%s)\n",
                        tx.getDate(),
                        tx.getAmount().toPlainString(),
                        tx.getDescription()
                )));

        send(new SendMessage(chatId.toString(), sb.toString()));
    }
}
