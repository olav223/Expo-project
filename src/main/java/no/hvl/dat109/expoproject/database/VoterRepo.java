package no.hvl.dat109.expoproject.database;

import no.hvl.dat109.expoproject.entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepo extends JpaRepository<Voter, String> {
}
