package org.example;


import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class ReactiveTests {

    private UnitOfMeasure uom1;
    private UnitOfMeasure uom2;
    private UnitOfMeasure uom3;
    private UnitOfMeasure uom4;
    private UnitOfMeasure uom5;
    private UnitOfMeasure uom6;
    private UnitOfMeasure uom7;
    private UnitOfMeasure uom8;
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

        uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");

        uom2 = new UnitOfMeasure();
        uom2.setDescription("Tablespoon");

        uom3 = new UnitOfMeasure();
        uom3.setDescription("Cup");

        uom4 = new UnitOfMeasure();
        uom4.setDescription("Pinch");

        uom5 = new UnitOfMeasure();
        uom5.setDescription("Ounce");

        uom6 = new UnitOfMeasure();
        uom6.setDescription("Each");

        uom7 = new UnitOfMeasure();
        uom7.setDescription("Pint");

        uom8 = new UnitOfMeasure();
        uom8.setDescription("Dash");
    }
    @Test
    public void monoTests() throws Exception {

        //create person mono
        Mono<UnitOfMeasure> uomMono = Mono.just(uom1);

        // get Person object from mono publisher
        UnitOfMeasure uom1 = uomMono.block();

        // Log name
        log.info(uom1.getDescription());
    }

    @Test
    public void monoTransformTest() throws Exception {
        //create new person mono
        Mono<UnitOfMeasure> personMono = Mono.just(uom2);

        UnitOfMeasureCommand command = personMono
                .map(uom -> unitOfMeasureToUnitOfMeasureCommand.convert(uom)).block();
        log.info(command.getDescription());

    }

    @Test(expected = NullPointerException.class)
    public void monoFilterTest() throws Exception {
        Mono<UnitOfMeasure> uomMono = Mono.just(uom3);

        UnitOfMeasure cup = uomMono
                .filter(uom -> uom.getDescription().equalsIgnoreCase("foo"))
                .block();
        log.info(cup.getDescription()); //throws NPE

    }

    /*
    @Test
    public void fluxTest() {
        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.subscribe(person -> log.info(person.sayMyName()));
    }


    @Test
    public void fluxFilterTest() {
        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.filter(person -> person.getFirstName().equalsIgnoreCase(fiona.getFirstName()))
                .subscribe(person -> log.info(person.sayMyName()));
    }

    @Test
    public void fluxTestDelayNoOutput() throws Exception{
        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .subscribe(person -> log.info(person.sayMyName()));
    }

    @Test
    @Repeat(5)
    public void fluxTestDelayNoOutput2() throws Exception{
        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);
        List<Person> people1 = new ArrayList<>();

        people.delayElements(Duration.ofSeconds(1))
                .subscribe(person -> people1.add(person));

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await(new Random().nextInt(5), TimeUnit.SECONDS);

        if(people1.size() == 0) {
            log.info(rafael.sayMyName());
        } else {
            people1.forEach(person -> log.info(person.sayMyName()));
        }

    }
    @Test
    public void fluxTestDelay() throws Exception  {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayMyName()));

        countDownLatch.await();
    }

    @Test
    public void fluxTestFilterDelay() throws Exception  {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Flux<Person> people = Flux.just(michael, fiona, sam, jesse);

        people.delayElements(Duration.ofSeconds(1))
                .filter(person -> person.getFirstName().contains("i"))
                .doOnComplete(countDownLatch::countDown)
                .subscribe(person -> log.info(person.sayMyName()));

        countDownLatch.await();
    }
    */

}
