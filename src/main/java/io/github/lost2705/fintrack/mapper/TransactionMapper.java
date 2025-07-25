package io.github.lost2705.fintrack.mapper;

import io.github.lost2705.fintrack.dto.TransactionDto;
import io.github.lost2705.fintrack.dto.TransactionRequest;
import io.github.lost2705.fintrack.dto.TransactionResponse;
import io.github.lost2705.fintrack.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto toDto(TransactionRequest request);

    TransactionDto toDto(Transaction entity);

    Transaction toEntity(TransactionDto dto);

    @Mapping(target = "category", expression = "java(transaction.getCategory().getName())")
    TransactionResponse toResponse(Transaction transaction);

}