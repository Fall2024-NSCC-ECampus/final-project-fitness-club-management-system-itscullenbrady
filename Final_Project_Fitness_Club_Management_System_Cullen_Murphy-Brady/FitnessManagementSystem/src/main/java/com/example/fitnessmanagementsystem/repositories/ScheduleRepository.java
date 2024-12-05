/**
 * This interface extends the Spring Data JPA JpaRepository interface
 * which provides the standard CRUD operations.
 * 
 * The ScheduleRepository interface is used to manage the Schedule entity
 * which represents a schedule in the fitness management system.
 * 
 * The two methods, findByMember and findByTrainer, are used to query the
 * Schedule table to find all schedules that are associated with a particular
 * member or trainer.
 * 
 * The deleteByMember and deleteByTrainer methods are used to delete all
 * schedules that are associated with a particular member or trainer.
 * 
 * @author <a href="mailto:your_email@example.com">Your Name</a>
 */
package com.example.fitnessmanagementsystem.repositories;

import com.example.fitnessmanagementsystem.model.Member;
import com.example.fitnessmanagementsystem.model.Schedule;
import com.example.fitnessmanagementsystem.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This interface extends the Spring Data JPA JpaRepository interface
 * which provides the standard CRUD operations.
 * 
 * The ScheduleRepository interface is used to manage the Schedule entity
 * which represents a schedule in the fitness management system.
 * 
 * The two methods, findByMember and findByTrainer, are used to query the
 * Schedule table to find all schedules that are associated with a particular
 * member or trainer.
 * 
 * The deleteByMember and deleteByTrainer methods are used to delete all
 * schedules that are associated with a particular member or trainer.
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * Finds all schedules associated with a particular member.
     * 
     * @param member The member whose schedules are to be found.
     * @return A list of schedules associated with the member.
     */
    List<Schedule> findByMember(Member member);

    /**
     * Finds all schedules associated with a particular trainer.
     * 
     * @param trainer The trainer whose schedules are to be found.
     * @return A list of schedules associated with the trainer.
     */
    List<Schedule> findByTrainer(Trainer trainer);

    /**
     * Deletes all schedules associated with a particular member.
     * 
     * @param member The member whose schedules are to be deleted.
     */
    void deleteByMember(Member member);

    /**
     * Deletes all schedules associated with a particular trainer.
     * 
     * @param trainer The trainer whose schedules are to be deleted.
     */
    void deleteByTrainer(Trainer trainer);

}
