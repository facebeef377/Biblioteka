package com.project.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRepository extends CrudRepository<Lease,Integer> {

@Query(value = "SELECT * FROM lease WHERE book_id = ? AND return_date IS NULL;",nativeQuery = true)
    List<Lease> getByBookNotReturned(Integer book_id);

}
