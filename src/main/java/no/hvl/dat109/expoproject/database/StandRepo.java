package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("select s from Stand s join Event e on e.id = ?1")
    List<Stand> findAllByEvent(Integer eventID);
}
