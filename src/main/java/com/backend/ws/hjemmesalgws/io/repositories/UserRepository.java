package com.backend.ws.hjemmesalgws.io.repositories;

import com.backend.ws.hjemmesalgws.io.entity.User_Entity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User_Entity, Long> {

    User_Entity findByEmail(String email); //Sådan Querier man sin database, hvis man har defineret denne i sin Entity VIGTIGT "findBy"

    User_Entity findByUserId(String id);
}
