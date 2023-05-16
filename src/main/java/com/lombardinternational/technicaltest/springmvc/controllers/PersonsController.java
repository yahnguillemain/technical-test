package com.lombardinternational.technicaltest.springmvc.controllers;

import com.lombardinternational.technicaltest.springmvc.api.PersonDto;
import com.lombardinternational.technicaltest.springmvc.exception.NotFoundException;
import com.lombardinternational.technicaltest.springmvc.exception.PersonNotModifiableException;
import com.lombardinternational.technicaltest.springmvc.mapper.PersonDtoMapper;
import com.lombardinternational.technicaltest.springmvc.service.PersonsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

//TODO

public class PersonsController {

}
