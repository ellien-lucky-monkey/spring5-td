package com.ellien.spring5.services;

import com.ellien.spring5.entities.Person;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ellien
 * @date 2018/04/16 15:31
 */
@Service
public class PersonService {

    private final Map<Long, Person> data = new ConcurrentHashMap<>();

    public Flux<Person> list() {
        return Flux.fromIterable(this.data.values());
    }

    public Flux<Person> getById(final Flux<Long> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
    }

    public Mono<Person> getById(final Long id) {
        return Mono.justOrEmpty(this.data.get(id))
                .switchIfEmpty(Mono.error(new NullPointerException()));
    }

    Flux<Person> createOrUpdate(final Flux<Person> persons) {
        return persons.doOnNext(person -> this.data.put(person.getId(), person));
    }

    public Mono<Person> createOrUpdate(final Person person) {
        this.data.put(person.getId(), person);
        return Mono.just(person);
    }

    public Mono<Person> delete(final Long id) {
        return Mono.justOrEmpty(this.data.remove(id));
    }
}
