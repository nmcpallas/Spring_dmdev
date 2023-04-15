package com.cpallas.http.rest;

import com.cpallas.dto.CreditCardCreateEditDto;
import com.cpallas.dto.CreditCardReadDto;
import com.cpallas.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CreditCardRestController {

    private final CreditCardService service;

    @GetMapping
    public ResponseEntity<List<CreditCardReadDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardReadDto> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreditCardReadDto> create(@RequestBody CreditCardCreateEditDto creditCardDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.create(creditCardDto));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<CreditCardReadDto> update(@PathVariable("id") Integer id, @RequestBody CreditCardCreateEditDto creditCardDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service
                        .update(id, creditCardDto)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                );
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        if (!service.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
