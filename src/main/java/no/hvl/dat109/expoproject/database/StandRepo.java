package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Stand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandRepo extends JpaRepository<Stand, Integer> {

    /**
     * @param id of the stand
     * @return stand with the id
     */
    Stand findById(int id);

    Stand deleteById(int id);

    List<Stand> findAllByEvent(Event event);
}
