package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Stand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandRepo extends JpaRepository<Stand,Integer> {

    /**
    @param id of the stand
    @return stand with the id
     */
    Stand getStandById(int id);
}
