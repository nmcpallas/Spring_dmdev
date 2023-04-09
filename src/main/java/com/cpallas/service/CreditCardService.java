package com.cpallas.service;

import com.cpallas.dto.CreditCardCreateEditDto;
import com.cpallas.dto.CreditCardReadDto;
import com.cpallas.mapper.CreditCardCreateEditMapper;
import com.cpallas.mapper.CreditCardReadMapper;
import com.cpallas.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreditCardService {

    private final CreditCardRepository repository;

    private final CreditCardCreateEditMapper editMapper;

    private final CreditCardReadMapper readMapper;

    public List<CreditCardReadDto> findAll() {
        return repository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    public Optional<CreditCardReadDto> findById(Integer id) {
        return repository.findById(id)
                .map(readMapper::map);
    }

    @Transactional
    public CreditCardReadDto create(CreditCardCreateEditDto createEditCard) {

        return Optional.of(createEditCard)
                .map(editMapper::map)
                .map(repository::save)
                .map(readMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<CreditCardReadDto> update(Integer id, CreditCardCreateEditDto createEditCard) {
        return repository.findById(id)
                .map(entity -> editMapper.map(createEditCard, entity))
                .map(repository::saveAndFlush)
                .map(readMapper::map);
    }


    @Transactional
    public boolean delete(Integer id) {
        return repository.findById(id).map(entity -> {
            repository.delete(entity);
            repository.flush();
            return true;
        }).orElse(false);
    }
}
