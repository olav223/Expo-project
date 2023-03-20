package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
interface EventRepo extends JpaRepository<Event, Integer> {

    /**
     * @param id ID til eventen
     * @return Eventen til id, eller null
     */
    Event findById(int id);
}
