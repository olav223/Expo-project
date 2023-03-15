package no.hvl.dat109.expoproject.service;

import no.hvl.dat109.expoproject.database.VoteRepo;
import no.hvl.dat109.expoproject.entities.Event;
import no.hvl.dat109.expoproject.entities.Vote;
import no.hvl.dat109.expoproject.entities.Voter;
import no.hvl.dat109.expoproject.interfaces.database.IVoteService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;

@Service
public class VoteService implements IVoteService {
    private final int STRING_LENGTH = 6;

    @Autowired
    private VoteRepo voteRepo;

    @Override
    public List<Vote> getAllVotesInEvent(int eventID) {
        return voteRepo.getAllVotesByEventID(eventID);
    }

    @Override
    public int getVote(int voterID, int standID) {
        return voteRepo.getVote(voterID, standID);
    }

    @Override
    public void registerVote(Vote vote) {
        voteRepo.save(vote);
    }

    @Override
    public void generateVoteCodes(int nrOfCodes, int eventID) {
        for(int i = 1; i <= nrOfCodes; i++){
           String code = StringUtils.left(UUID.randomUUID().toString(), STRING_LENGTH).toUpperCase();
            storeCode(code, eventID);
        }
    }

    @Override
    public List<String> getAllVoteCodes(int eventID) {
        return voteRepo.getAllVoteCodes(eventID);
    }

    //Old but gold
    private void storeCode(String code, int eventID){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("expo");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Voter voter;
        try{
            tx.begin();
            Event event = em.find(Event.class, eventID);
            voter = new Voter(code, event);

            em.persist(voter);

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(tx.isActive())
                tx.rollback();
        }finally {
            em.close();
        }
    }
}
